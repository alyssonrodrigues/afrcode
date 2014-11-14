package br.com.afrcode.arquitetura.util.verificadores;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;

import br.com.afrcode.arquitetura.is.modelo.ejb.objetoemmemoria.service.DaoUmObjetoEmMemoria;
import br.com.afrcode.arquitetura.is.modelo.rmi.objetoemmemoria.service.ServicoConsultaUmObjetoEmMemoriaRmi;
import br.com.afrcode.arquitetura.is.util.jms.JmsExceptionListener;
import br.com.afrcode.arquitetura.is.util.jms.QueueListener;
import br.com.afrcode.arquitetura.is.util.jms.QueueSender;
import br.com.afrcode.arquitetura.spring.anotacoes.Componente;
import br.com.afrcode.arquitetura.spring.anotacoes.Dao;
import br.com.afrcode.arquitetura.spring.anotacoes.Povoador;
import br.com.afrcode.arquitetura.spring.anotacoes.Servico;
import br.com.afrcode.arquitetura.spring.config.util.verificadores.ClassPathScanningCandidateComponentScanner;
import br.com.afrcode.arquitetura.spring.config.util.verificadores.ConstantesPadroes;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;
import br.com.afrcode.arquitetura.teste.unitario.util.logging.ComponentLoggingAspect;
import br.com.afrcode.arquitetura.util.contexto.ApplicationContextUtils;
import br.com.afrcode.arquitetura.util.dao.ExecutorTUCRUDDaoUtil;
import br.com.afrcode.arquitetura.util.hsqldb.HsqldbSchemaUtil;
import br.com.afrcode.arquitetura.util.hsqldb.HsqldbUtil;

public class VerificadorUsoSpringAnnotationsTest extends
		AbstractCasoTesteEmMemoria {
	@Qualifier("springAnnotationsClasspathScanner")
	@Autowired
	private ClassPathScanningCandidateComponentScanner springAnnotationsClasspathScanner;

	/**
	 * Método de verificação de regra arquitetural: "Todo componente Spring deve
	 * fazer uso das anotações previstas - Componente, Servico, Dao ou
	 * Povoador".
	 */
	@Test
	public void verificarTodoBeanSpringUsaAnotacaoPrevistaSpring() {
		Set<BeanDefinition> beansSpring = springAnnotationsClasspathScanner
				.findCandidateComponents(ConstantesPadroes.BASE_PACKAGE);

		List<String> anotacoesSpring = Arrays.asList(new String[] {
				Componente.class.getSimpleName(),
				Servico.class.getSimpleName(), Dao.class.getSimpleName(),
				Povoador.class.getSimpleName() });

		List<String> excecoesARegra = Arrays.asList(new String[] {
				ServicoConsultaUmObjetoEmMemoriaRmi.class.getName(),
				HsqldbUtil.class.getName(), HsqldbSchemaUtil.class.getName(),
				DaoUmObjetoEmMemoria.class.getName(),
				ComponentLoggingAspect.class.getName(),
				ApplicationContextUtils.class.getName(),
				ExecutorTUCRUDDaoUtil.class.getName(),
				QueueSender.class.getName(), QueueListener.class.getName(),
				JmsExceptionListener.class.getName() });

		Set<String> beansSpringSemAnotacao = new HashSet<String>();
		for (BeanDefinition beanSpring : beansSpring) {
			if (!excecoesARegra.contains(beanSpring.getBeanClassName())) {
				beansSpringSemAnotacao.add(beanSpring.getBeanClassName());
			}
		}

		Assert.assertTrue(
				"As anotações Spring previstas sã: "
						+ StringUtils.join(anotacoesSpring, ",")
						+ ". "
						+ "Os seguintes beans Spring não fazem uso destas anotações: "
						+ StringUtils.join(beansSpringSemAnotacao, ","),
				beansSpringSemAnotacao.isEmpty());
	}
}
