package br.com.afrcode.arquitetura.spring.config.util.verificadores;

import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

/**
 * Classe utilit�ria para scanner de classpath por beans.
 * 
 * 
 */
public class ClassPathScanningCandidateComponentScanner extends ClassPathScanningCandidateComponentProvider {

    public ClassPathScanningCandidateComponentScanner() {
        super(false);
    }

}
