package br.com.afrcode.apps.egos.dominio.dao;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import br.com.afrcode.apps.egos.dominio.OrdemServico;

@Repository
public class DaoOrdemServicoStub implements DaoOrdemServico {
	private static final Map<Long, OrdemServico> ordensServico =
			new ConcurrentHashMap<Long, OrdemServico>();

	@Override
	public void salvar(OrdemServico ordemServico) {
		Long id = Long.valueOf(ordemServico.hashCode());
		ordemServico.setId(id);
		ordensServico.put(id, ordemServico);
	}

	@Override
	public void excluir(OrdemServico ordemServico) {
		ordensServico.remove(ordemServico.getId());
	}

	@Override
	public OrdemServico recuperarOrdemServico(Long id) {
		return ordensServico.get(id);
	}

	@Override
	public Collection<OrdemServico> recuperarTodos() {
		return ordensServico.values();
	}
}
