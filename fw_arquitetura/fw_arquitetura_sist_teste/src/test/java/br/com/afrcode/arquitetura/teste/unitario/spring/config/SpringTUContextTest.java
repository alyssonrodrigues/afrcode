package br.com.afrcode.arquitetura.teste.unitario.spring.config;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import br.com.afrcode.arquitetura.teste.unitario.spring.config.util.ProfilesTU;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteSemJpaEJta;

/**
 * Classe de testes simples sem contexto transacional envolvido. Em uso para
 * validar o bootstrapping do Spring, ou seja, carga de classes @Configuration e
 * diretivas associadas.
 * 
 * 
 */
public class SpringTUContextTest extends AbstractCasoTesteSemJpaEJta {

	@Autowired
	private StopWatch stopWatch;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testarSpringTUContext() {
		try {
			Assert.assertNotNull(
					"Spring applicationContext não deveria ser nulo!",
					applicationContext);
			Assert.assertEquals("PROFILE_TU deveria ser o profile ativo!",
					ProfilesTU.PROFILE_TU, applicationContext.getEnvironment()
							.getActiveProfiles()[0]);
			Assert.assertNotNull("stopWatch não deveria ser nulo!", stopWatch);
		} catch (Throwable e) {
			Assert.fail("Erro ao iniciar contexto Spring!");
		}
	}

}
