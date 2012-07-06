package cursojsf.modelo.jms;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class JmsExceptionListener implements ExceptionListener {

	private static final Logger LOG = Logger.getLogger(JmsExceptionListener.class);

	@Override
	public void onException(JMSException exception) {
		LOG.error("Erro: ", exception);
	}

}
