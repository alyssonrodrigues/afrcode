package br.com.afrcode.arquitetura.util.testefuncional;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;
import br.com.afrcode.arquitetura.teste.unitario.spring.config.SpringTestConfig;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.SpringJUnit4ClassRunnerExtended;

/**
 * Classe base para execução de testes funcionais, usando o Spring Context, SEM
 * startup automático de banco de testes.
 * 
 * Classes de testes funcionais que envolvam operações de persistência
 * (JPA/Hibernate) <b>devem</b> ter esta classe como superclasse.
 * 
 * Observações: O contexto transacional dos testes funcionais é isolado em
 * relação ao da aplicação onde os testes são executados.<br>
 * Por isto, usa-se TransactionConfiguration#defaultRollback() == false.
 * 
 * Para criação de massa de dados de testes é necessário fazer uso do
 * TransactionTemplate programaticamente.
 * 
 * Subclasses de CasoTesteFuncional terão como Profile Spring ativo
 * (ActiveProfiles) o Profiles#PROFILE_TU, onde apenas classes Configuration
 * e/ou classes Component, e subtipos de Component, com Profile
 * Profiles#PROFILE_TU serão avaliadas em classpath pelo Spring.
 * 
 * 
 */
@RunWith(SpringJUnit4ClassRunnerExtended.class)
@ContextConfiguration(classes = { SpringTestConfig.class })
// Configuração para execução de testes de integração somente se houver a
// System.property[executandoTesteFuncional]
// (-DexecutandoTesteFuncional=true) com valor igual a true.
@IfProfileValue(name = "executandoTesteFuncional", value = "true")
@ActiveProfiles(Profiles.PROFILE_TU)
@TransactionConfiguration(defaultRollback = false)
public abstract class CasoTesteFuncional {

	@BeforeClass
	public static void iniciarSpringProfilesParaWebDriver() {
		// SystemProperty para inibir startup/shutdown do HSQLDB. Ver
		// HsqldbUtil.
		System.setProperty("executandoTesteFuncional", "true");
		System.setProperty("spring.profiles.active", Profiles.PROFILE_TU);
	}

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
