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

import br.com.afrcode.arquitetura.spring.config.util.verificadores.ClassPathScanningCandidateComponentScanner;
import br.com.afrcode.arquitetura.spring.config.util.verificadores.ConstantesPadroes;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTeste;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;
import br.com.afrcode.arquitetura.util.dao.AbstractDaoObjetoPersistenteAbstratoTest;
import br.com.afrcode.arquitetura.util.dao.AbstractDaoObjetoPersistenteEmMemoriaAbstratoTest;
import br.com.afrcode.arquitetura.util.dao.AbstractDaoObjetoPersistenteSomenteLeituraAbstratoTest;

public class VerificadorDaoComTUTest extends AbstractCasoTesteEmMemoria {

	@Qualifier("daoAnnotationClasspathScanner")
	@Autowired
	private ClassPathScanningCandidateComponentScanner daoAnnotationClasspathScanner;

	@Qualifier("daoTesteClasspathScanner")
	@Autowired
	private ClassPathScanningCandidateComponentScanner daoTesteClasspathScanner;

	/**
	 * Método de verificação de regra arquitetural:
	 * "Para todo DAo deve existir uma classe de Teste.".
	 */
	@Test
	public void verificarParaTodoDaoHaUmaClasseTU() {
		Set<BeanDefinition> beansDao = daoAnnotationClasspathScanner
				.findCandidateComponents(ConstantesPadroes.BASE_PACKAGE);
		Set<BeanDefinition> beansDaoTest = daoTesteClasspathScanner
				.findCandidateComponents(ConstantesPadroes.BASE_PACKAGE);

		Map<String, BeanDefinition> daosTest = new HashMap<String, BeanDefinition>();
		for (BeanDefinition beanDaoTest : beansDaoTest) {
			String daoTestClassName = beanDaoTest.getBeanClassName();
			String daoTestSimpleClassName = daoTestClassName
					.substring(daoTestClassName.lastIndexOf(".") + 1);
			daosTest.put(daoTestSimpleClassName, beanDaoTest);
		}

		Set<String> daosSemTest = new HashSet<String>();
		for (BeanDefinition beanDao : beansDao) {
			String daoClassName = beanDao.getBeanClassName();
			String daoSimpleClassName = daoClassName.substring(daoClassName
					.lastIndexOf(".") + 1);
			String daoTestSimpleClassName = ConstantesPadroes.PREFIXO_TESTE
					+ daoSimpleClassName;
			BeanDefinition daoBeanDaoTest = daosTest
					.get(daoTestSimpleClassName);
			if (daoBeanDaoTest == null) {
				daoTestSimpleClassName = daoSimpleClassName
						+ ConstantesPadroes.SUFIXO_TESTE;
				daoBeanDaoTest = daosTest.get(daoTestSimpleClassName);
				if (daoBeanDaoTest == null) {
					daosSemTest.add(beanDao.getBeanClassName());
				}
			}
		}

		Assert.assertTrue(
				"Os seguintes DAOs não possuem classe de testes implementada: "
						+ StringUtils.join(daosSemTest, ","),
				daosSemTest.isEmpty());
	}

	/**
	 * Método de verificação de regra arquitetural: "Classes de TU de DAO devem
	 * ser subclasses de AbstractDaoObjetoPersistenteEmMemoriaAbstratoTest,
	 * AbstractDaoObjetoPersistenteAbstratoTest,
	 * AbstractDaoObjetoPersistenteSomenteLeituraAbstratoTest, CasoTeste ou
	 * CasoTesteEmMemoria".
	 * 
	 * @throws ClassNotFoundException
	 */
	@Test
	public void verificarSuperclasseDeTesteDao() throws ClassNotFoundException {
		Set<BeanDefinition> beansDaoTU = daoTesteClasspathScanner
				.findCandidateComponents(ConstantesPadroes.BASE_PACKAGE);
		List<Class<?>> classesDaoTU = Arrays.asList(new Class<?>[] {
				AbstractDaoObjetoPersistenteEmMemoriaAbstratoTest.class,
				AbstractDaoObjetoPersistenteAbstratoTest.class,
				AbstractDaoObjetoPersistenteSomenteLeituraAbstratoTest.class,
				AbstractCasoTeste.class, AbstractCasoTesteEmMemoria.class });

		List<String> classesDaoTUSemSuperclasseTu = new ArrayList<String>();
		for (BeanDefinition beanClasseTeste : beansDaoTU) {
			String daoTUName = beanClasseTeste.getBeanClassName();
			Class<?> classeDaoTU = ClassUtils.getClass(daoTUName);

			boolean isSubclasseSuperclasseDaoTU = false;
			for (Class<?> superClassDaoTU : classesDaoTU) {
				if (superClassDaoTU.isAssignableFrom(classeDaoTU)) {
					isSubclasseSuperclasseDaoTU = true;
					break;
				}
			}

			if (!isSubclasseSuperclasseDaoTU) {
				classesDaoTUSemSuperclasseTu.add(daoTUName);
			}
		}
		Assert.assertTrue(
				"Os seguintes DAOs não possuem superclasses previstas: "
						+ StringUtils.join(classesDaoTUSemSuperclasseTu, ","),
				classesDaoTUSemSuperclasseTu.isEmpty());
	}

}
