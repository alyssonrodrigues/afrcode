package br.com.afrcode.arquitetura.spring.config.datasource;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;
import br.com.afrcode.arquitetura.util.hsqldb.HsqldbUtil;

/**
 * Configurações de datasource em uso no ambiente de desenvolvimento em especial
 * para batchES.
 * 
 * As informações necessárias (url, user, passwd, driver_class) são obtidas via
 * Java Properties (ver PropertiesConfig).
 * 
 * 
 */
@Configuration
@Profile(Profiles.PROFILE_APLICACAO_BATCH)
public class DataSourceBatchConfig {
    private static final Logger LOG = Logger.getLogger(DataSourceBatchConfig.class);

    @Value("${hibernate.connection.url}")
    private String url;

    @Value("${hibernate.connection.user}")
    private String username;

    @Value("${hibernate.connection.password}")
    private String password;

    @Value("${hibernate.connection.driver_class}")
    private String driverClassName;

    @Value("${hibernate.connection.minIdle}")
    private String minIdleConnections;

    @Value("${hibernate.connection.maxActive}")
    private String maxActiveConnections;

    @Autowired
    private HsqldbUtil hsqldbUtil;

    @Bean
    @Primary
    public DataSource dataSource() {

        alterarUrlParaTUSeNecessario();

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setMinIdle(Integer.valueOf(minIdleConnections));
        dataSource.setMaxActive(Integer.valueOf(maxActiveConnections));

        return dataSource;
    }

    private void alterarUrlParaTUSeNecessario() {
        if (!hsqldbUtil.isExecutandoPovoadorStandAlone() && !hsqldbUtil.isExecutandoTesteFuncional()
                && !hsqldbUtil.isExecutandoTesteIntegracao() && !hsqldbUtil.isEjbSpringApplicationContext()
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
