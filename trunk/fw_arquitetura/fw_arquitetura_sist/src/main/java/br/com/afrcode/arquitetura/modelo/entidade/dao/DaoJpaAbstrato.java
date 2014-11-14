package br.com.afrcode.arquitetura.modelo.entidade.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import br.com.afrcode.arquitetura.modelo.entidade.IEntidade;

/**
 * Implementação padrão da interface IDao.
 * 
 * 
 * @param <T>
 *            Tipo do ID (Long, Integer, String, etc.)
 * @param <E>
 *            Subtipo de IEntidade
 */
public abstract class DaoJpaAbstrato<T extends Comparable<T>, E extends IEntidade<T>>
		implements IDao<T, E> {

	protected static final Logger LOG = Logger.getLogger(DaoJpaAbstrato.class);

	/**
	 * Contexto de persistência transacional, onde ao final de uma transação os
	 * recursos envolvidos (EntityManager, Session, Connection, etc.) são
	 * descartados.
	 * 
	 * O uso do padrão OpenEntityManagerInViewFilter adia o descarte dos
	 * recursos envolvidos até o final do processamento da camada View. O uso
	 * deste padrão permite que entidades sejam usadas em MBeans JSF, EM UMA
	 * MESMA REQUISIÇÃO, sem ocorrência de exceções de detached object e/ou lazy
	 * exceptions.
	 */
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	private Class<E> classeEntidade;

	private Object classeId;

	public DaoJpaAbstrato() {
		iniciar();
	}

	/**
	 * Método de obtenção do tipo associado ao DAO via generic parameterers.
	 */
	private void iniciar() {
		Class<?> clazz = this.getClass();
		Type superClazz = clazz.getGenericSuperclass();
		// Em geral um DAO cont�m apenas um supertipo gen�rico, por�m mais de um
		// supertipo gen�rico pode surgir na presen�a de
		// aspectos associados ao DAO.
		while (!ParameterizedType.class.isAssignableFrom(superClazz.getClass())) {
			clazz = clazz.getSuperclass();
			superClazz = clazz.getGenericSuperclass();
		}
		ParameterizedType tipoParametrizado = (ParameterizedType) superClazz;
		Type[] params = tipoParametrizado.getActualTypeArguments();
		classeId = params[0];
		classeEntidade = (Class<E>) params[1];
	}

	@Override
	public E procurarPorId(T id) {
		return entityManager.find(getClasseEntidade(), id);
	}

	@Override
	public Collection<E> recuperarTodos() {
		String qlString = "from " + getClasseEntidade().getName();
		TypedQuery<E> query = entityManager.createQuery(qlString,
				getClasseEntidade());
		return query.getResultList();
	}

	@Override
	public Collection<E> recuperarTodos(int pagina, int quantidadeDeItens) {
		String qlString = "from " + getClasseEntidade().getName();
		TypedQuery<E> query = entityManager.createQuery(qlString,
				getClasseEntidade());

		int startPosition = pagina == 0 ? 0 : (pagina * quantidadeDeItens);
		query.setFirstResult(startPosition);
		query.setMaxResults(quantidadeDeItens);

		return query.getResultList();
	}

	@Override
	public Collection<E> recuperarObjetos(String qlString,
			Map<String, Object> params, int pagina, int quantidadeDeItens) {
		TypedQuery<E> query = entityManager.createQuery(qlString,
				getClasseEntidade());

		int startPosition = pagina == 0 ? 0 : (pagina * quantidadeDeItens);
		query.setFirstResult(startPosition);
		query.setMaxResults(quantidadeDeItens);

		for (Entry<String, Object> umParam : params.entrySet()) {
			query.setParameter(umParam.getKey(), umParam.getValue());
		}

		return query.getResultList();
	}

	@Override
	public void salvar(E obj) {
		obj.validarSalvamento();
		entityManager.persist(obj);
	}

	@Override
	public void salvarEmLote(Collection<E> objs, int numObjsPorLote) {
		int i = 0;
		for (E obj : objs) {
			salvar(obj);
			if (++i % numObjsPorLote == 0) {
				sincronizar();
				limparCache();
			}
		}

		if (i % numObjsPorLote != 0) {
			// �ltimo lote com numObjs < numObjsPorLote...
			sincronizar();
			limparCache();
		}
	}

	@Override
	public void excluir(E obj) {
		obj.validarExclusao();
		entityManager.remove(obj);
	}

	@Override
	public void excluirEmLote(Collection<E> objs, int numObjsPorLote) {
		int i = 0;
		for (E obj : objs) {
			excluir(obj);
			if (++i % numObjsPorLote == 0) {
				sincronizar();
				limparCache();
			}
		}

		if (i % numObjsPorLote != 0) {
			// �ltimo lote com numObjs < numObjsPorLote...
			sincronizar();
			limparCache();
		}

	}

	@Override
	public void sincronizar() {
		entityManager.flush();
	}

	@Override
	public void limparCache() {
		entityManager.clear();
	}

	/**
	 * Deve ser sobrescrito para atribuição de atributos não nulos do objeto
	 * instanciado.
	 * 
	 * @return TIPOENTIDADE
	 */
	@Override
	public E instanciarObjetoPersistivel() {
		E obj = null;
		try {
			obj = getClasseEntidade().newInstance();
			obj.preencherComValoresPersistiveis();
		} catch (InstantiationException e) {
			LOG.error("InstantiationException", e);
		} catch (IllegalAccessException e) {
			LOG.error("IllegalAccessException", e);
		}
		return obj;
	}

	/**
	 * @return the classeEntidade
	 */
	public Class<E> getClasseEntidade() {
		return classeEntidade;
	}

	/**
	 * @return the classeId
	 */
	public Object getClasseId() {
		return classeId;
	}

	/**
	 * Método de acesso ao EntityManager associado ao DAO via injeção de
	 * dependência.
	 * 
	 * @return
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
