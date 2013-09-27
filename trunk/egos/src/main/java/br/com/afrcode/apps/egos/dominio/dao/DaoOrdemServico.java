package br.com.afrcode.apps.egos.dominio.dao;

import java.util.Collection;

import br.com.afrcode.apps.egos.dominio.OrdemServico;

public interface DaoOrdemServico {
	
	void salvar(OrdemServico ordemServico);
	
	void excluir(OrdemServico ordemServico);
	
	OrdemServico recuperarOrdemServico(Long id);
	
	Collection<OrdemServico> recuperarTodos();
}
