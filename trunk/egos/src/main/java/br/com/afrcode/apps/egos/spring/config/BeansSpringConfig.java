package br.com.afrcode.apps.egos.spring.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan(basePackages = "br.com.afrcode")
@PropertySource({"classpath:acessoadados.properties"})
@ImportResource({"classpath:spring-security-beans.xml"})
@Profile("APLICACAO")
public class BeansSpringConfig {

	@Value("${jpa.packages_to_scan}")
	private String packagesToScan;

	@Value("${hibernate.dialect}")
	private String hibernateDialect;
	@Value("${hibernate.show_sql}")
	private String hibernateShowSql;
	@Value("${hibernate.format_sql}")
	private String hibernateFormatSql;
	@Value("${hibernate.use_sql_comments}")
	private String hibernateUseSqlComments;
	@Value("${hibernate.hbm2ddl.auto}")
	private String hibernateHbm2ddlAuto;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertiesConfigurer() {
		PropertySourcesPlaceholderConfigurer propertiesConfigurer = 
				new PropertySourcesPlaceholderConfigurer();
		return propertiesConfigurer;
	}

	@Bean
	@DependsOn("hsqldbUtil")
	public DataSource jndiDataSource() {
		JndiDataSourceLookup jndiDataSourceLookup = 
				new JndiDataSourceLookup();
		DataSource jndiDataSource = 
				jndiDataSourceLookup.getDataSource(
						"datasources/egosdatasource");
		return jndiDataSource;
	}

	@Bean
	public NamedParameterJdbcOperations jdbcTemplate() {
		NamedParameterJdbcOperations jdbcTemplate = 
				new NamedParameterJdbcTemplate(jndiDataSource());
		return jdbcTemplate;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = 
				new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(
				entityManagerFactory().getObject());
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = 
				new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(jndiDataSource());

		String[] aux = packagesToScan.split(",");
		entityManagerFactoryBean.setPackagesToScan(aux);

		JpaVendorAdapter jpaVendorAdapter = 
				new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

		Map<String, String> jpaProperties = 
				new HashMap<String, String>();
		jpaProperties.put("hibernate.dialect", hibernateDialect);
		jpaProperties.put("hibernate.show_sql", hibernateShowSql);
		jpaProperties.put("hibernate.format_sql", hibernateFormatSql);
		jpaProperties.put("hibernate.use_sql_comments", hibernateUseSqlComments);
		jpaProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);

		entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);
		entityManagerFactoryBean.afterPropertiesSet();
		return entityManagerFactoryBean;
	}
}
