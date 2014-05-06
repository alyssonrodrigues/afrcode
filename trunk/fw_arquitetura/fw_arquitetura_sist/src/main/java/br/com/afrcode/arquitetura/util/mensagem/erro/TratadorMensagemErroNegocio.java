package br.com.afrcode.arquitetura.util.mensagem.erro;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.com.afrcode.arquitetura.util.mensagem.TratadorMensagemAbstrato;

/**
 * Classe de implementação do tratador de mensagens do tipo MensagemErroNegocio.
 * 
 * 
 */
public class TratadorMensagemErroNegocio extends TratadorMensagemAbstrato<MensagemErroNegocio> {

    @Override
    public void tratarMensagem(MensagemErroNegocio mensagem) {
        Severity severity = FacesMessage.SEVERITY_ERROR;
        String msgSummary = mensagem.getMensagem();
        String msg = msgSummary;

        FacesMessage facesMessage = new FacesMessage(severity, msgSummary, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        LOG.info("Mensagem de erro de negócio obtida e tratada: " + msgSummary);
    }

}
