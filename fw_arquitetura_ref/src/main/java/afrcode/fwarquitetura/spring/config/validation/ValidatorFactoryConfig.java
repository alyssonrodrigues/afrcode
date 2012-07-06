package afrcode.fwarquitetura.spring.config.validation;

import static afrcode.fwarquitetura.spring.config.util.Profiles.PROFILE_APLICACAO;
import static afrcode.fwarquitetura.spring.config.util.Profiles.PROFILE_TESTES;

import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configura��o para tornar dispon�vel via DI beans do tipo {@link Validator}, necess�rios a rotinas de valida��o via Beans
 * Validations.
 * 
 * @author alyssonfr
 * 
 */
@Configuration
@Profile({PROFILE_TESTES, PROFILE_APLICACAO})
public class ValidatorFactoryConfig {

    @Bean
    public LocalValidatorFactoryBean validatorFactory() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        return localValidatorFactoryBean;
    }

}
