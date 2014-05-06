package br.com.afrcode.arquitetura.spring.config.security;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import br.com.afrcode.arquitetura.util.excecao.ExcecaoNaoPrevista;

/**
 * Componente responsável por tratar erros de acesso negado (erros de autorização).
 * 
 * 
 */
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    protected static final Log logger = LogFactory.getLog(AccessDeniedHandlerImpl.class);

    private static final String ATRIBUTO_EXCECAO_ACESSO_NEGADO = "excecaoAcessoNegado";

    private static final String MSG_ERRO_AO_REDIRECIONAR = "Não foi possível redirecionar para a página de acesso negado, " +
            "pois já houve envio de reposta HTML ao usuário!";

    private String errorPage;

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (facesContext != null
                && facesContext.getPartialViewContext() != null
                && facesContext.getPartialViewContext().isAjaxRequest()) {
            handleRequisicaoAjax(request, response, accessDeniedException);
        } else {
            handleRequisicaoNaoAjax(request, response, accessDeniedException);
        }
    }

    private void handleRequisicaoAjax(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) {

        if (response.isCommitted()) {
            throw new ExcecaoNaoPrevista(MSG_ERRO_AO_REDIRECIONAR, accessDeniedException);
        } else {
            configurarRequestResponse(request, response, accessDeniedException);
        }
    }

    private void handleRequisicaoNaoAjax(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException {
        if (response.isCommitted()) {
            throw new ExcecaoNaoPrevista(MSG_ERRO_AO_REDIRECIONAR, accessDeniedException);
        } else {
            if (errorPage == null) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
            } else {
                configurarRequestResponse(request, response, accessDeniedException);

                String urlErroPage = request.getContextPath() + errorPage;
                response.sendRedirect(urlErroPage);
            }
        }
    }

    private void configurarRequestResponse(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) {
        request.getSession().setAttribute(ATRIBUTO_EXCECAO_ACESSO_NEGADO,
                getStackTraceComoStringParaWeb(accessDeniedException));
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    private String getStackTraceComoStringParaWeb(AccessDeniedException accessDeniedException) {
        String stackTraceComoString = ExceptionUtils.getFullStackTrace(accessDeniedException);
        return stackTraceComoString.replaceAll("\t", "<br/>");
    }

    /**
     * The error page to use. Must begin with a "/" and is interpreted relative to the current context root.
     * 
     * @param errorPage the dispatcher path to display
     * 
     * @throws IllegalArgumentException if the argument doesn't comply with the above limitations
     */
    public void setErrorPage(String errorPage) {
        if ((errorPage != null) && !errorPage.startsWith("/")) {
            throw new IllegalArgumentException("errorPage must begin with '/'");
        }

        this.errorPage = errorPage;
    }
}
