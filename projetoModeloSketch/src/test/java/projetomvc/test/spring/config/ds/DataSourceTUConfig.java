package projetomvc.test.spring.config.ds;

import static projetomvc.sistema.spring.util.Profiles.PROFILE_DESENV;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Profile(PROFILE_DESENV)
public class DataSourceTUConfig {
	@Value("${hibernate.connection.url}") 
	private String url;

	@Value("${hibernate.connection.user}")
	private String username;
	
	@Value("${hibernate.connection.password}")
	private String password;
	
	@Value("${hibernate.connection.driver_class}")
	private String driverClassName;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = 
			new DriverManagerDataSource(url, username, password);
		dataSource.setDriverClassName(driverClassName);
		return dataSource;
	}
	
}
