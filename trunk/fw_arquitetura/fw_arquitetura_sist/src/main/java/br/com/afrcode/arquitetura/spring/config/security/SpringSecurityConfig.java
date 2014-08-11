package br.com.afrcode.arquitetura.spring.config.security;

import java.util.HashMap;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.authentication.jaas.DefaultJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Configura��es necess�rias para uso do Spring Security.
 * 
 * A estrat�gia em uso � a de defini��o dos beans necess�rios em detrimento ao
 * uso do namespace security (via XML). O uso do namespace security (via XML)
 * poder� ser usado em conjunto ou em substitui��o a esta classe.
 * 
 * ATEN��O: para que aplica��es WEB obtenham os servi�os aqui configurados �
 * necess�rio incluir o filter DelegatingFilterProxy em seus respectivos
 * arquivos web.xml conforme exemplo abaixo:
 * 
 * <filter> <filter-name>springSecurityFilterChain</filter-name>
 * <filter-class>org
 * .springframework.web.filter.DelegatingFilterProxy</filter-class> </filter>
 * 
 * <filter-mapping> <filter-name>springSecurityFilterChain</filter-name>
 * <url-pattern>/*</url-pattern> </filter-mapping>
 * 
 * 
 */
@Configuration
@Profile(Profiles.PROFILE_APLICACAO)
@ImportResource({ "classpath:spring-security-beans.xml" })
public class SpringSecurityConfig {
    public static final String LOGIN_CONTEXT_NAME = "SPRINGSECURITY";
    public static final String LOGIN_MODULE = "org.jboss.security.ClientLoginModule";

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * Bean provedor de autentica��o Spring. Respons�vel por encadear a
     * autentica��o via DAO pattern e via JAAS.
     * 
     * @return
     */
    @Bean
    @Primary
    public AuthenticationProviderImpl authenticationProviderImpl() {
        AuthenticationProviderImpl authenticationProviderImpl = new AuthenticationProviderImpl();
        authenticationProviderImpl.setDaoAuthenticationProvider(daoAuthenticationProvider());
        authenticationProviderImpl.setDefaultJaasAuthenticationProvider(defaultJaasAuthenticationProvider());
        return authenticationProviderImpl;
    }

    /**
     * Bean provedor de autentica��o Spring. Respons�vel por validar as
     * credenciais de um usu�rio via DAO pattern.
     * 
     * A obten��o dos dados para valida��o face �s credenciais ocorre via
     * implementa��o de UserDetailsService.
     * 
     * @return
     */
    @Bean
    @Primary
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new PlaintextPasswordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * Bean provedor de autentica��o Spring. Respons�vel por validar as
     * credenciais de um usu�rio via JAAS LoginModule.
     * 
     * O LoginModule � configurado via AppConfigurationEntry e repassado ao
     * DefaultJaasAuthenticationProvider.
     * 
     * O provider JAAS em uso � o JBoss Security vi m�dulo
     * org.jboss.security.ClientLoginModule. Este m�dulo � respons�vel por
     * configurar as propriedades
     * org.jboss.security.SecurityAssociation.principal e
     * org.jboss.security.SecurityAssociation.credential de acordo com a
     * informa��es correntes de autentica��o. Ou seja, n�o h� autentica��o de
     * fato em execu��o neste m�dulo e sim apenas configura��o de ambiente!
     * 
     * @return
     */
    @Bean
    public DefaultJaasAuthenticationProvider defaultJaasAuthenticationProvider() {
        DefaultJaasAuthenticationProvider defaultJaasAuthenticationProvider = new DefaultJaasAuthenticationProvider();

        // Configura��es do provider JAAS. Integra��o Spring Security com o JAAS
        // provider (JBoss) para Autentica��o.
        AppConfigurationEntry appConfigurationEntry =
                new AppConfigurationEntry(LOGIN_MODULE, LoginModuleControlFlag.REQUIRED, new HashMap<String, Object>());
        defaultJaasAuthenticationProvider.setConfiguration(new InMemoryConfiguration(
                new AppConfigurationEntry[] { appConfigurationEntry }));
        defaultJaasAuthenticationProvider.setLoginContextName(LOGIN_CONTEXT_NAME);

        // Configura��es do provider JAAS. Integra��o Spring Security com o JAAS
        // provider para Autoriza��o.
        AuthorityGranter authorityGranter = authorityGranter();
        defaultJaasAuthenticationProvider.setAuthorityGranters(new AuthorityGranter[] { authorityGranter });

        return defaultJaasAuthenticationProvider;
    }

    /**
     * Adaptador respons�vel por fazer o DE-PARA entre credenciais do principal
     * (Subject JAAS) e GrantedAuthority do Spring Security (DAO pattern).
     * 
     * @return
     */
    @Bean
    public AuthorityGranter authorityGranter() {
        AuthorityGranterImpl authorityGranter = new AuthorityGranterImpl();
        authorityGranter.setUserDetailsService(userDetailsService);
        return authorityGranter;
    }

    /**
     * Bean respons�vel por finalizar o processo de Logout de usu�rios.
     * 
     * Ver LogoutSuccessHandler.
     * 
     * @return
     */
    @Bean
    public LogoutSuccessHandlerImpl logoutSuccessHandlerImpl() {
        LogoutSuccessHandlerImpl logoutSuccessHandlerImpl = new LogoutSuccessHandlerImpl();
        logoutSuccessHandlerImpl.setTargetUrlParameter("/web/login.xhtml");
        return logoutSuccessHandlerImpl;
    }

    /**
     * Bean respons�vel por tratar erros de acesso negado (erros de
     * autoriza��o).
     * 
     * @return
     */
    @Bean
    public AccessDeniedHandlerImpl accessDeniedHandlerImpl() {
        AccessDeniedHandlerImpl accessDeniedHandlerImpl = new AccessDeniedHandlerImpl();
        accessDeniedHandlerImpl.setErrorPage("/web/acessonegado.xhtml");
        return accessDeniedHandlerImpl;
    }

    /**
     * Bean respons�vel por gerir o processo de Logout de usu�rios.
     * 
     * Ver LogoutHandler.
     * 
     * @return
     */
    @Bean
    public LogoutHandler logoutHandler() {
        return new SecurityContextLogoutHandler();
    }

    /**
     * Bean respons�vel por tratar erros de login.
     * 
     * @return
     */
    @Bean
    public AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl() {
        AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl = new AuthenticationFailureHandlerImpl();
        authenticationFailureHandlerImpl.setDefaultFailureUrl("/web/login.xhtml");
        authenticationFailureHandlerImpl.setUseForward(false);
        return authenticationFailureHandlerImpl;
    }

}
