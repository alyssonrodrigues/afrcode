package br.com.afrcode.arquitetura.spring.config.util.verificadores;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import br.com.afrcode.arquitetura.spring.anotacoes.Servico;
import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Configurações para scanner de classpath para componentes Dao.
 * 
 * Em uso em testes de aderência e conformidade.
 * 
 * 
 */
@Profile(Profiles.PROFILE_TU)
@Configuration
public class ServicoAnnotationClasspathScannerConfig {

    @Bean
    public ClassPathScanningCandidateComponentScanner servicoAnnotationClasspathScanner() {
        ClassPathScanningCandidateComponentScanner servicoAnnotationClasspathScanner =
                new ClassPathScanningCandidateComponentScanner();
        servicoAnnotationClasspathScanner.addIncludeFilter(new AnnotationTypeFilter(Servico.class, false));
        return servicoAnnotationClasspathScanner;
    }

}
