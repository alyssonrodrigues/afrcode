package afrcode.fwarquitetura.test.spring.config.jpa;

import static afrcode.fwarquitetura.spring.config.util.Profiles.PROFILE_TESTES;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import afrcode.fwarquitetura.spring.config.jpa.EntityManagerFactoryConfig;
import afrcode.fwarquitetura.test.spring.config.datasource.DataSourceTUConfig;

/**
 * Configurações para uso de JPA via Hibernate EntityManager.
 * 
 * Difere de {@link EntityManagerFactoryConfig} por usar um datasource específico
 * para o ambiente de desenvolvimento, em especial para testes de unidade
 * (ver {@link DataSourceTUConfig}).
 * 
 * @author alyssonfr
 * 
 */
@Configuration
@PropertySource({"classpath:hibernate-jpaProperties.properties"})
@Profile(PROFILE_TESTES)
public class EntityManagerFactoryTUConfig {

    private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_HBM2DDL_AUTO_TU = "hibernate.hbm2ddl.auto_tu";
    private static final String HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
    private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String HIBERNATE_DIALECT_TU = "hibernate.dialect_tu";
    private static final String JPA_PACKAGES_TO_SCAN = "jpa.packages_to_scan";

    @Autowired
    private DataSource dataSource;

    @Value("${" + JPA_PACKAGES_TO_SCAN + "}")
    private String packagesToScan;

    @Value("${" + HIBERNATE_DIALECT_TU + "}")
    private String hibernateDialect;

    @Value("${" + HIBERNATE_SHOW_SQL + "}")
    private String hibernateShowSql;

    @Value("${" + HIBERNATE_FORMAT_SQL + "}")
    private String hibernateFormatSql;

    @Value("${" + HIBERNATE_USE_SQL_COMMENTS + "}")
    private String hibernateUseSqlComments;

    @Value("${" + HIBERNATE_HBM2DDL_AUTO_TU + "}")
    private String hibernateHbm2ddlAuto;

    /**
     * Integração Spring e Hibernate (JPA) ...
     * 
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource);

        String[] aux = packagesToScan.split(",");
        entityManagerFactoryBean.setPackagesToScan(aux);

        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

        Map<String, String> jpaProperties = new HashMap<String, String>();
        jpaProperties.put(HIBERNATE_DIALECT_TU, hibernateDialect);
        jpaProperties.put(HIBERNATE_SHOW_SQL, hibernateShowSql);
        jpaProperties.put(HIBERNATE_FORMAT_SQL, hibernateFormatSql);
        jpaProperties.put(HIBERNATE_USE_SQL_COMMENTS, hibernateUseSqlComments);
        jpaProperties.put(HIBERNATE_HBM2DDL_AUTO, hibernateHbm2ddlAuto);

        entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);
        return entityManagerFactoryBean;
    }

}
