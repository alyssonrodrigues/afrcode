package br.com.afrcode.apps.egos.spring.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
@ComponentScan(basePackages = "br.com.afrcode")
@Profile("APLICACAO")
public class BeansSpringConfig {
	
	@Bean
	public DataSource jndiDataSource() {
		JndiDataSourceLookup jndiDataSourceLookup =
				new JndiDataSourceLookup();
		DataSource jndiDataSource = 
				jndiDataSourceLookup.getDataSource(
						"java:/datasources/egosdatasource");
		return jndiDataSource;
	}

	@Bean
	public NamedParameterJdbcOperations jdbcTemplate() {
		NamedParameterJdbcOperations jdbcTemplate =
				new NamedParameterJdbcTemplate(jndiDataSource());
		return jdbcTemplate;
	}
}
