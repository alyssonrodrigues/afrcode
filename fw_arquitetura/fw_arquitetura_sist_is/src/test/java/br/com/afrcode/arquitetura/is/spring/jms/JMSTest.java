package br.com.afrcode.arquitetura.is.spring.jms;

import javax.jms.Queue;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.com.afrcode.arquitetura.is.util.jms.QueueSender;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteSemJpaEJta;

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
 * 
 */
public class JMSTest extends AbstractCasoTesteSemJpaEJta {

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
			Assert.assertTrue(true);
		} catch (Throwable e) {
			Assert.fail(e.getMessage());
		}
	}

}
