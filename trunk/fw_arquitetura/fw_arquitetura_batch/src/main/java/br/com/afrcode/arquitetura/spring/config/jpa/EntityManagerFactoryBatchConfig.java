package br.com.afrcode.arquitetura.spring.config.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.cache.ehcache.EhCacheRegionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;
import br.com.afrcode.arquitetura.util.contexto.ApplicationContextUtils;

/**
 * Configurações para uso de JPA via Hibernate EntityManager.
 * 
 * Difere de EntityManagerFactoryConfig por usar um datasource específico para o
 * ambiente de desenvolvimento, em especial para batchES (ver
 * DataSourceBatchConfig).
 * 
 * 
 */
@Configuration
@PropertySource({ "classpath:hibernate-jpaProperties-batch.properties" })
@Profile(Profiles.PROFILE_APLICACAO_BATCH)
public class EntityManagerFactoryBatchConfig {

	private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String HIBERNATE_HBM2DDL_AUTO_BATCH = "hibernate.hbm2ddl.auto_batch";
	private static final String HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
	private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String HIBERNATE_DIALECT_BATCH = "hibernate.dialect_batch";
	private static final String JPA_PACKAGES_TO_SCAN = "jpa.packages_to_scan";
	private static final String HIBERNATE_CACHE_REGION_PROVIDER_CLASS = "hibernate.cache.region.factory_class";
	private static final String HIBERNATE_CACHE_USE_QUERY_CACHE = "hibernate.cache.use_query_cache";
	private static final String HIBERNATE_DEFAULT_SCHEMA = "hibernate.default_schema";
	private static final String HIBERNATE_DEFAULT_SCHEMA_BATCH = "hibernate.default_schema_batch";

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private DataSource dataSource;

	@Value("${" + JPA_PACKAGES_TO_SCAN + "}")
	private String packagesToScan;

	@Value("${" + HIBERNATE_DIALECT_BATCH + "}")
	private String hibernateDialect;

	@Value("${" + HIBERNATE_SHOW_SQL + "}")
	private String hibernateShowSql;

	@Value("${" + HIBERNATE_FORMAT_SQL + "}")
	private String hibernateFormatSql;

	@Value("${" + HIBERNATE_USE_SQL_COMMENTS + "}")
	private String hibernateUseSqlComments;

	@Value("${" + HIBERNATE_HBM2DDL_AUTO_BATCH + "}")
	private String hibernateHbm2ddlAuto;

	/**
	 * Integração Spring e Hibernate (JPA) ...
	 * 
	 * @return
	 */
	@Bean
	@DependsOn("hsqldbUtil")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(dataSource);

		String[] aux = packagesToScan.split(",");
		entityManagerFactoryBean.setPackagesToScan(aux);

		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

		Map<String, String> jpaProperties = new HashMap<String, String>();
		jpaProperties.put(HIBERNATE_DIALECT, hibernateDialect);
		jpaProperties.put(HIBERNATE_SHOW_SQL, hibernateShowSql);
		jpaProperties.put(HIBERNATE_FORMAT_SQL, hibernateFormatSql);
		jpaProperties.put(HIBERNATE_USE_SQL_COMMENTS, hibernateUseSqlComments);
		if (!"off".equals(hibernateHbm2ddlAuto)
				&& !ApplicationContextUtils
						.isEjbSpringApplicationContext(applicationContext)) {
			jpaProperties.put(HIBERNATE_HBM2DDL_AUTO, hibernateHbm2ddlAuto);
		}
		// Property não obtida por @Value por ser opcional.
		String hibernateDefaultSchema = applicationContext.getEnvironment()
				.getProperty(HIBERNATE_DEFAULT_SCHEMA_BATCH);
		if (StringUtils.isNotBlank(hibernateDefaultSchema)) {
			jpaProperties.put(HIBERNATE_DEFAULT_SCHEMA, hibernateDefaultSchema);
		}

		// Configurações para uso de cache de segundo nível do Hibernate.
		// Por padrão apenas entidades @Cacheable (@Cache) são geridas no cache
		// de segundo nível.
		// "Entities are not cached unless explicitly marked as cacheable."
		jpaProperties.put(HIBERNATE_CACHE_REGION_PROVIDER_CLASS,
				EhCacheRegionFactory.class.getName());
		jpaProperties.put(HIBERNATE_CACHE_USE_QUERY_CACHE,
				Boolean.FALSE.toString());

		entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);
		return entityManagerFactoryBean;
	}

}
