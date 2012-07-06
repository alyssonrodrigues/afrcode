package afrcode.fwarquitetura.util.mensagem.info;

import afrcode.fwarquitetura.util.mensagem.MensagemNegocioAbstrata;

/**
 * Classe representativa de uma mensagem de informação de negócio.
 * 
 * @author alyssonfr
 * 
 */
public class MensagemInfoNegocio extends MensagemNegocioAbstrata {
    private TratadorMensagemInfoNegocio tratador;

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
