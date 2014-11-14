package br.com.afrcode.arquitetura.is.modelo.rmi.objetoemmemoria.service;

import java.util.Collection;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.afrcode.arquitetura.is.modelo.ejb.objetoemmemoria.service.DaoUmObjetoEmMemoria;
import br.com.afrcode.arquitetura.is.modelo.ejb.objetoemmemoria.service.UmObjetoEmMemoria;

/**
 * Implementação de serviço exposto (IServicoConsultaUmObjetoEmMemoriaRmi) via
 * RMI para fins de testes sobre o Spring remoting.
 * 
 * 
 */
@Component
public class ServicoConsultaUmObjetoEmMemoriaRmi implements
		IServicoConsultaUmObjetoEmMemoriaRmi {
	private static final Logger LOG = Logger
			.getLogger(ServicoConsultaUmObjetoEmMemoriaRmi.class);

	@Autowired
	private DaoUmObjetoEmMemoria daoUmObjetoPersistente;

	@Autowired
	private StopWatch stopWatch;

	@Override
	public Collection<UmObjetoEmMemoria> listar() {
		stopWatch.start();

		Collection<UmObjetoEmMemoria> objs = daoUmObjetoPersistente
				.recuperarTodos();

		stopWatch.stop();
		LOG.info("listar: " + stopWatch.toString());
		stopWatch.reset();

		return objs;
	}

	@Override
	public UmObjetoEmMemoria recuperarPorId(Long id) {
		stopWatch.start();

		UmObjetoEmMemoria obj = daoUmObjetoPersistente.procurarPorId(id);

		stopWatch.stop();
		LOG.info("recuperarPorId: " + stopWatch.toString());
		stopWatch.reset();

		return obj;
	}

}
