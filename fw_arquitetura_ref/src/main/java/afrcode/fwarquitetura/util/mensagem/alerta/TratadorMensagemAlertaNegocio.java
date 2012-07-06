package afrcode.fwarquitetura.util.mensagem.alerta;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import afrcode.fwarquitetura.util.mensagem.TratadorMensagemAbstrato;

/**
 * Classe de implementação do tratador de mensagens do tipo {@link MensagemAlertaNegocio}.
 * 
 * @author alyssonfr
 * 
 */
public class TratadorMensagemAlertaNegocio extends TratadorMensagemAbstrato<MensagemAlertaNegocio> {

    @Override
    public void tratarMensagem(MensagemAlertaNegocio mensagem) {
        Severity severity = FacesMessage.SEVERITY_WARN;
        String msgSummary = mensagem.getCodMensagem() + ":" + mensagem.getMensagem();
        String msg = msgSummary;

        FacesMessage facesMessage = new FacesMessage(severity, msgSummary, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        LOG.info("Mensagem de alerta de negócio obtida e tratada: " + msgSummary);
    }

}
