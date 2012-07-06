package cursojsf.fronteira;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.log4j.Logger;

public class MyJSFPhaseListener implements PhaseListener {
	private static final long serialVersionUID = 7430920944416983153L;
	private static final Logger LOG = 
		Logger.getLogger(MyJSFPhaseListener.class);

	@Override
	public void afterPhase(PhaseEvent event) {
		LOG.info("after phase: " + event.getPhaseId());
		FacesContext facesContext = event.getFacesContext();
		LOG.info("renderResponse: " + facesContext.getRenderResponse());
		LOG.info("responseComplete: " + facesContext.getResponseComplete());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		LOG.info("before phase: " + event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.INVOKE_APPLICATION;
	}

}
