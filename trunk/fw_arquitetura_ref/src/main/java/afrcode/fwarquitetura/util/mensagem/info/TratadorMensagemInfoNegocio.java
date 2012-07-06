package afrcode.fwarquitetura.util.mensagem.info;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import afrcode.fwarquitetura.util.mensagem.TratadorMensagemAbstrato;

/**
 * Classe de implementa��o do tratador de mensagens do tipo {@link MensagemInfoNegocio}.
 * 
 * @author alyssonfr
 * 
 */
public class TratadorMensagemInfoNegocio extends TratadorMensagemAbstrato<MensagemInfoNegocio> {

    @Override
    public void tratarMensagem(MensagemInfoNegocio mensagem) {
        Severity severity = FacesMessage.SEVERITY_INFO;
        String msgSummary = mensagem.getCodMensagem() + ":" + mensagem.getMensagem();
        String msg = msgSummary;

        FacesMessage facesMessage = new FacesMessage(severity, msgSummary, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        LOG.info("Mensagem de informa��o de neg�cio obtida e tratada: " + msgSummary);
    }

}
