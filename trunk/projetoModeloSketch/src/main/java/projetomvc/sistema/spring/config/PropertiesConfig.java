package projetomvc.sistema.spring.config;

import static projetomvc.sistema.spring.util.Profiles.PROFILE_DESENV;
import static projetomvc.sistema.spring.util.Profiles.PROFILE_PROD;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile({PROFILE_DESENV, PROFILE_PROD})
@PropertySource("classpath:projetomvc/sistema/spring/config/hibernate-jpaProperties.properties")
public class PropertiesConfig {

}
