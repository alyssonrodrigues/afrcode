package br.com.afrcode.arquitetura.is.util.jms;

import javax.jms.Queue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Classe para envio de mensagens via JMS.
 * 
 * TODO: Incluir mecanismos de conversão de mensagens mais elaboradas (objetos,
 * etc.).
 * 
 * 
 */
@Component
public class QueueSender {
	private static final Logger LOG = Logger.getLogger(QueueSender.class);

	private JmsTemplate jmsTemplate;

	public QueueSender() {
	}

	@Autowired
	public QueueSender(final JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void send(final String message, Queue queue) {
		LOG.info("Enviando mensagem via JMS...");
		jmsTemplate.convertAndSend(queue, message);
	}
}
