package afrcode.fwarquitetura.jsf.logging;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

/**
 * Listener de fase JSF 2 respons�vel por finalizar as rotinas de monitoramento de aplica��o.
 * 
 * A partir deste listener tem-se uma chamada remota ao servi�o de Monitoramento do componente Corporativo.
 * 
 * TODO: Integrar ao corporativoIS-api para chamada ao servi�o de Monitoramento.
 * 
 * @author alyssonfr
 * 
 */
public class JSFRenderResponseLoggingListener implements PhaseListener {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(JSFRenderResponseLoggingListener.class);

    @Override
    public void afterPhase(PhaseEvent event) {
        // TODO: integrar ao corporativoIS-api obtendo o bean proxyMonitoramento...
        WebApplicationContext webCtx = FacesContextUtils.getWebApplicationContext(event.getFacesContext());
        if (false) {
            Class<?> monitoramentoProxyClazz = null;
            Object monitoramentoProxy = webCtx.getBean(monitoramentoProxyClazz);
            // TODO: Chamada ao servi�o de monitoramento para demarca��o de fim...
        }
        LOG.info(getPhaseId() + " / SpringCtx " + webCtx.getDisplayName() + ": demarca��o de fim de monitoramento!");
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        // Nada faz.
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

}
