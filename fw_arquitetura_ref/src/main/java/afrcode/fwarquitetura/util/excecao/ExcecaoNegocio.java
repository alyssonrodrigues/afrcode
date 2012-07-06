package afrcode.fwarquitetura.util.excecao;

import org.apache.commons.lang.Validate;

import afrcode.fwarquitetura.util.mensagem.MensagemNegocioAbstrata;

/**
 * Classe base para exce��es de neg�cio (lan�adas na camada Model do MVC).
 * 
 * Ver {@link MensagemNegocioAbstrata} e seus subtipos.
 * 
 * Em geral estas exce��es s�o conhecidas e tratadas gerando informa��es e mensagens exibidas ou enviadas ao usu�rio final.
 * 
 * @author alyssonfr
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

    private void validarMsg(MensagemNegocioAbstrata msg) {
        Validate.notNull(msg, "Uma inst�ncia de MensagemErroNegocio deve ser informada!");
    }

    /**
     * Construtor padr�o onde deve ser informada a chave de mensagem a exibir ao usu�rio final.
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

}
