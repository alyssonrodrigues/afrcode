package br.com.afrcode.arquitetura.util.excecao;

import java.io.IOException;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

public class TratadorExcecoesNaoPrevistas implements ITratadorExcecao<Throwable> {

    public static final String MENSAGEM_ERRO_PADRAO = " N�o foi poss�vel finalizar a opera��o. "
            + "Uma mensagem autom�tica" + " foi enviada para a �rea t�cnica respons�vel. "
            + " Caso o problema persista, entre em contato com o Administrador do Sistema"
            + " e informe o ocorrido. O atendente far� o encaminhamento" + " e/ou prioriza��o de sua manifesta��o.";

    private static final String MSG_ERRO_AO_REDIRECIONAR =
            "N�o foi poss�vel redirecionar para a p�gina de erros gen�ricos, "
                    + "pois j� houve envio de reposta HTML ao usu�rio!";

    private static final String ATRIBUTO_EXCECOES_NAO_PREVISTAS = "excecoesNaoPrevistas";

    private static final String ATRIBUTO_MENSAGEM_ERRO = "mensagemErro";

    private static final Logger LOG = Logger.getLogger(TratadorExcecoesNaoPrevistas.class);

    private static final String ERROR_PAGE = "/web/errogenerico.xhtml";

    private void configurarRequestResponse(List<ExcecaoNaoPrevista> excecoes, HttpServletResponse response,
            boolean seAjax) {
        HttpServletRequest request = getHttpServletRequest();
        request.getSession().setAttribute(ATRIBUTO_EXCECOES_NAO_PREVISTAS, excecoes);
        if (!seAjax) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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

    private void redirecionarParaPaginaDeErro(FacesContext ctx, List<ExcecaoNaoPrevista> excecoes) {
        boolean ajax = ctx.getPartialViewContext().isAjaxRequest();
        HttpServletResponse response = getHttpServletResponse();
        if (response.isCommitted()) {
            LOG.error(MSG_ERRO_AO_REDIRECIONAR);
            throw new ExcecaoNaoPrevista(MSG_ERRO_AO_REDIRECIONAR);
        } else {
            configurarRequestResponse(excecoes, response, ajax);
            ExternalContext extContext = ctx.getExternalContext();
            String url =
                    extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, ERROR_PAGE));

            try {
                extContext.redirect(url);
            } catch (IOException e) {
                LOG.error(e);
                throw new FacesException(e);
            }
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

    @Override
    public void tratarExcecao(Throwable excecao) {
        throw new UnsupportedOperationException(
                "O m�todo tratarExcecao n�o deve ser usado! Use o m�todo tratarExcecoes!");
    }

    public void tratarExcecoes(FacesContext facesContext, List<ExcecaoNaoPrevista> excecoes) {
        if (!excecoes.isEmpty()) {
            registrarEmLogExcecoes(excecoes);
            gerarMensagemErro(excecoes);
            redirecionarParaPaginaDeErro(facesContext, excecoes);
        }
    }

}
