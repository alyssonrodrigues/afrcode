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

public class VerificadorServicoComTUTest extends AbstractCasoTesteEmMemoria {

	@Qualifier("servicoAnnotationClasspathScanner")
	@Autowired
	private ClassPathScanningCandidateComponentScanner servicoAnnotationClasspathScanner;

	@Qualifier("servicoTesteClasspathScanner")
	@Autowired
	private ClassPathScanningCandidateComponentScanner servicoTesteClasspathScanner;

	/**
	 * Método de verificação de regra arquitetural:
	 * "Para todo Servico deve existir uma classe de Teste.".
	 */
	@Test
	public void verificarParaTodoServicoHaUmaClasseTU() {
		Set<BeanDefinition> beansServico = servicoAnnotationClasspathScanner
				.findCandidateComponents(ConstantesPadroes.BASE_PACKAGE);
		Set<BeanDefinition> beansTesteServico = servicoTesteClasspathScanner
				.findCandidateComponents(ConstantesPadroes.BASE_PACKAGE);

		Map<String, BeanDefinition> testesServicos = new HashMap<String, BeanDefinition>();
		for (BeanDefinition beanTesteServico : beansTesteServico) {
			String testeServicoClassName = beanTesteServico.getBeanClassName();
			String testeServicoSimpleClassName = testeServicoClassName
					.substring(testeServicoClassName.lastIndexOf(".") + 1);
			testesServicos.put(testeServicoSimpleClassName, beanTesteServico);
		}

		Set<String> servicosSemTeste = new HashSet<String>();
		for (BeanDefinition beanServico : beansServico) {
			String servicoClassName = beanServico.getBeanClassName();
			String servicoSimpleClassName = servicoClassName
					.substring(servicoClassName.lastIndexOf(".") + 1);
			String testeServicoSimpleClassName = ConstantesPadroes.PREFIXO_TESTE
					+ servicoSimpleClassName;
			BeanDefinition servicoBeanTesteServico = testesServicos
					.get(testeServicoSimpleClassName);
			if (servicoBeanTesteServico == null) {
				testeServicoSimpleClassName = servicoSimpleClassName
						+ ConstantesPadroes.SUFIXO_TESTE;
				servicoBeanTesteServico = testesServicos
						.get(testeServicoSimpleClassName);
				if (servicoBeanTesteServico == null) {
					servicosSemTeste.add(beanServico.getBeanClassName());
				}
			}
		}

		Assert.assertTrue(
				"Os seguintes Serviços não possuem classe de testes implementada: "
						+ StringUtils.join(servicosSemTeste, ","),
				servicosSemTeste.isEmpty());
	}

}
