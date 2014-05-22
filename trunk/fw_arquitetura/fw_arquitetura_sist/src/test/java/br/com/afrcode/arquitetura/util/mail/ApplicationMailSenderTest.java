package br.com.afrcode.arquitetura.util.mail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;
import br.com.afrcode.arquitetura.teste.unitario.spring.config.SpringTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestConfig.class)
@ActiveProfiles(Profiles.PROFILE_TU)
public class ApplicationMailSenderTest {

	@Autowired
	private ApplicationMailSender applicationMailSender;

	@Test
	public void testarEnviarEmail() {
		Assert.assertNotNull("MailSender não configurado!",
				applicationMailSender);
	}
}
