package br.com.afrcode.apps.egos.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.afrcode.apps.egos.spring.config.BeansSpringTestesConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeansSpringTestesConfig.class)
@ActiveProfiles("TESTES")
public class ApplicationMailSenderTest {
	
	@Autowired
	private ApplicationMailSender applicationMailSender;

	@Test
	public void testarEnviarEmail() {
		applicationMailSender.enviarEmail("alysson.rodrigues@gmail.com",
				"alysson.rodrigues@gmail.com",
				new String[0],
				"Feedback sobre o curso de Spring Framework",
				"Muito legal! Obrigado! :)");
	}
}
