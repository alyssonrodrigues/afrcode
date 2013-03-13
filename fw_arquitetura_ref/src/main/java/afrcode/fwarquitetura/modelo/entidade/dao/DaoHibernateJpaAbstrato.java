package afrcode.fwarquitetura.modelo.entidade.dao;

import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;

import afrcode.fwarquitetura.modelo.entidade.IEntidade;

/**
 * Subclasse de {@link DaoJpaAbstrato} cujo objetivo é expor serviços específicos da solução Hibernate JPA.
 * 
 * Pretende-se que esta seja a superclasse de todos os Daos disponíveis.
 * 
 * @author alyssonfr
 * 
 * @param <TIPOID> Tipo do ID (Long, Integer, String, etc.)
 * @param <TIPOENTIDADE> Subtipo de {@link IEntidade}
 */
public abstract class DaoHibernateJpaAbstrato<TIPOID extends Comparable<TIPOID>, TIPOENTIDADE extends IEntidade<TIPOID>> extends DaoJpaAbstrato<TIPOID, TIPOENTIDADE> {

    /**
     * Método de acesso ao HibernateEntityManager associado ao DAO via injeção de dependência.
     * Ver {@link DaoJpaAbstrato#getEntityManager()}.
     * 
     * @return
     */
    protected HibernateEntityManager getHibernateEntityManager() {
        return (HibernateEntityManager) getEntityManager();
    }

    /**
     * Método de acesso a {@link Session} do Hibernate para uso de funcionalidades específicas do Hibernate.
     * 
     * ATENÇÃO: Deve-se dar preferência ao uso dos recursos JPA disponíveis via {@link #getEntityManager()}.
     * 
     * @return
     */
    protected Session getHibernateSession() {
        return getHibernateEntityManager().getSession();
    }

}
