package afrcode.fwarquitetura.tu.util.hsqldb;

import static afrcode.fwarquitetura.tu.spring.config.util.ProfilesTU.PROFILE_TESTES;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hsqldb.Server;
import org.hsqldb.server.ServerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

/**
 * Classe utilit�ria para startup e shutdown do SGBD HSQLDB.
 * 
 * @author alyssonfr
 * 
 */
@Component
@Profile(PROFILE_TESTES)
public class HSQLDBUtil {
    private static final String PROP_EXECUTANDO_TESTE_FUNCIONAL = "executandoTesteFuncional";

    private static final String PROP_EXECUTANDO_POVOADOR_STAND_ALONE = "executandoPovoadorStandAlone";

    private static final String DATABASE_NAME_PADRAO = "testdb";

    private static final Logger LOG = Logger.getLogger(HSQLDBUtil.class);

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

    private String getDatabaseName() {
        String databaseName = DATABASE_NAME_PADRAO;
        try {
            Resource resource = applicationContext.getResource("classpath:hibernate-jpaProperties.properties");
            if (resource != null) {
                Properties hibernateProperties = PropertiesLoaderUtils.loadProperties(resource);
                String url = (String) hibernateProperties.get("hibernate.connection.url");
                if (url != null) {
                    databaseName = url.substring(url.lastIndexOf("/") + 1);
                }
            }
            LOG.info("HSQLDB databaseName: " + databaseName);
        } catch (IOException e) {
            LOG.warn("Arquivo hibernate-jpaProperties.properties n�o presente em classpath. " +
                    "O HSQLDB ir� iniciar o BD \"testedb\" padr�o!");
        }
        return databaseName;
    }

    @PostConstruct
    public Server iniciarHSQLDB() {
        if (!isExecutandoPovoadorStandAlone() && !isExecutandoTesteFuncional()) {
            doIniciarHSQLDB();
        } else {
            LOG.info("Execu��o de povoador standalone detectada. O SGBD em uso dever� ser iniciado manualmente pelo desenvolvedor!");
        }

        return hsqldbServer;
    }

    private void doIniciarHSQLDB() {
        int state = -1;
        try {
            LOG.info("Iniciando HSQLDB em mem�ria...");
            configurarHSQLDB(serverPort);
            hsqldbServer.start();
            state = hsqldbServer.getState();
            state = iniciarHSQLDBEmNovaPortaSeNecessario(state);

            if (ServerConstants.SERVER_STATE_ONLINE == state) {
                LOG.info("HSQLDB em mem�ria em execu��o na porta: " + getServerPort());
            } else {
                throw new RuntimeException("N�o foi poss�vel iniciar o HSQLDB por poss�vel falha de startup " +
                        "devido a outra(s) inst�ncia(s) de HSQLDB j� em execu��o!");
            }
        } catch (Throwable e) {
            if (ServerConstants.SERVER_STATE_SHUTDOWN != state) {
                hsqldbServer.stop();
            }
            LOG.warn(e);
        }
    }

    private int iniciarHSQLDBEmNovaPortaSeNecessario(int state) {
        if (ServerConstants.SERVER_STATE_SHUTDOWN == state) {
            LOG.info("Poss�vel falha de startup de HSQLDB devido a outra inst�ncia de HSQLDB j� em execu��o!");

            Random random = new Random(this.hashCode());
            // Nova porta = 9001 + (1,2,3..., 100)
            int port = ServerConstants.SC_DEFAULT_HSQL_SERVER_PORT + random.nextInt(100) + 1;

            LOG.info("Nova tentativa de startup de HSQLDB em nova porta: " + port);
            configurarHSQLDB(port);
            hsqldbServer.start();
            state = hsqldbServer.getState();
        }
        return state;
    }

    @PreDestroy
    public void pararHSQLDB() {
        if (!isExecutandoPovoadorStandAlone() && !isExecutandoTesteFuncional()) {
            doPararHSQLDB();
        } else {
            LOG.info("Execu��o de povoador standalone detectada. O SGBD em uso dever� ser finalizado manualmente pelo desenvolvedor!");
        }
    }

    public Boolean isExecutandoPovoadorStandAlone() {
        String property = System.getProperty(PROP_EXECUTANDO_POVOADOR_STAND_ALONE);
        return StringUtils.isNotBlank(property) ? Boolean.valueOf(property) : false;
    }

    public Boolean isExecutandoTesteFuncional() {
        String property = System.getProperty(PROP_EXECUTANDO_TESTE_FUNCIONAL);
        return StringUtils.isNotBlank(property) ? Boolean.valueOf(property) : false;
    }

    private void doPararHSQLDB() {
        try {
            LOG.info("Finalizando HSQLDB em mem�ria na porta: " + getServerPort());
            hsqldbServer.stop();
            LOG.info("HSQLDB em mem�ria finalizado.");
        } catch (Throwable e) {
            LOG.warn(e);
        }
    }

    /**
     * @return the serverPort
     */
    public int getServerPort() {
        return serverPort;
    }

    public boolean isServerPortDefault() {
        return ServerConstants.SC_DEFAULT_HSQL_SERVER_PORT == getServerPort();
    }
}
