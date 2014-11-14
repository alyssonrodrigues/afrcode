package br.com.afrcode.arquitetura.util.mail;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;

public class ApplicationMailSenderTest extends AbstractCasoTesteEmMemoria {

	@Autowired
	private ApplicationMailSender applicationMailSender;

	@Test
	public void testarEnviarEmail() {
		Assert.assertNotNull("MailSender não configurado!",
				applicationMailSender);
	}
}
