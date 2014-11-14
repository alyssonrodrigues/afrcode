package br.com.afrcode.arquitetura.spring.config.security;

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;

import br.com.afrcode.arquitetura.util.excecao.ExcecaoNaoPrevista;

/**
 * Componente responsável por tratar erros de acesso negado (erros de
 * autorização).
 * 
 * 
 */
public class AccessDeniedHandlerImpl {
	protected static final Log logger = LogFactory
			.getLog(AccessDeniedHandlerImpl.class);

	private static final String ATRIBUTO_EXCECAO_ACESSO_NEGADO = "excecaoAcessoNegado";

	private static final String MSG_ERRO_AO_REDIRECIONAR = "Não foi possível redirecionar para a página de acesso negado, "
			+ "pois já houve envio de reposta HTML ao usuário!";

	private String errorPage;

	private void configurarRequestResponse(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) {
		request.getSession().setAttribute(ATRIBUTO_EXCECAO_ACESSO_NEGADO,
				getStackTraceComoStringParaWeb(accessDeniedException));
		if (!isAjaxRequest()) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	private String getStackTraceComoStringParaWeb(
			AccessDeniedException accessDeniedException) {
		String stackTraceComoString = ExceptionUtils
				.getFullStackTrace(accessDeniedException);
		return stackTraceComoString.replaceAll("\t", "<br/>");
	}

	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) {
		try {
			if (response.isCommitted()) {
				throw new ExcecaoNaoPrevista(MSG_ERRO_AO_REDIRECIONAR,
						accessDeniedException);
			} else if (errorPage == null) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN,
						accessDeniedException.getMessage());
			} else {
				configurarRequestResponse(request, response,
						accessDeniedException);
				redirect();
			}
		} catch (IOException ex) {
			throw new FacesException(ex);
		}
	}

	private boolean isAjaxRequest() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		boolean ajax = facesContext.getPartialViewContext() != null
				&& facesContext.getPartialViewContext().isAjaxRequest();
		return ajax;
	}

	private void redirect() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext extContext = ctx.getExternalContext();
		String url = extContext.encodeActionURL(ctx.getApplication()
				.getViewHandler().getActionURL(ctx, errorPage));
		try {
			extContext.redirect(url);
		} catch (IOException ex) {
			throw new FacesException(ex);
		}
	}

	/**
	 * The error page to use. Must begin with a "/" and is interpreted relative
	 * to the current context root.
	 * 
	 * @param errorPage
	 *            the dispatcher path to display
	 * 
	 * @throws IllegalArgumentException
	 *             if the argument doesn't comply with the above limitations
	 */
	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && !errorPage.startsWith("/")) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}

		this.errorPage = errorPage;
	}
}
