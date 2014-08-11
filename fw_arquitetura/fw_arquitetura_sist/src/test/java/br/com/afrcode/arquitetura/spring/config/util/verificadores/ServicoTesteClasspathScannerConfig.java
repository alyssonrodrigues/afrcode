package br.com.afrcode.arquitetura.spring.config.util.verificadores;

import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Configurações para scanner de classpath para componentes de Teste sobre
 * Servicos.
 * 
 * Em uso em testes de aderência e conformidade.
 * 
 * 
 */
@Profile(Profiles.PROFILE_TU)
@Configuration
public class ServicoTesteClasspathScannerConfig {

    @Bean
    public ClassPathScanningCandidateComponentScanner servicoTesteClasspathScanner() {
        ClassPathScanningCandidateComponentScanner servicoTesteClasspathScanner =
                new ClassPathScanningCandidateComponentScanner();
        for (String regExpInclude : ConstantesPadroes.REGS_EXP_TESTES_SERVICO) {
            servicoTesteClasspathScanner.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(regExpInclude)));
        }
        return servicoTesteClasspathScanner;
    }

}
