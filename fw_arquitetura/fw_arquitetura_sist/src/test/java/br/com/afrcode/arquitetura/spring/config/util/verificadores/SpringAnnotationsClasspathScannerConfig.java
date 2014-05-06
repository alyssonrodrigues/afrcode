package br.com.afrcode.arquitetura.spring.config.util.verificadores;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Configurações para scanner de classpath para componentes que violam o uso das
 * anotações Spring previstas.
 * 
 * Em uso em testes de aderência e conformidade.
 * 
 * 
 */
@Profile(Profiles.PROFILE_TU)
@Configuration
public class SpringAnnotationsClasspathScannerConfig {

	@Bean
	public ClassPathScanningCandidateComponentScanner springAnnotationsClasspathScanner() {
		ClassPathScanningCandidateComponentScanner springAnnotationsClasspathScanner = new ClassPathScanningCandidateComponentScanner();
		springAnnotationsClasspathScanner
				.addIncludeFilter(new AnnotationTypeFilter(Component.class,
						false));
		springAnnotationsClasspathScanner
				.addIncludeFilter(new AnnotationTypeFilter(Controller.class,
						false));
		springAnnotationsClasspathScanner
				.addIncludeFilter(new AnnotationTypeFilter(Repository.class,
						false));
		springAnnotationsClasspathScanner
				.addIncludeFilter(new AnnotationTypeFilter(Service.class, false));
		return springAnnotationsClasspathScanner;
	}
}
