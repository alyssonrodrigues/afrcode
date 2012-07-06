package projetomvc.test.util.hsqldb;

import org.apache.log4j.Logger;
import org.hsqldb.Server;

/**
 * Classe utilit�ria para startup e shutdown do SGBD HSQLDB.
 * 
 * @author alyssonfr
 * 
 */
public class HSQLDBUtil {
    private static final Logger LOG = Logger.getLogger(HSQLDBUtil.class);

    private static Server hsqldbServer;

    private static HSQLDBUtil instancia = null;

    public static final HSQLDBUtil getInstancia() {
        if (instancia == null) {
            synchronized (HSQLDBUtil.class) {
                if (instancia == null) {
                    instancia = new HSQLDBUtil();
                }
            }
        }
        return instancia;
    }

    private HSQLDBUtil() {

    }

    public void iniciarHSQLDB() {
        try {
            hsqldbServer = new Server();
            hsqldbServer.setLogWriter(null);
            hsqldbServer.setDatabaseName(0, "testdb");
            hsqldbServer.setDatabasePath(0, "mem:testdb");
            LOG.info("Iniciando HSQLDB...");
            hsqldbServer.start();
            LOG.info("HSQLDB em execu��o...");
        } catch (Throwable e) {
            if (hsqldbServer != null) {
                hsqldbServer.stop();
            }
            throw new RuntimeException(e);
        }
    }

    public void pararHSQLDB() {
        if (hsqldbServer != null) {
            LOG.info("Finalizando HSQLDB...");
            hsqldbServer.stop();
            LOG.info("HSQLDB finalizado.");
        }
    }
}
