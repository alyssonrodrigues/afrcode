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
 * Configurações necessárias para uso do Spring Security.
 * 
 * A estratégia em uso é a de definição dos beans necessários em detrimento ao
 * uso do namespace security (via XML). O uso do namespace security (via XML)
 * poderá ser usado em conjunto ou em substituição a esta classe.
 * 
 * ATENÇÃO: para que aplicações WEB obtenham os serviços aqui configurados é
 * necessário incluir o filter DelegatingFilterProxy em seus respectivos
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
     * Bean provedor de autenticação Spring. Responsável por encadear a
     * autenticação via DAO pattern e via JAAS.
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
     * Bean provedor de autenticação Spring. Responsável por validar as
     * credenciais de um usuário via DAO pattern.
     * 
     * A obtenção dos dados para validação face às credenciais ocorre via
     * implementação de UserDetailsService.
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
     * Bean provedor de autenticação Spring. Responsável por validar as
     * credenciais de um usuário via JAAS LoginModule.
     * 
     * O LoginModule é configurado via AppConfigurationEntry e repassado ao
     * DefaultJaasAuthenticationProvider.
     * 
     * O provider JAAS em uso é o JBoss Security vi módulo
     * org.jboss.security.ClientLoginModule. Este módulo é responsável por
     * configurar as propriedades
     * org.jboss.security.SecurityAssociation.principal e
     * org.jboss.security.SecurityAssociation.credential de acordo com a
     * informações correntes de autenticação. Ou seja, não há autenticação de
     * fato em execução neste módulo e sim apenas configuração de ambiente!
     * 
     * @return
     */
    @Bean
    public DefaultJaasAuthenticationProvider defaultJaasAuthenticationProvider() {
        DefaultJaasAuthenticationProvider defaultJaasAuthenticationProvider = new DefaultJaasAuthenticationProvider();

        // Configurações do provider JAAS. Integração Spring Security com o JAAS
        // provider (JBoss) para Autenticação.
        AppConfigurationEntry appConfigurationEntry =
                new AppConfigurationEntry(LOGIN_MODULE, LoginModuleControlFlag.REQUIRED, new HashMap<String, Object>());
        defaultJaasAuthenticationProvider.setConfiguration(new InMemoryConfiguration(
                new AppConfigurationEntry[] { appConfigurationEntry }));
        defaultJaasAuthenticationProvider.setLoginContextName(LOGIN_CONTEXT_NAME);

        // Configurações do provider JAAS. Integração Spring Security com o JAAS
        // provider para Autorização.
        AuthorityGranter authorityGranter = authorityGranter();
        defaultJaasAuthenticationProvider.setAuthorityGranters(new AuthorityGranter[] { authorityGranter });

        return defaultJaasAuthenticationProvider;
    }

    /**
     * Adaptador responsável por fazer o DE-PARA entre credenciais do principal
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
     * Bean responsável por finalizar o processo de Logout de usuários.
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
     * Bean responsável por tratar erros de acesso negado (erros de
     * autorização).
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
     * Bean responsável por gerir o processo de Logout de usuários.
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
     * Bean responsável por tratar erros de login.
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
