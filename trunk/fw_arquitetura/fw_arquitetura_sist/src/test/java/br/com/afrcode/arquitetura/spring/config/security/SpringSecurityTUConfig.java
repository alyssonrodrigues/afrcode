package br.com.afrcode.arquitetura.spring.config.security;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.authentication.jaas.DefaultJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Configurações necessárias para uso do Spring Security.
 * 
 * A estratégia em uso é a de definição dos beans necessários em detrimento ao
 * uso do namespace security (via XML). O uso do namespace security (via XML)
 * poderá ser usado em conjunto ou em substituição a esta classe.
 * 
 * Para o profile de TUs basta definir um autenticador JAAS para autenticação de
 * um PRINCIPAL com ROLE padrão para os TUs. Ver LoginModuleParaTU e suas
 * definições.
 * 
 * 
 */
@Configuration
@Profile({ Profiles.PROFILE_TU })
@ImportResource({ "classpath:spring-security-beans-tu.xml" })
public class SpringSecurityTUConfig {

	/**
	 * Bean provedor de autenticação Spring. Responsável por validar as
	 * credenciais de um usuário via JAAS LoginModule.
	 * 
	 * O LoginModule é configurado via AppConfigurationEntry e repassado ao
	 * DefaultJaasAuthenticationProvider.
	 * 
	 * O provider JAAS em uso para testes é o LoginModuleParaTU.
	 * 
	 * @return
	 */
	@Bean
	public DefaultJaasAuthenticationProvider authenticationProviderImpl() {
		DefaultJaasAuthenticationProvider defaultJaasAuthenticationProvider = new DefaultJaasAuthenticationProvider();

		// Configurações do provider JAAS. Integração Spring Security com o JAAS
		// provider para Autenticação.
		Map<String, AppConfigurationEntry[]> appCofEntries = new HashMap<String, AppConfigurationEntry[]>();
		AppConfigurationEntry appConfigurationEntry = new AppConfigurationEntry(
				LoginModuleParaTU.class.getName(),
				LoginModuleControlFlag.REQUIRED, Collections.EMPTY_MAP);
		appCofEntries.put("SPRINGSECURITY",
				new AppConfigurationEntry[] { appConfigurationEntry });
		defaultJaasAuthenticationProvider
				.setConfiguration(new InMemoryConfiguration(appCofEntries));

		// Configurações do provider JAAS. Integração Spring Security com o JAAS
		// provider para Autorização.
		AuthorityGranter authorityGranter = authorityGranter();
		defaultJaasAuthenticationProvider
				.setAuthorityGranters(new AuthorityGranter[] { authorityGranter });

		return defaultJaasAuthenticationProvider;
	}

	/**
	 * Adaptador responsável por fazer o DE-PARA entre credenciais do principal
	 * (Subject JAAS) e GrantedAuthority do Spring Security.
	 * 
	 * @return
	 */
	private AuthorityGranter authorityGranter() {
		AuthorityGranter authorityGranter = new AuthorityGranterParaTU();
		return authorityGranter;
	}

}
