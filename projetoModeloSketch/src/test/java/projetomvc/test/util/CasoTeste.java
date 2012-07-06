package projetomvc.test.util;

import static projetomvc.sistema.spring.util.Profiles.PROFILE_DESENV;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-tu-context.xml" })
@ActiveProfiles(PROFILE_DESENV)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public abstract class CasoTeste {

	/**
	 * Metodo de pre-configuracao para um metodo de TU. Deve ser sobrescrito
	 * quando necessario.
	 */
	@Before
	public void antesDeExecutarMetodoTU() {

	}

	/**
	 * Metodo de configuracao pre-execucao de um metodo de TU.
	 */
	@After
	public void aposExecutarMetodoTU() {

	}

}
