package br.com.afrcode.arquitetura.spring.config.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Configura��o para tornar dispon�vel via DI beans do tipo Validator,
 * necess�rios a rotinas de valida��o via Beans Validations.
 * 
 * 
 */
@Configuration
@Profile({ Profiles.PROFILE_TU, Profiles.PROFILE_APLICACAO,
		Profiles.PROFILE_APLICACAO_BATCH })
public class ValidatorFactoryConfig {

	@Bean
	public LocalValidatorFactoryBean validatorFactory() {
		return new LocalValidatorFactoryBean();
	}

}
