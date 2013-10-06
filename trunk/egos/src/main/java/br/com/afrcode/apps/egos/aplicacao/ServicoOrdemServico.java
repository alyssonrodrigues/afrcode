package br.com.afrcode.apps.egos.aplicacao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.afrcode.apps.egos.dominio.OrdemServico;
import br.com.afrcode.apps.egos.dominio.dao.DaoOrdemServico;

@Service
public class ServicoOrdemServico {

	@Autowired
	@Qualifier("daoStubOrdemServico")
	private DaoOrdemServico daoOrdemServico;
	
	public Collection<OrdemServico> recuperarOrdensServicoEmAtraso(
			Calendar dataAtual) {
		Collection<OrdemServico> ordensServico = 
				daoOrdemServico.recuperarTodos();
		Collection<OrdemServico> ordensServicoEmAtraso =
				new ArrayList<OrdemServico>();
		for (OrdemServico ordemServico : ordensServico) {
			Date dataEntregaEmContrato = 
					ordemServico.getDataEntregaEmContrato();
			Calendar dt = DateUtils.toCalendar(dataEntregaEmContrato);
			Boolean concluida = ordemServico.getConcluida();
			if (!concluida && dt.before(dataAtual)) {
				ordensServicoEmAtraso.add(ordemServico);
			}
		}
		return ordensServicoEmAtraso;
	}
}
