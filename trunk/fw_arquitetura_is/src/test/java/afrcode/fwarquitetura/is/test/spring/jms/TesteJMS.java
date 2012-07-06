package afrcode.fwarquitetura.is.test.spring.jms;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.jms.Queue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import afrcode.fwarquitetura.is.util.jms.QueueSender;
import afrcode.fwarquitetura.tu.util.junit.CasoTesteSemTransacao;

/**
 * Teste unit�rio sobre o arcabou�o m�nimo de envio/recebimento de mensagens via
 * JMS.
 * 
 * Objetivo: enviar mensagens via JMS sem erros.
 * 
 * Observa��es: no momento a mensage recebida � exibida em console apenas.
 * 
 * TODO: Incrementar teste a medida que o arcabou�o de suporte ao JMS evolua.
 * 
 * @author alyssonfr
 * 
 */
public class TesteJMS extends CasoTesteSemTransacao {

    @Autowired
    private QueueSender queueSender;

    @Autowired
    @Qualifier("queueTest")
    private Queue queueTest;

    @Test
    public void testarSendReceiveJms() {
        final String umaMsg = "Uma mensagem de texto via JMS!";
        try {
            queueSender.send(umaMsg, queueTest);
            assertTrue(true);
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }
}
