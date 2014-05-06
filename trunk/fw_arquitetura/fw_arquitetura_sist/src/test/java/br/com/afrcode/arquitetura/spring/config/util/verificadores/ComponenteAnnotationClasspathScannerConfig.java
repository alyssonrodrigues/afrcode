package br.com.afrcode.arquitetura.spring.config.util.verificadores;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import br.com.afrcode.arquitetura.spring.anotacoes.Componente;
import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Configurações para scanner de classpath para componentes Componente.
 * 
 * Em uso em testes de aderência e conformidade.
 * 
 * 
 */
@Profile(Profiles.PROFILE_TU)
@Configuration
public class ComponenteAnnotationClasspathScannerConfig {

	@Bean
	public ClassPathScanningCandidateComponentScanner componenteAnnotationClasspathScanner() {
		ClassPathScanningCandidateComponentScanner componenteAnnotationClasspathScanner = new ClassPathScanningCandidateComponentScanner();
		componenteAnnotationClasspathScanner
				.addIncludeFilter(new AnnotationTypeFilter(Componente.class,
						false));
		return componenteAnnotationClasspathScanner;
	}

}
