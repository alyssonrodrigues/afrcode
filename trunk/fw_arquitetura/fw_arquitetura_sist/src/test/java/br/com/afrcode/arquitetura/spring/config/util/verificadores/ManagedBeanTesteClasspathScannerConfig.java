package br.com.afrcode.arquitetura.spring.config.util.verificadores;

import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Configurações para scanner de classpath para componentes de Teste sobre
 * ManagedBeans.
 * 
 * Em uso em testes de aderência e conformidade.
 * 
 * 
 */
@Profile(Profiles.PROFILE_TU)
@Configuration
public class ManagedBeanTesteClasspathScannerConfig {

	@Bean
	public ClassPathScanningCandidateComponentScanner managedBeanTesteClasspathScanner() {
		ClassPathScanningCandidateComponentScanner managedBeanTesteClasspathScanner = new ClassPathScanningCandidateComponentScanner();
		for (String regExpInclude : ConstantesPadroes.REGS_EXP_TESTES_MBEAN) {
			managedBeanTesteClasspathScanner
					.addIncludeFilter(new RegexPatternTypeFilter(Pattern
							.compile(regExpInclude)));
		}
		return managedBeanTesteClasspathScanner;
	}

}
