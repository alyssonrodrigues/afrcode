package cursojsf.test.modelo.jms;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cursojsf.modelo.jms.QueueSender;
import cursojsf.test.modelo.sistema.CasoTeste;

public class TesteJMS extends CasoTeste {

	private QueueSender queueSender;
	
	@Test
	public void testarSendReceiveJms() {
		final String umaMsg = "Uma mensagem de texto via JMS!";
		try {
		    queueSender.send(umaMsg);
		    assertTrue(true);
		    
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}
	
	@Autowired
	public void setQueueSender(QueueSender queueSender) {
		this.queueSender = queueSender;
	}

}
