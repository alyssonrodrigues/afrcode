package br.com.afrcode.apps.egos.aplicacao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.afrcode.apps.egos.dominio.OrdemServico;
import br.com.afrcode.apps.egos.dominio.dao.DaoOrdemServico;

@Service
public class ServicoOrdemServico {

	@Autowired
	private DaoOrdemServico daoOrdemServico;
	
	public Collection<OrdemServico> recuperarOrdensServicoEmAtraso(
			Calendar dataAtual) {
		Collection<OrdemServico> ordensServico = 
				daoOrdemServico.recuperarTodos();
		Collection<OrdemServico> ordensServicoEmAtraso =
				new ArrayList<OrdemServico>();
		for (OrdemServico ordemServico : ordensServico) {
			Calendar dataEntregaEmContrato = 
					ordemServico.getDataEntregaEmContrato();
			Boolean concluida = ordemServico.getConcluida();
			if (!concluida 
					&& dataEntregaEmContrato.before(dataAtual)) {
				ordensServicoEmAtraso.add(ordemServico);
			}
		}
		return ordensServicoEmAtraso;
	}
}
