package br.com.afrcode.arquitetura.util.verificadores;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Suite de verificadores de aderência para a camada web.
 * 
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({VerificadorComponenteComTUTest.class, VerificadorManagedBeanComTUTest.class,
        VerificadorSuperclasseClassesTUTest.class, VerificadorUsoSpringAnnotationsTest.class})
public class SuiteVerificadoresWebArqSist {

}
