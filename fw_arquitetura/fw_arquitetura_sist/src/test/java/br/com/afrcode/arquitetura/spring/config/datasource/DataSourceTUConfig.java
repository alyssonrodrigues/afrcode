package br.com.afrcode.arquitetura.spring.config.datasource;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;
import br.com.afrcode.arquitetura.util.hsqldb.HsqldbUtil;

/**
 * Configurações de datasource em uso no ambiente de desenvolvimento em especial
 * para testes de unidade.
 * 
 * As informações necessárias (url, user, passwd, driver_class) são obtidas via
 * Java Properties (ver PropertiesConfig).
 * 
 * 
 */
@Configuration
@Profile(Profiles.PROFILE_TU)
public class DataSourceTUConfig {
	private static final Logger LOG = Logger
			.getLogger(DataSourceTUConfig.class);

	@Value("${hibernate.connection.url}")
	private String url;

	@Value("${hibernate.connection.user}")
	private String username;

	@Value("${hibernate.connection.password}")
	private String password;

	@Value("${hibernate.connection.driver_class}")
	private String driverClassName;

	@Autowired
	private HsqldbUtil hsqldbUtil;

	@Bean
	@Primary
	public DataSource dataSource() {

		alterarUrlParaTUSeNecessario();

		DriverManagerDataSource dataSource = new DriverManagerDataSource(url,
				username, password);
		dataSource.setDriverClassName(driverClassName);
		return dataSource;
	}

	private void alterarUrlParaTUSeNecessario() {
		if (!hsqldbUtil.isExecutandoPovoadorStandAlone()
				&& !hsqldbUtil.isExecutandoTesteFuncional()
				&& !hsqldbUtil.isExecutandoTesteIntegracao()
				&& !hsqldbUtil.isEjbSpringApplicationContext()
				&& !hsqldbUtil.isServerPortDefault()) {
			// TUs usando HSQLDB iniciado em porta diferente do padrão, é
			// necessário alterar a URL padrão, obtida do
			// hibernate-jpaProperties.properties.
			String replacement = "localhost\\:" + hsqldbUtil.getServerPort();
			url = url.replaceFirst("localhost", replacement);
			LOG.info("Nova url para o HSQLDB iniciado em nova porta: " + url);
		}
	}

	@Bean
	@Primary
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

}
