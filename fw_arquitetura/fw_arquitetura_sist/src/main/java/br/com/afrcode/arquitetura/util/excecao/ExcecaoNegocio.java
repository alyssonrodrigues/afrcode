package br.com.afrcode.arquitetura.util.excecao;

import org.apache.commons.lang.Validate;

import br.com.afrcode.arquitetura.util.mensagem.MensagemNegocioAbstrata;

/**
 * Classe base para exceções de negócio (lançadas na camada Model do MVC).
 * 
 * Ver MensagemNegocioAbstrata e seus subtipos.
 * 
 * Em geral estas exceções são conhecidas e tratadas gerando informações e
 * mensagens exibidas ou enviadas ao usuário final.
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
     * Construtor padrão onde deve ser informada a chave de mensagem a exibir ao
     * usuário final.
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
        Validate.notNull(msg, "Uma instância de MensagemErroNegocio deve ser informada!");
    }

    @Override
    public String getMessage() {
        return getMensagem().getMensagem();
    }
}
