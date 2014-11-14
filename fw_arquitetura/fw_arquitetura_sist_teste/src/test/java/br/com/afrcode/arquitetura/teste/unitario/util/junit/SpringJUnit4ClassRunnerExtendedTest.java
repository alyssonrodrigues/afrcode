package br.com.afrcode.arquitetura.teste.unitario.util.junit;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import br.com.afrcode.arquitetura.teste.unitario.spring.config.SpringTestConfig;
import br.com.afrcode.arquitetura.teste.unitario.spring.config.util.ProfilesTU;

/**
 * Classe de testes simples sem contexto transacional envolvido. Em uso para
 * validar o bootstrapping do Spring, ou seja, carga de classes @Configuration e
 * diretivas associadas.
 * 
 * 
 */
@RunWith(SpringJUnit4ClassRunnerExtended.class)
@ContextConfiguration(classes = { SpringTestConfig.class })
@ActiveProfiles(ProfilesTU.PROFILE_TU)
public class SpringJUnit4ClassRunnerExtendedTest {
	private static int numExecucoes = 0;
	private static final int NUM_MAX_EXECUCOES = 2;
	private static final Logger LOG = Logger
			.getLogger(SpringJUnit4ClassRunnerExtendedTest.class);

	@Test
	@Repeat(NUM_MAX_EXECUCOES)
	public void testarSpringTURepeatAnnotation() {
		// Ver verificarNumExecucoes()...
		LOG.info("testarSpringTURepeatAnnotation" + "@Repeat[" + ++numExecucoes
				+ "]");
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			// Nada faz.
		}
	}

	@AfterClass
	public static void verificarNumExecucoes() {
		Assert.assertEquals(
				"O método testarSpringTURepeatAnnotation deveria ter sido executado "
						+ NUM_MAX_EXECUCOES + " vezes!", NUM_MAX_EXECUCOES,
				numExecucoes);
		numExecucoes = 0;
	}

}
