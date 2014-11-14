package br.com.afrcode.arquitetura.util.jsf.excecao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.Validate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import br.com.afrcode.arquitetura.is.util.excecao.ExcecaoNegocioRemota;
import br.com.afrcode.arquitetura.spring.config.security.AccessDeniedHandlerImpl;
import br.com.afrcode.arquitetura.util.excecao.ExcecaoNaoPrevista;
import br.com.afrcode.arquitetura.util.excecao.ExcecaoNegocio;
import br.com.afrcode.arquitetura.util.excecao.TratadorConstraintViolationException;
import br.com.afrcode.arquitetura.util.excecao.TratadorExcecaoNegocio;
import br.com.afrcode.arquitetura.util.excecao.TratadorExcecaoNegocioRemota;
import br.com.afrcode.arquitetura.util.excecao.TratadorExcecoesNaoPrevistas;

/**
 * Classe central de tratamento de exceções para o JSF 2.
 * 
 * Determinados tipos de exceções são tratados de alguma forma a partir desta
 * classe - gerando mensagens ao usuário, logs, redirecionamento p/ páginas de
 * erros inesperados, etc. Ver tratarExcecaoSeExcecaoConhecida(Throwable).
 * 
 * ATENÇÃO: o tratamento da exceção quanto a transações é feito anteriormente a
 * este tratador, ou seja, neste ponto a transação corrente já deixou de existir
 * (via rollback - Transactional).
 * 
 * 
 */
public class JSF2ExceptionHandler extends ExceptionHandlerWrapper {
	private ExceptionHandler wrapped;

	public JSF2ExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	private HttpServletRequest getHttpServletRequest(ExceptionQueuedEvent event) {
		Object requestObj = event.getContext().getContext()
				.getExternalContext().getRequest();
		Validate.isTrue(HttpServletRequest.class.isAssignableFrom(requestObj
				.getClass()), "request não é HttpServletRequest!");
		HttpServletRequest request = (HttpServletRequest) requestObj;
		return request;
	}

	private HttpServletResponse getHttpServletResponse(
			ExceptionQueuedEvent event) {
		Object requestObj = event.getContext().getContext()
				.getExternalContext().getResponse();
		Validate.isTrue(HttpServletResponse.class.isAssignableFrom(requestObj
				.getClass()), "request não é HttpServletResponse!");
		HttpServletResponse request = (HttpServletResponse) requestObj;
		return request;
	}

	private WebApplicationContext getWebApplicationContext(
			ExceptionQueuedEvent event) {
		WebApplicationContext webCtx = FacesContextUtils
				.getWebApplicationContext(event.getContext().getContext());
		return webCtx;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		List<ExcecaoNaoPrevista> excecoesNaoPrevistas = new ArrayList<ExcecaoNaoPrevista>();

		for (Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents()
				.iterator(); it.hasNext();) {
			ExceptionQueuedEvent event = it.next();
			ExceptionQueuedEventContext ctx = event.getContext();

			Throwable te = obterExpcetionCause(ctx.getException());
			if (te instanceof AbortProcessingException) {
				// Exceções internas ao ciclo de vida do JSF serão tratadas
				// por ele mesmo, não é necessário prosseguir na cadeia
				// de exceções.
				super.handle();
				return;
			}

			boolean excecaoTratada = false;
			try {
				excecaoTratada = tratarExcecaoSeExcecaoConhecida(te, event);
			} finally {
				if (excecaoTratada) {
					// Indicando ao JSF 2 que houve erro de validação.
					FacesContext.getCurrentInstance().validationFailed();
					it.remove();
				}
			}
		}

		tratarExcecoesNaoPrevistas(FacesContext.getCurrentInstance(),
				excecoesNaoPrevistas);
	}

	private Throwable obterExpcetionCause(Throwable exception) {
		Throwable te = exception;
		while (te.getCause() != null) {
			te = te.getCause();
		}
		return te;
	}

	private void tratarAccessDeniedException(AccessDeniedException ex,
			ExceptionQueuedEvent event) {
		WebApplicationContext webCtx = getWebApplicationContext(event);
		HttpServletRequest request = getHttpServletRequest(event);
		HttpServletResponse response = getHttpServletResponse(event);
		AccessDeniedHandlerImpl handler = webCtx
				.getBean(AccessDeniedHandlerImpl.class);
		handler.handle(request, response, ex);
	}

	private void tratarConstraintViolationException(
			ConstraintViolationException cve) {
		new TratadorConstraintViolationException().tratarExcecao(cve);
	}

	private boolean tratarExcecaoSeExcecaoConhecida(Throwable te,
			ExceptionQueuedEvent event) {
		boolean excecaoTratada = false;

		if (te instanceof ConstraintViolationException) {
			ConstraintViolationException cve = (ConstraintViolationException) te;
			tratarConstraintViolationException(cve);
			excecaoTratada = true;
		} else if (te instanceof ExcecaoNegocio) {
			ExcecaoNegocio exn = (ExcecaoNegocio) te;
			tratarExcecaoNegocio(exn);
			excecaoTratada = true;
		} else if (te instanceof AccessDeniedException) {
			tratarAccessDeniedException(AccessDeniedException.class.cast(te),
					event);
			excecaoTratada = true;
		} else if (te instanceof ExcecaoNegocioRemota) {
			ExcecaoNegocioRemota er = (ExcecaoNegocioRemota) te;
			tratarExcecaoNegocioRemota(er);
			excecaoTratada = true;
		}

		return excecaoTratada;
	}

	private void tratarExcecaoNegocio(ExcecaoNegocio exn) {
		new TratadorExcecaoNegocio().tratarExcecao(exn);
	}

	private void tratarExcecaoNegocioRemota(ExcecaoNegocioRemota er) {
		new TratadorExcecaoNegocioRemota().tratarExcecao(er);
	}

	private void tratarExcecoesNaoPrevistas(FacesContext facesContext,
			List<ExcecaoNaoPrevista> excecoesNaoPrevistas) {
		new TratadorExcecoesNaoPrevistas().tratarExcecoes(facesContext,
				excecoesNaoPrevistas);
	}

}
