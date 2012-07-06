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
 * Configura��es necess�rias para uso do Spring Security.
 * 
 * A estrat�gia em uso � a de defini��o dos beans necess�rios em detrimento ao uso do namespace security (via XML). O uso do
 * namespace security (via XML) poder� ser usado em conjunto ou em substitui��o a esta classe.
 * 
 * ATEN��O: para que aplica��es WEB obtenham os servi�os aqui configurados � necess�rio incluir o filter
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
     * Bean provedor de autentica��o Spring. Respons�vel por validar as credenciais de um usu�rio via DAO pattern.
     * 
     * A obten��o dos dados para valida��o face �s credenciais ocorre via implementa��o de {@link UserDetailsService}.
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
