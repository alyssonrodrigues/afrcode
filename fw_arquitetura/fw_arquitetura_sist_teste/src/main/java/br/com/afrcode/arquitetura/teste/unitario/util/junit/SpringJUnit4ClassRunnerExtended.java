package br.com.afrcode.arquitetura.teste.unitario.util.junit;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Classe respons�vel por sobrecarregar o m�todo
 * withPotentialRepeat(FrameworkMethod, Object, Statement) da super classe
 * SpringJUnit4ClassRunner, alterando o Statement de retorno para
 * SpringRepeatExtended. Este Statement estende o SpringRepeat com
 * funcionalidades de registros de tempos de execu��o para posteriores an�lises
 * de desempenho.
 * 
 * 
 */
public class SpringJUnit4ClassRunnerExtended extends SpringJUnit4ClassRunner {

    public SpringJUnit4ClassRunnerExtended(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected Statement withPotentialRepeat(FrameworkMethod frameworkMethod, Object testInstance, Statement next) {
        Repeat repeatAnnotation = frameworkMethod.getAnnotation(Repeat.class);
        int repeat = (repeatAnnotation == null ? 1 : repeatAnnotation.value());
        return new SpringRepeatExtended(next, frameworkMethod.getMethod(), repeat);
    }

}
