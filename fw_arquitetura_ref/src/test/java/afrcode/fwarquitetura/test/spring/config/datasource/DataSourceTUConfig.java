package afrcode.fwarquitetura.test.spring.config.datasource;

import static afrcode.fwarquitetura.spring.config.util.Profiles.PROFILE_TESTES;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Configurações de datasource em uso no ambiente de desenvolvimento em especial
 * para testes de unidade.
 * 
 * As informações necessárias (url, user, passwd, driver_class) são obtidas via
 * Java Properties (ver {@link PropertiesConfig}).
 * 
 * @author alyssonfr
 * 
 */
@Configuration
@Profile(PROFILE_TESTES)
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
