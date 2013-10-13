package afrcode.fwarquitetura.util.mensagem.erro;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import afrcode.fwarquitetura.util.mensagem.TratadorMensagemAbstrato;

/**
 * Classe de implementa��o do tratador de mensagens do tipo {@link MensagemErroNegocio}.
 * 
 * @author alyssonfr
 * 
 */
public class TratadorMensagemErroNegocio extends TratadorMensagemAbstrato<MensagemErroNegocio> {

    @Override
    public void tratarMensagem(MensagemErroNegocio mensagem) {
        Severity severity = FacesMessage.SEVERITY_ERROR;
        String msgSummary = mensagem.getCodMensagem() + ":" + mensagem.getMensagem();
        String msg = msgSummary;

        FacesMessage facesMessage = new FacesMessage(severity, msgSummary, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        LOG.info("Mensagem de erro de neg�cio obtida e tratada: " + msgSummary);
    }

}