package br.com.afrcode.arquitetura.util.mensagem.erro;

import br.com.afrcode.arquitetura.util.mensagem.MensagemNegocioAbstrata;

/**
 * Classe representativa de uma mensagem de erro de negócio.
 * 
 * 
 */

public class MensagemErroNegocio extends MensagemNegocioAbstrata {
    private TratadorMensagemErroNegocio tratador;

    public MensagemErroNegocio() {
        super();
    }

    public MensagemErroNegocio(String mensagem) {
        this.setMensagem(mensagem);
    }

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
