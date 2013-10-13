package br.com.afrcode.apps.egos.aplicacao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.annotation.security.RolesAllowed;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.afrcode.apps.egos.dominio.OrdemServico;
import br.com.afrcode.apps.egos.dominio.dao.DaoOrdemServico;

@Service
@Transactional
@RolesAllowed("ROLE_MANAGER")
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
