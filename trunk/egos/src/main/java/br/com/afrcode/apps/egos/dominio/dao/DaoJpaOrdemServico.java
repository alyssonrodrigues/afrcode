package br.com.afrcode.apps.egos.dominio.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import br.com.afrcode.apps.egos.dominio.OrdemServico;

@Repository
@Primary
public class DaoJpaOrdemServico implements DaoOrdemServico {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void salvar(OrdemServico ordemServico) {
		entityManager.persist(ordemServico);
	}

	@Override
	public void excluir(OrdemServico ordemServico) {
		entityManager.remove(ordemServico);
	}

	@Override
	public OrdemServico recuperarOrdemServico(Long id) {
		return entityManager.find(OrdemServico.class, id);
	}

	@Override
	public Collection<OrdemServico> recuperarTodos() {
		StringBuilder qlString = new StringBuilder("from ")
				.append(OrdemServico.class.getName());
		TypedQuery<OrdemServico> r = entityManager.createQuery(
						qlString.toString(), 
						OrdemServico.class);
		return r.getResultList();
	}
}
