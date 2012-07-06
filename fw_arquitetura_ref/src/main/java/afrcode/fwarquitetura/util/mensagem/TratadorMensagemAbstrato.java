package afrcode.fwarquitetura.util.mensagem;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import afrcode.fwarquitetura.util.mensagem.erro.TratadorMensagemErroNegocio;

/**
 * Superclasse para implementações de {@link ITratadorMensagem}.
 * 
 * @author alyssonfr
 * 
 * @param <TIPOMENSAGEM> Subtipo de {@link IMensagem}
 */
public abstract class TratadorMensagemAbstrato<TIPOMENSAGEM extends IMensagem> implements ITratadorMensagem<TIPOMENSAGEM> {
    protected static final Logger LOG = Logger.getLogger(TratadorMensagemErroNegocio.class);

    @Override
    public String getMensagem(TIPOMENSAGEM mensagem) {
        String msg = mensagem.getMensagem();
        if (StringUtils.isBlank(msg)) {
            // TODO: recuperar mensagem de resource bundle...
        }
        return msg;
    }

}
