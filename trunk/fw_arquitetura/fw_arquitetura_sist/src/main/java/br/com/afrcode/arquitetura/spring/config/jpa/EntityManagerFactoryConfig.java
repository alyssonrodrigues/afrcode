package br.com.afrcode.arquitetura.spring.config.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.cache.ehcache.EhCacheRegionFactory;
import org.hibernate.engine.transaction.internal.jta.CMTTransactionFactory;
import org.hibernate.service.jta.platform.internal.JBossAppServerJtaPlatform;
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
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;
import br.com.afrcode.arquitetura.util.contexto.ApplicationContextUtils;

/**
 * Configurações para uso de JPA via Hibernate EntityManager gerido pelo
 * container JBoss.
 * 
 * 
 */
@Configuration
@PropertySource({ "classpath:hibernate-jpaProperties.properties" })
@Profile(Profiles.PROFILE_APLICACAO)
public class EntityManagerFactoryConfig {

	private static final String JPA_PACKAGES_TO_SCAN = "jpa.packages_to_scan";
	private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
	private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String HIBERNATE_TRANSACTION_FACTORY_CLASS = "hibernate.transaction.factory_class";
	private static final String HIBERNATE_TRANSACTION_JTA_PLATFORM = "hibernate.transaction.jta.platform";
	private static final String HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS = "hibernate.current_session_context_class";
	private static final String HIBERNATE_CACHE_REGION_PROVIDER_CLASS = "hibernate.cache.region.factory_class";
	private static final String HIBERNATE_CACHE_USE_QUERY_CACHE = "hibernate.cache.use_query_cache";
	private static final String HIBERNATE_DEFAULT_SCHEMA = "hibernate.default_schema";

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private DataSource jtaDataSource;

	@Value("${" + JPA_PACKAGES_TO_SCAN + "}")
	private String packagesToScan;

	@Value("${" + HIBERNATE_DIALECT + "}")
	private String hibernateDialect;

	@Value("${" + HIBERNATE_SHOW_SQL + "}")
	private String hibernateShowSql;

	@Value("${" + HIBERNATE_FORMAT_SQL + "}")
	private String hibernateFormatSql;

	@Value("${" + HIBERNATE_USE_SQL_COMMENTS + "}")
	private String hibernateUseSqlComments;

	@Value("${" + HIBERNATE_HBM2DDL_AUTO + "}")
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

		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

		String[] aux = packagesToScan.split(",");
		entityManagerFactoryBean.setPackagesToScan(aux);

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
				.getProperty(HIBERNATE_DEFAULT_SCHEMA);
		if (StringUtils.isNotBlank(hibernateDefaultSchema)) {
			jpaProperties.put(HIBERNATE_DEFAULT_SCHEMA, hibernateDefaultSchema);
		}

		// Configurações do JTA para APLICACAO
		// Integração Spring Hibernate onde informamos ao Hibernate que
		// transações são iniciadas pelo Spring (Container Managed
		// Transactions)
		jpaProperties.put(HIBERNATE_TRANSACTION_FACTORY_CLASS,
				CMTTransactionFactory.class.getName());
		jpaProperties.put(HIBERNATE_TRANSACTION_JTA_PLATFORM,
				JBossAppServerJtaPlatform.class.getName());
		jpaProperties.put(HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS, "jta");

		// Configurações para uso de cache de segundo nível do Hibernate.
		// Por padrão apenas entidades @Cacheable (@Cache) são geridas no cache
		// de segundo nível.
		// "Entities are not cached unless explicitly marked as cacheable."
		jpaProperties.put(HIBERNATE_CACHE_REGION_PROVIDER_CLASS,
				EhCacheRegionFactory.class.getName());
		jpaProperties.put(HIBERNATE_CACHE_USE_QUERY_CACHE,
				Boolean.FALSE.toString());

		entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);

		/*
		 * Para atribuição de datasource JTA é necessário usar um
		 * PersistenceUnitPostProcessor, pois a API de
		 * LocalContainerEntityManagerFactoryBean não permite atribuição
		 * explícita de datasource JTA.
		 * 
		 * ATENÇÃO: O método
		 * LocalContainerEntityManagerFactoryBean.setDatasource não deve ser
		 * usado pois este é válido para datasource NON_JTA apenas!
		 */
		entityManagerFactoryBean
				.setPersistenceUnitPostProcessors(new PersistenceUnitPostProcessor() {
					@Override
					public void postProcessPersistenceUnitInfo(
							MutablePersistenceUnitInfo pui) {
						pui.setJtaDataSource(jtaDataSource);
					}
				});

		entityManagerFactoryBean.afterPropertiesSet();

		return entityManagerFactoryBean;
	}

}
