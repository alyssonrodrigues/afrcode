package br.com.afrcode.arquitetura.spring.config.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;
import br.com.afrcode.arquitetura.util.contexto.ContextoAplicacaoWeb;
import br.com.afrcode.arquitetura.util.excecao.ExcecaoNaoPrevista;

/**
 * Configurações para definições de datasource em uso pela aplicação em
 * produção.
 * 
 * O datasource será obtido via JNDI.
 * 
 * Contextos Spring para aplicações Web são iniciados a partir do web.xml, onde
 * é definido o init-param contextId=nomeContextoWeb. Neste caso o nome JNDI
 * será obtido via sintaxe: java:/xadatasources/<<nomeContextoWeb>>Datasource.
 * 
 * Contextos Spring para EJBs são independentes e isolados em relação a
 * Contextos Spring para aplicações Web. Neste caso o nome JNDI será obtido via
 * arquivo de propriedades em
 * <<projeto>>IS-impl/src/main/resources/datasource.properties.
 * 
 * 
 */
@Configuration
@Profile(Profiles.PROFILE_APLICACAO)
public class DataSourceConfig {
    private static final Logger LOG = Logger.getLogger(DataSourceConfig.class);

    @Autowired
    private ContextoAplicacaoWeb contextoAplicacaoWeb;

    @Bean
    @Primary
    public DataSource dataSource() {
        DataSource dataSource = null;
        try {
            Context context = new InitialContext();
            String datasourceJndiName = recuperarDatasourceJndiName();
            dataSource = (DataSource) context.lookup(datasourceJndiName);
        } catch (NamingException e) {
            LOG.error(e);
            throw new ExcecaoNaoPrevista(e);
        }
        return dataSource;
    }

    private String recuperarDatasourceJndiName() {
        String datasourceJndiName = null;
        InputStream isDataSourceProperties =
                Thread.currentThread().getContextClassLoader().getResourceAsStream("datasource.properties");
        if (isDataSourceProperties == null) {
            datasourceJndiName = "java:/xadatasources/" + contextoAplicacaoWeb.getNomeContextoWeb() + "Datasource";
        } else {
            Properties datasourceProperties = new Properties();
            try {
                datasourceProperties.load(isDataSourceProperties);
                datasourceJndiName = datasourceProperties.getProperty("datasourceJndiName");
            } catch (IOException e) {
                throw new ExcecaoNaoPrevista(e);
            }
        }
        return datasourceJndiName;
    }

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

}
