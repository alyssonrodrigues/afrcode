package br.com.afrcode.arquitetura.spring.config.util.verificadores;

import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Configurações para scanner de classpath para componentes de Classes de Teste.
 * 
 * Em uso em testes de aderência e conformidade.
 * 
 * 
 */
@Profile(Profiles.PROFILE_TU)
@Configuration
public class ClasseTesteClasspathScannerConfig {

    @Bean
    public ClassPathScanningCandidateComponentScanner classeTesteClasspathScanner() {
        ClassPathScanningCandidateComponentScanner classeTesteClasspathScanner =
                new ClassPathScanningCandidateComponentScanner();
        for (String regExpInclude : ConstantesPadroes.REGS_EXP_TESTES_COMPONENTE) {
            classeTesteClasspathScanner.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(regExpInclude)));
        }
        return classeTesteClasspathScanner;
    }

}
