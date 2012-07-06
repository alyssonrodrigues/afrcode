package projetomvc.sistema.spring.config.ds;

import static projetomvc.sistema.spring.util.Profiles.PROFILE_PROD;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(PROFILE_PROD)
public class DataSourceConfig {
	
	@Bean
	public DataSource dataSource() throws NamingException {
		Context context = new InitialContext();
		DataSource dataSource = (DataSource) context.lookup(
				"java:com/env/jdbc/projetomvc");
		return dataSource;
	}

}
