package br.com.afrcode.arquitetura.util.excecao;

import org.apache.commons.lang.Validate;

import br.com.afrcode.arquitetura.util.mensagem.MensagemNegocioAbstrata;

/**
 * Classe base para exce��es de neg�cio (lan�adas na camada Model do MVC).
 * 
 * Ver MensagemNegocioAbstrata e seus subtipos.
 * 
 * Em geral estas exce��es s�o conhecidas e tratadas gerando informa��es e
 * mensagens exibidas ou enviadas ao usu�rio final.
 * 
 * 
 */
public class ExcecaoNegocio extends RuntimeException implements IExcecao<MensagemNegocioAbstrata> {

    private static final long serialVersionUID = 1L;

    private MensagemNegocioAbstrata msg;

    public ExcecaoNegocio(MensagemNegocioAbstrata msg, Throwable cause) {
        super(cause);
        validarMsg(msg);
        this.msg = msg;
    }

    /**
     * Construtor padr�o onde deve ser informada a chave de mensagem a exibir ao
     * usu�rio final.
     * 
     * @param msg
     */
    public ExcecaoNegocio(MensagemNegocioAbstrata msg) {
        validarMsg(msg);
        this.msg = msg;
    }

    @Override
    public MensagemNegocioAbstrata getMensagem() {
        return msg;
    }

    private void validarMsg(MensagemNegocioAbstrata msg) {
        Validate.notNull(msg, "Uma inst�ncia de MensagemErroNegocio deve ser informada!");
    }

    @Override
    public String getMessage() {
        return getMensagem().getMensagem();
    }
}
