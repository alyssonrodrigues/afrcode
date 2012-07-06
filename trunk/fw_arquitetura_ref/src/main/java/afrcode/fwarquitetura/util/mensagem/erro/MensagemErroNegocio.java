package afrcode.fwarquitetura.util.mensagem.erro;

import afrcode.fwarquitetura.util.mensagem.MensagemNegocioAbstrata;

/**
 * Classe representativa de uma mensagem de erro de negócio.
 * 
 * @author alyssonfr
 * 
 */
public class MensagemErroNegocio extends MensagemNegocioAbstrata {
    private TratadorMensagemErroNegocio tratador;

    private TratadorMensagemErroNegocio getTratador() {
        if (tratador == null) {
            tratador = new TratadorMensagemErroNegocio();
        }
        return tratador;
    }

    @Override
    public void tratarMensagem() {
        getTratador().tratarMensagem(this);
    }

}
