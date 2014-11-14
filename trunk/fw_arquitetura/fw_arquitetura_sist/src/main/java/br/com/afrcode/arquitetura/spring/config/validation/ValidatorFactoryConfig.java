package br.com.afrcode.arquitetura.spring.config.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Configuração para tornar disponível via DI beans do tipo Validator,
 * necessários a rotinas de validação via Beans Validations.
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
