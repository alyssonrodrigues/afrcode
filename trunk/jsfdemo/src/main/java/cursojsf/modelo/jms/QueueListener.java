package cursojsf.modelo.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class QueueListener implements MessageListener {

	private static final Logger LOG = Logger.getLogger(QueueListener.class);

	@Override
	public void onMessage(Message message) {
		LOG.info("Mensagem recebida via JMS...");
		final TextMessage textMessage = (TextMessage) message;
		try {
			String text = textMessage.getText();
			LOG.info("Conteúdo: " + text);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

}
