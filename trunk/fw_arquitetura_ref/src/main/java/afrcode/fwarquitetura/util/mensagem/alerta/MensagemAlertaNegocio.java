package afrcode.fwarquitetura.util.mensagem.alerta;

import afrcode.fwarquitetura.util.mensagem.MensagemNegocioAbstrata;

/**
 * Classe representativa de uma mensagem de alerta de negócio.
 * 
 * @author alyssonfr
 * 
 */
public class MensagemAlertaNegocio extends MensagemNegocioAbstrata {
    private TratadorMensagemAlertaNegocio tratador;

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
