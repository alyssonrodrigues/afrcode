package br.com.afrcode.arquitetura.spring.config.util.verificadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Configurações para scanner de classpath para componentes de Teste sobre
 * Componentes.
 * 
 * Em uso em testes de aderência e conformidade.
 * 
 * 
 */
@Profile(Profiles.PROFILE_TU)
@Configuration
public class ComponenteTesteClasspathScannerConfig {

	@Bean
	public ClassPathScanningCandidateComponentScanner componenteTesteClasspathScanner() {
		ClassPathScanningCandidateComponentScanner componenteTesteClasspathScanner = new ClassPathScanningCandidateComponentScanner();
		for (String regExpInclude : ConstantesPadroes.REGS_EXP_TESTES_COMPONENTE) {
			componenteTesteClasspathScanner
					.addIncludeFilter(new RegexPatternTypeFilter(Pattern
							.compile(regExpInclude)));
		}
		List<String> regsExpExclude = new ArrayList<String>();
		regsExpExclude.addAll(Arrays
				.asList(ConstantesPadroes.REGS_EXP_TESTES_DAO));
		regsExpExclude.addAll(Arrays
				.asList(ConstantesPadroes.REGS_EXP_TESTES_SERVICO));
		for (String regExpExclude : regsExpExclude) {
			componenteTesteClasspathScanner
					.addExcludeFilter(new RegexPatternTypeFilter(Pattern
							.compile(regExpExclude)));
		}

		return componenteTesteClasspathScanner;
	}

}
