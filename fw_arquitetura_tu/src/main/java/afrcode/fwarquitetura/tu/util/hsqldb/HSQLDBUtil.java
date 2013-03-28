package afrcode.fwarquitetura.tu.util.hsqldb;

import static afrcode.fwarquitetura.tu.spring.config.util.ProfilesTU.PROFILE_TESTES;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.hsqldb.Server;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Classe utilitária para startup e shutdown do SGBD HSQLDB.
 * 
 * @author alyssonfr
 * 
 */
@Component
@Profile(PROFILE_TESTES)
public class HSQLDBUtil {
    private static final Logger LOG = Logger.getLogger(HSQLDBUtil.class);

    private static Server hsqldbServer;

	private void configurarHSQLDBServer() {
		hsqldbServer = new Server();
		hsqldbServer.setLogWriter(null);
		hsqldbServer.setDatabaseName(0, "testdb");
		hsqldbServer.setDatabasePath(0, "mem:testdb");
	}

    @PostConstruct
    protected void iniciarHSQLDB() {
        try {
            LOG.info("Iniciando HSQLDB...");
            configurarHSQLDBServer();
            hsqldbServer.start();
            LOG.info("HSQLDB em execução...");
        } catch (Throwable e) {
            hsqldbServer.stop();
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    protected void pararHSQLDB() {
        LOG.info("Finalizando HSQLDB...");
        hsqldbServer.stop();
        LOG.info("HSQLDB finalizado.");
    }
}
