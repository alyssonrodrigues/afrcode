package br.com.afrcode.arquitetura.is.modelo.ejb.objetoemmemoria;

import java.util.Collection;

import javax.ejb.EJB;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.afrcode.arquitetura.is.modelo.ejb.objetoemmemoria.service.DaoUmObjetoEmMemoria;
import br.com.afrcode.arquitetura.is.modelo.ejb.objetoemmemoria.service.IServicoConsultaUmObjetoEmMemoria;
import br.com.afrcode.arquitetura.is.modelo.ejb.objetoemmemoria.service.UmObjetoEmMemoria;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteSemJpaEJta;

public class ServicoConsultaUmObjetoEmMemoriaBeanTest extends
		AbstractCasoTesteSemJpaEJta {
	private static final Logger LOG = Logger
			.getLogger(ServicoConsultaUmObjetoEmMemoriaBeanTest.class);

	@Autowired
	private StopWatch stopWatch;

	@EJB
	private IServicoConsultaUmObjetoEmMemoria servicoConsultaUmObjetoEmMemoria;

	@Test
	public void testarListar() {
		stopWatch.start();

		Collection<UmObjetoEmMemoria> objs = servicoConsultaUmObjetoEmMemoria
				.listar();

		stopWatch.stop();

		LOG.info("testarListar: " + stopWatch.toString());
		stopWatch.reset();

		Assert.assertNotNull("A coleção de objetos não deveria ser nula!", objs);
		Assert.assertTrue("A coleção de objetos não deveria estar vazia!",
				!objs.isEmpty());
		Assert.assertTrue("A coleção de objetos deveria ter "
				+ DaoUmObjetoEmMemoria.NUM_OBJS_CRIADOS + " objetos!",
				objs.size() == DaoUmObjetoEmMemoria.NUM_OBJS_CRIADOS);
	}

	@Test
	public void testarRecuperarPorId() {
		Long id = 1L;

		stopWatch.start();

		UmObjetoEmMemoria umObj = servicoConsultaUmObjetoEmMemoria
				.recuperarPorId(id);

		stopWatch.stop();

		LOG.info("testarRecuperarPorId: " + stopWatch.toString());
		stopWatch.reset();

		Assert.assertNotNull("O objeto não deveria ser nulo!", umObj);
		Assert.assertTrue("O id do objeto retornado é diferente do esperado!",
				id.equals(umObj.getId()));
	}
}
