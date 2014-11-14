package br.com.afrcode.arquitetura.util.verificadores;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;

import br.com.afrcode.arquitetura.spring.config.util.verificadores.ClassPathScanningCandidateComponentScanner;
import br.com.afrcode.arquitetura.spring.config.util.verificadores.ConstantesPadroes;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;

public class VerificadorEntityComDaoTest extends AbstractCasoTesteEmMemoria {

	private static final String PREFIXO_DAO = "Dao";

	@Qualifier("entityAnnotationClasspathScanner")
	@Autowired
	private ClassPathScanningCandidateComponentScanner entityAnnotationClasspathScanner;

	@Qualifier("daoAnnotationClasspathScanner")
	@Autowired
	private ClassPathScanningCandidateComponentScanner daoAnnotationClasspathScanner;

	/**
	 * Método de verificação de regra arquitetural:
	 * "Para toda Entidade deve existir um DAO.".
	 */
	@Test
	public void verificarParaTodaEntidadeHaUmDao() {
		Set<BeanDefinition> beansEntity = entityAnnotationClasspathScanner
				.findCandidateComponents(ConstantesPadroes.BASE_PACKAGE);
		Set<BeanDefinition> beansDao = daoAnnotationClasspathScanner
				.findCandidateComponents(ConstantesPadroes.BASE_PACKAGE);

		Map<String, BeanDefinition> daos = new HashMap<String, BeanDefinition>();
		for (BeanDefinition beanDao : beansDao) {
			String daoClassName = beanDao.getBeanClassName();
			String daoSimpleClassName = daoClassName.substring(daoClassName
					.lastIndexOf(".") + 1);
			daos.put(daoSimpleClassName, beanDao);
		}

		Set<String> entitiesSemDao = new HashSet<String>();
		for (BeanDefinition beanEntity : beansEntity) {
			String entityClassName = beanEntity.getBeanClassName();
			String entitySimpleClassName = entityClassName
					.substring(entityClassName.lastIndexOf(".") + 1);
			String entityDaoSimpleClassName = PREFIXO_DAO
					+ entitySimpleClassName;
			BeanDefinition entityBeanDao = daos.get(entityDaoSimpleClassName);
			if (entityBeanDao == null) {
				entitiesSemDao.add(beanEntity.getBeanClassName());
			}
		}

		Assert.assertTrue(
				"As seguintes Entidades não possuem Dao implementado: "
						+ StringUtils.join(entitiesSemDao, ","),
				entitiesSemDao.isEmpty());
	}

}
