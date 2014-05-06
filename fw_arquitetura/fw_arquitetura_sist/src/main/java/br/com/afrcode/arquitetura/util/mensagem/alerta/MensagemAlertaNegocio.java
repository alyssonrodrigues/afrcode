package br.com.afrcode.arquitetura.util.mensagem.alerta;

import br.com.afrcode.arquitetura.util.mensagem.MensagemNegocioAbstrata;

/**
 * Classe representativa de uma mensagem de alerta de negócio.
 * 
 * 
 */
public class MensagemAlertaNegocio extends MensagemNegocioAbstrata {
    private TratadorMensagemAlertaNegocio tratador;

    public MensagemAlertaNegocio(String mensagem) {
        this.setMensagem(mensagem);
    }

    public MensagemAlertaNegocio() {
        super();
    }

    private TratadorMensagemAlertaNegocio getTratador() {
        if (tratador == null) {
            tratador = new TratadorMensagemAlertaNegocio();
        }
        return tratador;
    }

    @Override
    public void tratarMensagem() {
        getTratador().tratarMensagem(this);
    }

}
