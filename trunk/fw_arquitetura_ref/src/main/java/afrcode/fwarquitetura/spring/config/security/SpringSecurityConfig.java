package afrcode.fwarquitetura.spring.config.security;

import static afrcode.fwarquitetura.spring.config.util.Profiles.PROFILE_APLICACAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * Configurações necessárias para uso do Spring Security.
 * 
 * A estratégia em uso é a de definição dos beans necessários em detrimento ao uso do namespace security (via XML). O uso do
 * namespace security (via XML) poderá ser usado em conjunto ou em substituição a esta classe.
 * 
 * ATENÇÃO: para que aplicações WEB obtenham os serviços aqui configurados é necessário incluir o filter
 * {@link DelegatingFilterProxy} em seus respectivos arquivos web.xml conforme exemplo abaixo:
 * 
 * <filter>
 * <filter-name>springSecurityFilterChain</filter-name>
 * <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
 * </filter>
 * 
 * <filter-mapping>
 * <filter-name>springSecurityFilterChain</filter-name>
 * <url-pattern>/*</url-pattern>
 * </filter-mapping>
 * 
 * TODO: Incluir filter para logout {@link LogoutFilter}.
 * 
 * @author alyssonfr
 * 
 */
@Configuration
@Profile({PROFILE_APLICACAO})
@ImportResource({"classpath:spring-security-beans.xml"})
public class SpringSecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoderService passwordEncoderService;

    /**
     * Bean provedor de autenticação Spring. Responsável por validar as credenciais de um usuário via DAO pattern.
     * 
     * A obtenção dos dados para validação face às credenciais ocorre via implementação de {@link UserDetailsService}.
     * 
     * @return
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoderService);
        return daoAuthenticationProvider;
    }

}
