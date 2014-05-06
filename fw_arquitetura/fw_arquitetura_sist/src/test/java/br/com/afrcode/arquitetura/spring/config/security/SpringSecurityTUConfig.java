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
 * Configura��es necess�rias para uso do Spring Security.
 * 
 * A estrat�gia em uso � a de defini��o dos beans necess�rios em detrimento ao
 * uso do namespace security (via XML). O uso do namespace security (via XML)
 * poder� ser usado em conjunto ou em substitui��o a esta classe.
 * 
 * Para o profile de TUs basta definir um autenticador JAAS para autentica��o de
 * um PRINCIPAL com ROLE padr�o para os TUs. Ver LoginModuleParaTU e suas
 * defini��es.
 * 
 * 
 */
@Configuration
@Profile({ Profiles.PROFILE_TU })
@ImportResource({ "classpath:spring-security-beans-tu.xml" })
public class SpringSecurityTUConfig {

	/**
	 * Bean provedor de autentica��o Spring. Respons�vel por validar as
	 * credenciais de um usu�rio via JAAS LoginModule.
	 * 
	 * O LoginModule � configurado via AppConfigurationEntry e repassado ao
	 * DefaultJaasAuthenticationProvider.
	 * 
	 * O provider JAAS em uso para testes � o LoginModuleParaTU.
	 * 
	 * @return
	 */
	@Bean
	public DefaultJaasAuthenticationProvider authenticationProviderImpl() {
		DefaultJaasAuthenticationProvider defaultJaasAuthenticationProvider = new DefaultJaasAuthenticationProvider();

		// Configura��es do provider JAAS. Integra��o Spring Security com o JAAS
		// provider para Autentica��o.
		Map<String, AppConfigurationEntry[]> appCofEntries = new HashMap<String, AppConfigurationEntry[]>();
		AppConfigurationEntry appConfigurationEntry = new AppConfigurationEntry(
				LoginModuleParaTU.class.getName(),
				LoginModuleControlFlag.REQUIRED, Collections.EMPTY_MAP);
		appCofEntries.put("SPRINGSECURITY",
				new AppConfigurationEntry[] { appConfigurationEntry });
		defaultJaasAuthenticationProvider
				.setConfiguration(new InMemoryConfiguration(appCofEntries));

		// Configura��es do provider JAAS. Integra��o Spring Security com o JAAS
		// provider para Autoriza��o.
		AuthorityGranter authorityGranter = authorityGranter();
		defaultJaasAuthenticationProvider
				.setAuthorityGranters(new AuthorityGranter[] { authorityGranter });

		return defaultJaasAuthenticationProvider;
	}

	/**
	 * Adaptador respons�vel por fazer o DE-PARA entre credenciais do principal
	 * (Subject JAAS) e GrantedAuthority do Spring Security.
	 * 
	 * @return
	 */
	private AuthorityGranter authorityGranter() {
		AuthorityGranter authorityGranter = new AuthorityGranterParaTU();
		return authorityGranter;
	}

}
