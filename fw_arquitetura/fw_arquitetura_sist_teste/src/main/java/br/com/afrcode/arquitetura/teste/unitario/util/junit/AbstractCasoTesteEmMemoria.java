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
 * Classe base para execu��o de testes unit�rios, usando o Spring Context, com
 * startup autom�tico do HSQLDB como banco de testes. Ver classe HSQLDBUtil.
 * 
 * Classes de teste unit�rio que envolvam opera��es de persist�ncia
 * (JPA/Hibernate) <b>podem</b> ter esta classe como superclasse ou
 * (exclusivamente) a classe AbstractCasoTeste como superclasse.
 * 
 * Classes de teste unit�rio de servi�os remotos <b>n�o devem</b> usar esta
 * classe como superclasse! Ver classe AbstractCasoTesteSemJpaEJta para maiores
 * informa��es.
 * 
 * Observa��es: um contexto transacional (via anota��o Transactional) � provido
 * tendo como comportamento default o rollback (ver TransactionConfiguration) ao
 * final de cada m�todo de teste.
 * 
 * Subclasses de AbstractCasoTesteEmMemoria ter�o como Profile Spring ativo
 * (ActiveProfiles) o PROFILE_TU, onde apenas classes Configuration e/ou classes
 * Component, e subtipos de Component, com Profile PROFILE_TU ser�o avaliadas em
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
	 * SpringBeanAutowiringInterceptor um novo contexto Spring ser� iniciado.
	 * Este por sua vez ser� distinto e independete do contexto Spring inicial
	 * de testes. Portanto, faz necess�rio a configura��o do profile Spring a
	 * usar. Isto � feito programaticamente por este m�todo.
	 */
	@BeforeClass
	public static void iniciarSpringProfilesParaContextosEJB() {
		System.setProperty("spring.profiles.active", ProfilesTU.PROFILE_TU);
		// Para evitar que o OpenEJB reconfigure o log4j conflitando com threads
		// j� em execu��o (Spring Threads, por exemplo).
		System.setProperty("openejb.logger.external", "true");
	}

	/**
	 * Metodo de pre-configuracao para um metodo de TU. Deve ser sobrescrito
	 * quando necessario.
	 */
	@Before
	public void antesDeExecutarMetodoTU() {
		// Pr�-condi��es de execu��o de m�todos de TU.
	}

	/**
	 * Metodo de configuracao pre-execucao de um metodo de TU.
	 */
	@After
	public void aposExecutarMetodoTU() {
		// P�s-condi��es de execu��o de m�todos de TU.
	}

}
