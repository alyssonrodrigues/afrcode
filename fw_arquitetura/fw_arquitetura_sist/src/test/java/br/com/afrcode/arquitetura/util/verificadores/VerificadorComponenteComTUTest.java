package br.com.afrcode.arquitetura.util.verificadores;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import br.com.afrcode.arquitetura.util.contexto.ContextoAplicacaoWeb;

public class VerificadorComponenteComTUTest extends AbstractCasoTesteEmMemoria {
	@Qualifier("componenteAnnotationClasspathScanner")
	@Autowired
	private ClassPathScanningCandidateComponentScanner componenteAnnotationClasspathScanner;

	@Qualifier("componenteTesteClasspathScanner")
	@Autowired
	private ClassPathScanningCandidateComponentScanner componenteTesteClasspathScanner;

	/**
	 * Método de verificação de regra arquitetural:
	 * "Para todo Componente deve existir uma classe de Teste.".
	 */
	@Test
	public void verificarParaTodoComponenteHaUmaClasseTU() {
		Set<BeanDefinition> beansComponente = componenteAnnotationClasspathScanner
				.findCandidateComponents(ConstantesPadroes.BASE_PACKAGE);
		Set<BeanDefinition> beansTesteComponente = componenteTesteClasspathScanner
				.findCandidateComponents(ConstantesPadroes.BASE_PACKAGE);

		Map<String, BeanDefinition> testesComponentes = new HashMap<String, BeanDefinition>();
		for (BeanDefinition beanTesteComponente : beansTesteComponente) {
			String testeComponenteClassName = beanTesteComponente
					.getBeanClassName();
			String testeComponenteSimpleClassName = testeComponenteClassName
					.substring(testeComponenteClassName.lastIndexOf(".") + 1);
			testesComponentes.put(testeComponenteSimpleClassName,
					beanTesteComponente);
		}

		List<String> excecoesARegra = Arrays.asList(ContextoAplicacaoWeb.class
				.getName());

		Set<String> componentesSemTeste = new HashSet<String>();
		for (BeanDefinition beanComponente : beansComponente) {
			String componenteClassName = beanComponente.getBeanClassName();
			String componenteSimpleClassName = componenteClassName
					.substring(componenteClassName.lastIndexOf(".") + 1);
			String testeComponenteSimpleClassName = ConstantesPadroes.PREFIXO_TESTE
					+ componenteSimpleClassName;
			BeanDefinition componenteBeanTesteComponente = testesComponentes
					.get(testeComponenteSimpleClassName);
			if (componenteBeanTesteComponente == null) {
				testeComponenteSimpleClassName = componenteSimpleClassName
						+ ConstantesPadroes.SUFIXO_TESTE;
				componenteBeanTesteComponente = testesComponentes
						.get(testeComponenteSimpleClassName);
				if (!excecoesARegra.contains(beanComponente.getBeanClassName())
						&& componenteBeanTesteComponente == null) {
					componentesSemTeste.add(beanComponente.getBeanClassName());
				}
			}
		}

		Assert.assertTrue(
				"Os seguintes Componentes não possuem classe de testes implementada: "
						+ StringUtils.join(componentesSemTeste, ","),
				componentesSemTeste.isEmpty());
	}

}
