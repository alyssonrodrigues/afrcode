package br.com.afrcode.arquitetura.teste.unitario.util.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.afrcode.arquitetura.teste.unitario.spring.config.SpringTestConfig;
import br.com.afrcode.arquitetura.teste.unitario.spring.config.util.ProfilesTU;

/**
 * Classe base para execução de testes unitários, usando o Spring Context, com
 * startup automático do HSQLDB como banco de testes. Ver classe HSQLDBUtil.
 * 
 * Classes de teste unitário que envolvam operações de persistência
 * (JPA/Hibernate) <b>podem</b> ter esta classe como superclasse ou
 * (exclusivamente) a classe AbstractCasoTeste como superclasse.
 * 
 * Classes de teste unitário de serviços remotos <b>não devem</b> usar esta
 * classe como superclasse! Ver classe AbstractCasoTesteSemJpaEJta para maiores
 * informações.
 * 
 * Observações: um contexto transacional (via anotação Transactional) é provido
 * tendo como comportamento default o rollback (ver TransactionConfiguration) ao
 * final de cada método de teste.
 * 
 * Subclasses de AbstractCasoTesteEmMemoria terão como Profile Spring ativo
 * (ActiveProfiles) o PROFILE_TU, onde apenas classes Configuration e/ou classes
 * Component, e subtipos de Component, com Profile PROFILE_TU serão avaliadas em
 * classpath pelo Spring.
 * 
 * 
 */
@RunWith(SpringJUnit4ClassRunnerExtended.class)
@ContextConfiguration(classes = { SpringTestConfig.class })
@ActiveProfiles(ProfilesTU.PROFILE_TU)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractCasoTesteEmMemoria {

	/**
	 * Existindo EJBs 3 no classpath com Interceptors
	 * SpringBeanAutowiringInterceptor um novo contexto Spring será iniciado.
	 * Este por sua vez será distinto e independete do contexto Spring inicial
	 * de testes. Portanto, faz necessário a configuração do profile Spring a
	 * usar. Isto é feito programaticamente por este método.
	 */
	@BeforeClass
	public static void iniciarSpringProfilesParaContextosEJB() {
		System.setProperty("spring.profiles.active", ProfilesTU.PROFILE_TU);
		// Para evitar que o OpenEJB reconfigure o log4j conflitando com threads
		// já em execução (Spring Threads, por exemplo).
		System.setProperty("openejb.logger.external", "true");
	}

	/**
	 * Metodo de pre-configuracao para um metodo de TU. Deve ser sobrescrito
	 * quando necessario.
	 */
	@Before
	public void antesDeExecutarMetodoTU() {
		// Pré-condições de execução de métodos de TU.
	}

	/**
	 * Metodo de configuracao pre-execucao de um metodo de TU.
	 */
	@After
	public void aposExecutarMetodoTU() {
		// Pós-condições de execução de métodos de TU.
	}

}
