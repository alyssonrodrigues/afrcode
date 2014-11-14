package br.com.afrcode.arquitetura.spring.config.util.verificadores;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import br.com.afrcode.arquitetura.spring.anotacoes.Dao;
import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Configurações para scanner de classpath para componentes Dao.
 * 
 * Em uso em testes de aderêcia e conformidade.
 * 
 * 
 */
@Profile(Profiles.PROFILE_TU)
@Configuration
public class DaoAnnotationClasspathScannerConfig {

	@Bean
	public ClassPathScanningCandidateComponentScanner daoAnnotationClasspathScanner() {
		ClassPathScanningCandidateComponentScanner daoAnnotationClasspathScanner = new ClassPathScanningCandidateComponentScanner();
		daoAnnotationClasspathScanner
				.addIncludeFilter(new AnnotationTypeFilter(Dao.class, false));
		return daoAnnotationClasspathScanner;
	}

}
