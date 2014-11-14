package br.com.afrcode.arquitetura.util.verificadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;

import br.com.afrcode.arquitetura.spring.config.util.verificadores.ClassPathScanningCandidateComponentScanner;
import br.com.afrcode.arquitetura.spring.config.util.verificadores.ConstantesPadroes;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTeste;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteSemJpaEJta;

public class VerificadorSuperclasseClassesTUTest extends
		AbstractCasoTesteEmMemoria {

	@Qualifier("classeTesteClasspathScanner")
	@Autowired
	private ClassPathScanningCandidateComponentScanner classeTesteClasspathScanner;

	/**
	 * Método de verificação de regra arquitetural:
	 * "Classes de TU devem ser subclasses de CasoTeste, CasoTesteEmMemoria ou CasoTesteSemJpaEJta"
	 * .
	 * 
	 * @throws ClassNotFoundException
	 */
	@Test
	public void verificarSuperclasseDeClasseTeste()
			throws ClassNotFoundException {
		Set<BeanDefinition> beansClasseTeste = classeTesteClasspathScanner
				.findCandidateComponents(ConstantesPadroes.BASE_PACKAGE);
		List<Class<?>> classesTU = Arrays.asList(new Class<?>[] {
				AbstractCasoTeste.class, AbstractCasoTesteEmMemoria.class,
				AbstractCasoTesteSemJpaEJta.class });

		List<String> classesTesteSemSuperclasseTu = new ArrayList<String>();
		for (BeanDefinition beanClasseTeste : beansClasseTeste) {
			String classeTesteName = beanClasseTeste.getBeanClassName();
			Class<?> classeTeste = ClassUtils.getClass(classeTesteName);

			boolean isSubclasseSuperclasseTU = false;
			for (Class<?> superClassTU : classesTU) {
				if (superClassTU.isAssignableFrom(classeTeste)) {
					isSubclasseSuperclasseTU = true;
					break;
				}
			}

			if (!isSubclasseSuperclasseTU) {
				classesTesteSemSuperclasseTu.add(classeTesteName);
			}
		}
		Assert.assertTrue(
				"Os seguintes TUs não possuem superclasses previstas: "
						+ StringUtils.join(classesTesteSemSuperclasseTu, ","),
				classesTesteSemSuperclasseTu.isEmpty());
	}

}
