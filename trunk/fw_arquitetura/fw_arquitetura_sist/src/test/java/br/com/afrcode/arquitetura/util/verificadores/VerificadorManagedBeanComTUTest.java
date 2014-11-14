package br.com.afrcode.arquitetura.util.verificadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;

import br.com.afrcode.arquitetura.apresentacao.managedbean.ManagedBeanAbstrato;
import br.com.afrcode.arquitetura.spring.config.util.verificadores.ClassPathScanningCandidateComponentScanner;
import br.com.afrcode.arquitetura.spring.config.util.verificadores.ConstantesPadroes;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;

public class VerificadorManagedBeanComTUTest extends AbstractCasoTesteEmMemoria {

	@Qualifier("managedBeanAnnotationClasspathScanner")
	@Autowired
	private ClassPathScanningCandidateComponentScanner managedBeanAnnotationClasspathScanner;

	@Qualifier("managedBeanTesteClasspathScanner")
	@Autowired
	private ClassPathScanningCandidateComponentScanner managedBeanTesteClasspathScanner;

	/**
	 * Método de verificação de regra arquitetural:
	 * "Para todo ManagedBean deve existir uma classe de Teste.".
	 */
	@Test
	public void verificarParaTodoMBeanHaUmaClasseTU() {
		Set<BeanDefinition> beansMBean = managedBeanAnnotationClasspathScanner
				.findCandidateComponents(ConstantesPadroes.BASE_PACKAGE);
		Set<BeanDefinition> beansTesteMBean = managedBeanTesteClasspathScanner
				.findCandidateComponents(ConstantesPadroes.BASE_PACKAGE);

		Map<String, BeanDefinition> testesMBeans = new HashMap<String, BeanDefinition>();
		for (BeanDefinition beanTesteMBean : beansTesteMBean) {
			String testeMBeanClassName = beanTesteMBean.getBeanClassName();
			String testeMBeanSimpleClassName = testeMBeanClassName
					.substring(testeMBeanClassName.lastIndexOf(".") + 1);
			testesMBeans.put(testeMBeanSimpleClassName, beanTesteMBean);
		}

		Set<String> mBeansSemTeste = new HashSet<String>();
		for (BeanDefinition beanMBean : beansMBean) {
			String mBeanClassName = beanMBean.getBeanClassName();
			String mBeanSimpleClassName = mBeanClassName
					.substring(mBeanClassName.lastIndexOf(".") + 1);
			String testeMBeanSimpleClassName = ConstantesPadroes.PREFIXO_TESTE
					+ mBeanSimpleClassName;
			BeanDefinition mBeanBeanTesteMBean = testesMBeans
					.get(testeMBeanSimpleClassName);
			if (mBeanBeanTesteMBean == null) {
				testeMBeanSimpleClassName = mBeanSimpleClassName
						+ ConstantesPadroes.SUFIXO_TESTE;
				mBeanBeanTesteMBean = testesMBeans
						.get(testeMBeanSimpleClassName);
				if (mBeanBeanTesteMBean == null) {
					mBeansSemTeste.add(beanMBean.getBeanClassName());
				}
			}
		}

		Assert.assertTrue(
				"Os seguintes ManagedBeans não possuem classe de testes implementada: "
						+ StringUtils.join(mBeansSemTeste, ","),
				mBeansSemTeste.isEmpty());
	}

	@Test
	public void verificarSuperclasseDeMBean() throws ClassNotFoundException {
		Set<BeanDefinition> beansMBean = managedBeanAnnotationClasspathScanner
				.findCandidateComponents(ConstantesPadroes.BASE_PACKAGE);
		List<Class<?>> superclassesMBean = Arrays
				.asList(new Class<?>[] { ManagedBeanAbstrato.class });

		List<String> classesMBeanSemSuperclasse = new ArrayList<String>();
		for (BeanDefinition beanMBean : beansMBean) {
			String mBeanName = beanMBean.getBeanClassName();
			Class<?> classeMBean = ClassUtils.getClass(mBeanName);

			boolean isSubclasseSuperclasseMBean = false;
			for (Class<?> superClassDaoTU : superclassesMBean) {
				if (superClassDaoTU.isAssignableFrom(classeMBean)) {
					isSubclasseSuperclasseMBean = true;
					break;
				}
			}

			if (!isSubclasseSuperclasseMBean) {
				classesMBeanSemSuperclasse.add(mBeanName);
			}
		}
		Assert.assertTrue(
				"Os seguintes MBeans não possuem superclasses previstas: "
						+ StringUtils.join(classesMBeanSemSuperclasse, ","),
				classesMBeanSemSuperclasse.isEmpty());
	}

}
