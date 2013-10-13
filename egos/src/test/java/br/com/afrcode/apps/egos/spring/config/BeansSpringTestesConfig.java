package br.com.afrcode.apps.egos.spring.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan(basePackages = "br.com.afrcode")
@PropertySource({"classpath:acessoadados.properties"})
@ImportResource({"classpath:spring-security-testes-beans.xml"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Profile("TESTES")
public class BeansSpringTestesConfig {

	@Value("${datasource.driverClassName}")
	private String dataSourceDriverName;
	@Value("${datasource.connection.url}")
	private String dataSourceUrl;
	@Value("${datasource.connection.username}")
	private String dataSourceUsername;
	@Value("${datasource.connection.password}")
	private String dataSourcePassword;

	@Value("${jpa.packages_to_scan}")
	private String packagesToScan;

	@Value("${hibernate.dialect_tu}")
	private String hibernateDialect;
	@Value("${hibernate.show_sql}")
	private String hibernateShowSql;
	@Value("${hibernate.format_sql}")
	private String hibernateFormatSql;
	@Value("${hibernate.use_sql_comments}")
	private String hibernateUseSqlComments;
	@Value("${hibernate.hbm2ddl.auto_tu}")
	private String hibernateHbm2ddlAuto;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertiesConfigurer() {
		PropertySourcesPlaceholderConfigurer propertiesConfigurer = 
				new PropertySourcesPlaceholderConfigurer();
		return propertiesConfigurer;
	}

	@Bean
	@DependsOn("hsqldbUtil")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(dataSourceDriverName);
		dataSource.setUrl(dataSourceUrl);
		dataSource.setUsername(dataSourceUsername);
		dataSource.setPassword(dataSourcePassword);
		return dataSource;
	}

	@Bean
	public NamedParameterJdbcOperations jdbcTemplate() {
		NamedParameterJdbcOperations jdbcTemplate = 
				new NamedParameterJdbcTemplate(dataSource());
		return jdbcTemplate;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = 
				new JpaTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = 
				new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(dataSource());

		String[] aux = packagesToScan.split(",");
		entityManagerFactoryBean.setPackagesToScan(aux);

		JpaVendorAdapter jpaVendorAdapter = 
				new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

		Map<String, String> jpaProperties = new HashMap<String, String>();
		jpaProperties.put("hibernate.dialect", hibernateDialect);
		jpaProperties.put("hibernate.show_sql", hibernateShowSql);
		jpaProperties.put("hibernate.format_sql", hibernateFormatSql);
		jpaProperties.put("hibernate.use_sql_comments", 
				hibernateUseSqlComments);
		jpaProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);

		entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);
		return entityManagerFactoryBean;
	}
	
	@Bean
	public TestingAuthenticationProvider testingAuthenticationProvider() {
		TestingAuthenticationProvider testingAuthenticationProvider =
				new TestingAuthenticationProvider();
		return testingAuthenticationProvider;
	}
}
