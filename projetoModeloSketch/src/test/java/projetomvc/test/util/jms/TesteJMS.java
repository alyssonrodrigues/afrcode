package projetomvc.test.util.jms;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import projetomvc.sistema.util.jms.QueueSender;
import projetomvc.test.util.CasoTeste;
import projetomvc.test.util.hsqldb.HSQLDBUtil;

public class TesteJMS extends CasoTeste {

	private QueueSender queueSender;

    @BeforeClass
    public static void iniciarHSQLDB() {
        HSQLDBUtil.getInstancia().iniciarHSQLDB();
    }

    @AfterClass
    public static void pararHSQLDB() {
        HSQLDBUtil.getInstancia().pararHSQLDB();
    }

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
