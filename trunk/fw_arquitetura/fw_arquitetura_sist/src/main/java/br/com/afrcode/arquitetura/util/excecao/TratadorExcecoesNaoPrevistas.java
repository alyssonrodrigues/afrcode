package br.com.afrcode.arquitetura.util.excecao;

import java.io.IOException;
import java.util.List;

import javax.faces.FactoryFinder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

public class TratadorExcecoesNaoPrevistas implements ITratadorExcecao<Throwable> {

    public static final String MENSAGEM_ERRO_PADRAO = " Não foi possível finalizar a operação. "
            + "Uma mensagem automática" + " foi enviada para a área técnica responsável. "
            + " Caso o problema persista, entre em contato com o Administrador do Sistema"
            + " e informe o ocorrido. O atendente fará o encaminhamento" + " e/ou priorização de sua manifestação.";

    private static final String MSG_ERRO_AO_REDIRECIONAR =
            "Não foi possível redirecionar para a página de erros genéricos, "
                    + "pois já houve envio de reposta HTML ao usuário!";

    private static final String ATRIBUTO_EXCECOES_NAO_PREVISTAS = "excecoesNaoPrevistas";

    private static final String ATRIBUTO_MENSAGEM_ERRO = "mensagemErro";

    private static final Logger LOG = Logger.getLogger(TratadorExcecoesNaoPrevistas.class);

    private static final String ERROR_PAGE = "/web/errogenerico.xhtml";

    public void tratarExcecoes(FacesContext facesContext, List<ExcecaoNaoPrevista> excecoes) {
        if (!excecoes.isEmpty()) {
            registrarEmLogExcecoes(excecoes);
            gerarMensagemErro(excecoes);
            redirecionarParaPaginaDeErro(facesContext, excecoes);
        }
    }

    private void registrarEmLogExcecoes(List<ExcecaoNaoPrevista> excecoes) {
        StringBuffer sb = new StringBuffer();
        for (ExcecaoNaoPrevista excecaoNaoPrevista : excecoes) {
            sb.append(ExceptionUtils.getFullStackTrace(excecaoNaoPrevista));
            sb.append("\\n");
        }
        LOG.error(sb.toString());
    }

    private void redirecionarParaPaginaDeErro(FacesContext facesContext, List<ExcecaoNaoPrevista> excecoes) {
        if (facesContext.getPartialViewContext().isAjaxRequest()) {
            redirecionarParaPaginaDeErroEmRequisicaoAjax(facesContext, excecoes);
        } else {
            redirecionarParaPaginaDeErroEmRequisicaoNaoAjax(excecoes);
        }
    }

    private void redirecionarParaPaginaDeErroEmRequisicaoAjax(FacesContext facesContext,
            List<ExcecaoNaoPrevista> excecoes) {
        HttpServletResponse response = getHttpServletResponse();

        if (response.isCommitted()) {
            LOG.error(MSG_ERRO_AO_REDIRECIONAR);
            throw new ExcecaoNaoPrevista(MSG_ERRO_AO_REDIRECIONAR);
        } else {
            configurarRequestResponse(excecoes, response, true);

            HttpServletRequest request = getHttpServletRequest();
            String urlErroPage = request.getContextPath() + ERROR_PAGE;
            ExternalContext ec = facesContext.getExternalContext();

            try {
                if ((facesContext.getPartialViewContext().isPartialRequest())
                        && facesContext.getResponseWriter() == null && facesContext.getRenderKit() == null) {
                    response.setCharacterEncoding(request.getCharacterEncoding());

                    RenderKitFactory factory =
                            (RenderKitFactory) FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);

                    RenderKit renderKit =
                            factory.getRenderKit(facesContext, facesContext.getApplication().getViewHandler()
                                    .calculateRenderKitId(facesContext));

                    ResponseWriter responseWriter =
                            renderKit.createResponseWriter(response.getWriter(), null, request.getCharacterEncoding());
                    facesContext.setResponseWriter(responseWriter);
                }

                ec.redirect(urlErroPage);
            } catch (IOException e) {
                LOG.error(MSG_ERRO_AO_REDIRECIONAR);
            }
        }
    }

    private void configurarRequestResponse(List<ExcecaoNaoPrevista> excecoes, HttpServletResponse response,
            boolean seAjax) {
        HttpServletRequest request = getHttpServletRequest();
        request.getSession().setAttribute(ATRIBUTO_EXCECOES_NAO_PREVISTAS, excecoes);
        if (!seAjax) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void redirecionarParaPaginaDeErroEmRequisicaoNaoAjax(List<ExcecaoNaoPrevista> excecoes) {
        HttpServletResponse response = getHttpServletResponse();
        if (response.isCommitted()) {
            LOG.error(MSG_ERRO_AO_REDIRECIONAR);
            throw new ExcecaoNaoPrevista(MSG_ERRO_AO_REDIRECIONAR);
        } else {
            configurarRequestResponse(excecoes, response, false);

            try {
                HttpServletRequest request = getHttpServletRequest();
                String urlErroPage = request.getContextPath() + ERROR_PAGE;
                response.sendRedirect(urlErroPage);
            } catch (IOException e) {
                LOG.error(e);
                throw new ExcecaoNaoPrevista(e);
            }
        }
    }

    private void gerarMensagemErro(List<ExcecaoNaoPrevista> excecoes) {
        HttpServletRequest request = getHttpServletRequest();
        request.getSession().setAttribute(ATRIBUTO_MENSAGEM_ERRO, MENSAGEM_ERRO_PADRAO);
    }

    private HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    private HttpServletResponse getHttpServletResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    @Override
    public void tratarExcecao(Throwable excecao) {
        throw new UnsupportedOperationException(
                "O método tratarExcecao não deve ser usado! Use o método tratarExcecoes!");
    }

}
