package br.com.afrcode.arquitetura.spring.config.util.verificadores;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Configurações para scanner de classpath para componentes ManagedBean.
 * 
 * Em uso em testes de aderência e conformidade.
 * 
 * 
 */
@Profile(Profiles.PROFILE_TU)
@Configuration
public class ManagedBeanAnnotationClasspathScannerConfig {

	@Bean
	public ClassPathScanningCandidateComponentScanner managedBeanAnnotationClasspathScanner() {
		ClassPathScanningCandidateComponentScanner managedBeanAnnotationClasspathScanner = new ClassPathScanningCandidateComponentScanner();
		managedBeanAnnotationClasspathScanner
				.addIncludeFilter(new AnnotationTypeFilter(ManagedBean.class,
						false));
		return managedBeanAnnotationClasspathScanner;
	}

}
