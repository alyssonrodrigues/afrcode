package afrcode.fwarquitetura.jsf.logging;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

/**
 * Listener de fase JSF 2 responsável por iniciar as rotinas de monitoramento de aplicação.
 * 
 * A partir deste listener tem-se uma chamada remota ao serviço de Monitoramento do componente Corporativo.
 * 
 * TODO: Integrar ao corporativoIS-api para chamada ao serviço de Monitoramento.
 * 
 * @author alyssonfr
 * 
 */
public class JSFRestoreViewLoggingListener implements PhaseListener {
    private static final Logger LOG = Logger.getLogger(JSFRestoreViewLoggingListener.class);

    private static final long serialVersionUID = 1L;

    @Override
    public void afterPhase(PhaseEvent event) {
        // Nada faz.
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        // TODO: integrar ao corporativoIS-api obtendo o bean proxyMonitoramento...
        WebApplicationContext webCtx = FacesContextUtils.getWebApplicationContext(event.getFacesContext());
        if (false) {
            Class<?> monitoramentoProxyClazz = null;
            Object monitoramentoProxy = webCtx.getBean(monitoramentoProxyClazz);
            // TODO: Chamada ao serviço de monitoramento para demarcação de início...
        }
        LOG.info(getPhaseId() + " / SpringCtx " + webCtx.getDisplayName() + ": demarcação de início de monitoramento!");
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
