package br.com.afrcode.arquitetura.util.verificadores;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Suite de verificadores de aderência para camada de negócio.
 * 
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ VerificadorComponenteComTUTest.class,
		VerificadorDaoComTUTest.class, VerificadorEntityComDaoTest.class,
		VerificadorServicoComTUTest.class,
		VerificadorSuperclasseClassesTUTest.class,
		VerificadorUsoSpringAnnotationsTest.class })
public class SuiteVerificadoresNegocioArqSist {

}
