package br.com.afrcode.arquitetura.is.util.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Ouvinte padrão de mensagens recebidas via JMS.
 * 
 * TODO: Flexibilizar uso e recepção da mensagem para componente externo
 * qualquer.
 * 
 * 
 */
@Component
public class QueueListener implements MessageListener {

	private static final Logger LOG = Logger.getLogger(QueueListener.class);

	public QueueListener() {
	}

	@Override
	public void onMessage(Message message) {
		LOG.info("Mensagem recebida via JMS...");
		final TextMessage textMessage = (TextMessage) message;
		try {
			String text = textMessage.getText();
			LOG.info("Conteúdo: " + text);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
