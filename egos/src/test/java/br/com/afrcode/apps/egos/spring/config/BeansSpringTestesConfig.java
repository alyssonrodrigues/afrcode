package br.com.afrcode.apps.egos.spring.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@ComponentScan(basePackages = "br.com.afrcode")
@PropertySource({"classpath:acessoadados.properties"})
@Profile("TESTES")
public class BeansSpringTestesConfig {
	
	@Value("datasource.driverClassName")
	private String dataSourceDriverName;
	@Value("datasource.connection.url")
	private String dataSourceUrl;
	@Value("datasource.connection.username")
	private String dataSourceUsername;
	@Value("datasource.connection.password")
	private String dataSourcePassword;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(dataSourceDriverName);
		dataSource.setUrl(dataSourceUrl);
		dataSource.setUsername(dataSourceUsername);
		dataSource.setPassword(dataSourcePassword);
		return dataSource;
	}
	
	@Bean
	public NamedParameterJdbcOperations jdbcTemplate() {
		NamedParameterJdbcOperations jdbcTemplate =
				new NamedParameterJdbcTemplate(dataSource());
		return jdbcTemplate;
	}
}
