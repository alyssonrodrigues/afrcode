package br.com.afrcode.apps.egos.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hsqldb.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HsqldbUtil {

	@Value("${datasource.connection.url}")
	private String dataSourceUrl;

	private Server hsqldbServer;

	private void configurarHSQLDB() {
		hsqldbServer = new Server();
		hsqldbServer.setLogWriter(null);
		String databaseName = dataSourceUrl.substring(
				dataSourceUrl.lastIndexOf("/") + 1);
		hsqldbServer.setDatabaseName(0, databaseName);
		hsqldbServer.setDatabasePath(0, "mem:" + databaseName);
	}

	@PostConstruct
	public void iniciarHSQLDB() {
		try {
			configurarHSQLDB();
			hsqldbServer.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@PreDestroy
	public void pararHSQLDB() {
		try {
			hsqldbServer.stop();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
