package br.com.afrcode.arquitetura.util.mensagem.info;

import br.com.afrcode.arquitetura.util.mensagem.MensagemNegocioAbstrata;

/**
 * Classe representativa de uma mensagem de informa��o de neg�cio.
 * 
 * 
 */
public class MensagemInfoNegocio extends MensagemNegocioAbstrata {
    private TratadorMensagemInfoNegocio tratador;

    public MensagemInfoNegocio() {
        super();
    }

    public MensagemInfoNegocio(String mensagem) {
        this.setMensagem(mensagem);
    }

    private TratadorMensagemInfoNegocio getTratador() {
        if (tratador == null) {
            tratador = new TratadorMensagemInfoNegocio();
        }
        return tratador;
    }

    @Override
    public void tratarMensagem() {
        getTratador().tratarMensagem(this);
    }

}
