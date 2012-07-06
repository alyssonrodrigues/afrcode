package projetomvc.sistema.util.jms;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueSender {
	private static final Logger LOG = Logger.getLogger(QueueSender.class);

	private final JmsTemplate jmsTemplate;

	@Autowired
	public QueueSender(final JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void send(final String message) {
		LOG.info("Enviando mensagem via JMS...");
		jmsTemplate.convertAndSend(message);
	}
}
