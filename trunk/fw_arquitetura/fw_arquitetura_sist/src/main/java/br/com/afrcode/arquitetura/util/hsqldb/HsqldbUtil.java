package br.com.afrcode.arquitetura.util.hsqldb;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hsqldb.Server;
import org.hsqldb.server.ServerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;
import br.com.afrcode.arquitetura.util.contexto.ApplicationContextUtils;
import br.com.afrcode.arquitetura.util.excecao.ExcecaoNaoPrevista;

/**
 * Classe utilit�ria para startup e shutdown do SGBD HSQLDB.
 * 
 * Startup e shutdown associados do SGBD HSQLDB associados ao startup e shutdown
 * do contexto Spring.
 * 
 * Para testes unit�rios, em caso de indisponibilidade de porta para execu��o do
 * HSQLDB elege-se outra aleatoriamente em uma segunda tentativa de startup.
 * 
 * Para contexto Spring exclusivos de EJBs o HSQLDB n�o � iniciado assumindo-se
 * que o EJB far� uso do HSQLDB anteriormente iniciado pelos contextos Spring de
 * testes ou de aplica��o.
 * 
 */
@Component
public class HsqldbUtil {
	private static final String MSG_FALHA_STARTUP_HSQLDB_DEVIDO_A_OUTRA_INSTANCIA_EM_EXECUCAO = "Poss�vel falha de startup de HSQLDB devido a outra inst�ncia de HSQLDB j� em execu��o!";

	private static final String PROP_EXECUTANDO_TESTE_FUNCIONAL = "executandoTesteFuncional";

	private static final String PROP_EXECUTANDO_TESTE_INTEGRACAO = "executandoTesteIntegracao";

	private static final String PROP_EXECUTANDO_POVOADOR_STAND_ALONE = "executandoPovoadorStandAlone";

	private static final String DATABASE_NAME_PADRAO = "testdb";

	private static final Logger LOG = Logger.getLogger(HsqldbUtil.class);

	private static final String HIBERNATE_DIALECT = "hibernate.dialect";

	private static final String HIBERNATE_DIALECT_TU = "hibernate.dialect_tu";

	@Value("${" + HIBERNATE_DIALECT + "}")
	private String hibernateDialect;

	@Value("${" + HIBERNATE_DIALECT_TU + "}")
	private String hibernateDialectTU;

	@Autowired
	private ApplicationContext applicationContext;

	private Server hsqldbServer;

	private int serverPort = ServerConstants.SC_DEFAULT_HSQL_SERVER_PORT;

	private void configurarHSQLDB(int port) {
		hsqldbServer = new Server();
		hsqldbServer.setLogWriter(null);
		String databaseName = getDatabaseName();
		hsqldbServer.setDatabaseName(0, databaseName);
		hsqldbServer.setDatabasePath(0, "mem:" + databaseName);
		if (ServerConstants.SC_DEFAULT_HSQL_SERVER_PORT != port) {
			serverPort = port;
			hsqldbServer.setPort(serverPort);
		}
	}

	private void doIniciarHSQLDB() {
		int state = -1;
		try {
			LOG.info("Iniciando HSQLDB em mem�ria...");
			configurarHSQLDB(serverPort);
			hsqldbServer.start();
			state = hsqldbServer.getState();
			state = iniciarHSQLDBParaTUsEmNovaPortaSeNecessario(state);

			if (ServerConstants.SERVER_STATE_ONLINE == state) {
				LOG.info("HSQLDB em mem�ria em execu��o na porta: "
						+ getServerPort());
			} else if (isProfileTUAtivo()) {
				throw new ExcecaoNaoPrevista(
						"N�o foi poss�vel iniciar o HSQLDB por poss�vel falha de startup "
								+ "devido a outra(s) inst�ncia(s) de HSQLDB j� em execu��o!");
			} else {
				LOG.warn(MSG_FALHA_STARTUP_HSQLDB_DEVIDO_A_OUTRA_INSTANCIA_EM_EXECUCAO);
			}
		} catch (Exception e) {
			if (ServerConstants.SERVER_STATE_SHUTDOWN != state) {
				hsqldbServer.stop();
			}
			LOG.warn(e);
		}
	}

	private void doPararHSQLDB() {
		try {
			LOG.info("Finalizando HSQLDB em mem�ria na porta: "
					+ getServerPort());
			hsqldbServer.stop();
			LOG.info("HSQLDB em mem�ria finalizado.");
		} catch (Exception e) {
			LOG.warn(e);
		}
	}

	private String getDatabaseName() {
		String databaseName = DATABASE_NAME_PADRAO;
		try {
			Resource resource = applicationContext
					.getResource("classpath:hibernate-jpaProperties.properties");
			if (resource != null) {
				Properties hibernateProperties = PropertiesLoaderUtils
						.loadProperties(resource);
				String url = (String) hibernateProperties
						.get("hibernate.connection.url");
				if (url != null) {
					databaseName = url.substring(url.lastIndexOf("/") + 1);
				}
			}
			LOG.info("HSQLDB databaseName: " + databaseName);
		} catch (IOException e) {
			LOG.warn("Arquivo hibernate-jpaProperties.properties n�o presente em classpath. "
					+ "O HSQLDB ir� iniciar o BD \"testedb\" padr�o!");
		}
		return databaseName;
	}

	/**
	 * @return the serverPort
	 */
	public int getServerPort() {
		return serverPort;
	}

	@PostConstruct
	public Server iniciarHSQLDB() {
		if (isExecutandoPovoadorStandAlone() || isExecutandoTesteFuncional()
				|| isExecutandoTesteIntegracao()
				|| isEjbSpringApplicationContext()) {
			LOG.info("Execu��o de testes de integra��o, povoador ou contexto EJB detectados."
					+ "O SGBD HSQLDB n�o ser� iniciado automaticamente!");
		} else if (isHsqldbEmUsoParaTU() || isHsqldbEmUsoParaAplicacao()) {
			// Startup de HSQLDB para testes unit�rios ou para uso pela pr�pria
			// aplica��o.
			doIniciarHSQLDB();
		}

		return hsqldbServer;
	}

	private int iniciarHSQLDBParaTUsEmNovaPortaSeNecessario(int state) {
		if (isProfileTUAtivo()
				&& ServerConstants.SERVER_STATE_SHUTDOWN == state) {
			LOG.info(MSG_FALHA_STARTUP_HSQLDB_DEVIDO_A_OUTRA_INSTANCIA_EM_EXECUCAO);

			Random random = new Random(this.hashCode());
			// Nova porta = 9001 + (1,2,3..., 100)
			final int cem = 100;
			final int um = 1;
			int port = ServerConstants.SC_DEFAULT_HSQL_SERVER_PORT
					+ random.nextInt(cem) + um;

			LOG.info("Nova tentativa de startup de HSQLDB em nova porta: "
					+ port);
			configurarHSQLDB(port);
			hsqldbServer.start();
			state = hsqldbServer.getState();
		}
		return state;
	}

	public boolean isEjbSpringApplicationContext() {
		return ApplicationContextUtils
				.isEjbSpringApplicationContext(applicationContext);
	}

	public Boolean isExecutandoPovoadorStandAlone() {
		String property = System
				.getProperty(PROP_EXECUTANDO_POVOADOR_STAND_ALONE);
		return StringUtils.isNotBlank(property) ? Boolean.valueOf(property)
				: false;
	}

	public Boolean isExecutandoTesteFuncional() {
		String property = System.getProperty(PROP_EXECUTANDO_TESTE_FUNCIONAL);
		return StringUtils.isNotBlank(property) ? Boolean.valueOf(property)
				: false;
	}

	public Boolean isExecutandoTesteIntegracao() {
		String property = System.getProperty(PROP_EXECUTANDO_TESTE_INTEGRACAO);
		return StringUtils.isNotBlank(property) ? Boolean.valueOf(property)
				: false;
	}

	private boolean isHsqldbEmUsoParaAplicacao() {
		final String HSQLDB_DIALECT = "org.hibernate.dialect.HSQLDialect";
		boolean hsqldbEmUsoParaAplicacao = StringUtils
				.isNotBlank(hibernateDialect) ? hibernateDialect
				.equals(HSQLDB_DIALECT) : false;
		return isProfileAplicacaoAtivo() && hsqldbEmUsoParaAplicacao;
	}

	public boolean isHsqldbEmUsoParaTU() {
		final String HSQLDB_DIALECT = "org.hibernate.dialect.HSQLDialect";
		boolean hsqldbEmUsoParaTU = StringUtils.isNotBlank(hibernateDialectTU) ? hibernateDialectTU
				.equals(HSQLDB_DIALECT) : false;
		return isProfileTUAtivo() && hsqldbEmUsoParaTU;
	}

	private boolean isProfileAplicacaoAtivo() {
		List<String> activeProfiles = Arrays.asList(applicationContext
				.getEnvironment().getActiveProfiles());
		return activeProfiles.contains(Profiles.PROFILE_APLICACAO);
	}

	private boolean isProfileTUAtivo() {
		List<String> activeProfiles = Arrays.asList(applicationContext
				.getEnvironment().getActiveProfiles());
		return activeProfiles.contains(Profiles.PROFILE_TU);
	}

	public boolean isServerPortDefault() {
		return ServerConstants.SC_DEFAULT_HSQL_SERVER_PORT == getServerPort();
	}

	@PreDestroy
	public void pararHSQLDB() {
		if (isExecutandoPovoadorStandAlone() || isExecutandoTesteFuncional()
				|| isExecutandoTesteIntegracao()
				|| isEjbSpringApplicationContext()) {
			LOG.info("Execu��o de testes de integra��o, povoador ou contexto EJB detectados."
					+ "O SGBD HSQLDB n�o ser� finalizado automaticamente!");
		} else if (isHsqldbEmUsoParaTU() || isHsqldbEmUsoParaAplicacao()) {
			// Shutdown de HSQLDB para testes unit�rios ou para uso pela pr�pria
			// aplica��o.
			doPararHSQLDB();
		}
	}
}
