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
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;

import br.com.afrcode.arquitetura.is.util.excecao.ExcecaoNegocioRemota;
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
    private static Logger LOG = Logger.getLogger(JSF2ExceptionHandler.class);

    private ExceptionHandler wrapped;

    public JSF2ExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {
        List<ExcecaoNaoPrevista> excecoesNaoPrevistas = new ArrayList<ExcecaoNaoPrevista>();

        for (Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents().iterator(); it.hasNext();) {
            ExceptionQueuedEvent event = it.next();
            ExceptionQueuedEventContext ctx = event.getContext();

            if (ctx.getException() instanceof AbortProcessingException) {
                // Exceções internas ao ciclo de vida do JSF serão tratadas por
                // ele mesmo, não é necessário prosseguir na cadeia
                // de exceções.
                super.handle();
                return;
            }

            Throwable te = obterExpcetionCause(ctx.getException());
            boolean houveAccessDeniedException = te instanceof AccessDeniedException;
            boolean excecaoTratada = false;
            try {
                excecaoTratada = tratarExcecaoSeExcecaoConhecida(te);
            } finally {
                if (excecaoTratada) {
                    // Indicando ao JSF 2 que houve erro de validação.
                    FacesContext.getCurrentInstance().validationFailed();
                    it.remove();
                } else {
                    if (houveAccessDeniedException) {
                        // Houve AccessDeniedException que será tratada pelo
                        // AccessDeniedHandlerImpl posteriormente.
                        LOG.warn(te.getMessage());
                        super.handle();
                    } else {
                        excecoesNaoPrevistas.add(new ExcecaoNaoPrevista(te));
                        it.remove();
                    }
                }
            }

            if (houveAccessDeniedException) {
                // Exceção tratada pelo Spring, não é necessário prosseguir na
                // cadeia de exceções.
                return;
            }
        }

        tratarExcecoesNaoPrevistas(FacesContext.getCurrentInstance(), excecoesNaoPrevistas);
    }

    private Throwable obterExpcetionCause(Throwable exception) {
        Throwable te = exception;
        while (te.getCause() != null) {
            te = te.getCause();
        }
        return te;
    }

    private void tratarConstraintViolationException(ConstraintViolationException cve) {
        new TratadorConstraintViolationException().tratarExcecao(cve);
    }

    private boolean tratarExcecaoSeExcecaoConhecida(Throwable te) {
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
            // Não é necessário nenhum tratamento adicional a partir deste ponto
            // pois o próprio Spring irá tratar a exceção
            // através do AccessDeniedHandlerImpl configurado no
            // SpringSecurityConfig e em spring-security-beans.xml.
            excecaoTratada = false;
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

    private void tratarExcecoesNaoPrevistas(FacesContext facesContext, List<ExcecaoNaoPrevista> excecoesNaoPrevistas) {
        new TratadorExcecoesNaoPrevistas().tratarExcecoes(facesContext, excecoesNaoPrevistas);
    }

}
