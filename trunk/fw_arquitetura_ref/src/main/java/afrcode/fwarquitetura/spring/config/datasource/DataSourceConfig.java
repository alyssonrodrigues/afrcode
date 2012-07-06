package afrcode.fwarquitetura.spring.config.datasource;

import static afrcode.fwarquitetura.spring.config.util.Profiles.PROFILE_APLICACAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Configura��es para defini��es de datasource em uso pela aplica��o em produ��o.
 * 
 * O datasource ser� obtido via JNDI.
 * 
 * @author alyssonfr
 * 
 */
@Configuration
@Profile(PROFILE_APLICACAO)
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() throws NamingException {
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(
                "java:jboss/datasources/dataSourceDefault");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws NamingException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

}
