package br.com.afrcode.arquitetura.spring.config.util.verificadores;

import javax.persistence.Entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Configurações para scanner de classpath para componentes Entity.
 * 
 * Em uso em testes de aderência e conformidade.
 * 
 * 
 */
@Profile(Profiles.PROFILE_TU)
@Configuration
public class EntityAnnotationClasspathScannerConfig {

	@Bean
	public ClassPathScanningCandidateComponentScanner entityAnnotationClasspathScanner() {
		ClassPathScanningCandidateComponentScanner entityAnnotationClasspathScanner = new ClassPathScanningCandidateComponentScanner();
		entityAnnotationClasspathScanner
				.addIncludeFilter(new AnnotationTypeFilter(Entity.class, false));
		return entityAnnotationClasspathScanner;
	}

}
