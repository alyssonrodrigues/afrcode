package afrcode.fwarquitetura.modelo.entidade.dao;

import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;

import afrcode.fwarquitetura.modelo.entidade.IEntidade;

/**
 * Subclasse de {@link DaoJpaAbstrato} cujo objetivo � expor servi�os espec�ficos da solu��o Hibernate JPA.
 * 
 * Pretende-se que esta seja a superclasse de todos os Daos dispon�veis.
 * 
 * @author alyssonfr
 * 
 * @param <TIPOID> Tipo do ID (Long, Integer, String, etc.)
 * @param <TIPOENTIDADE> Subtipo de {@link IEntidade}
 */
public abstract class DaoHibernateJpaAbstrato<TIPOID extends Comparable<TIPOID>, TIPOENTIDADE extends IEntidade<TIPOID>> extends DaoJpaAbstrato<TIPOID, TIPOENTIDADE> {

    /**
     * M�todo de acesso ao HibernateEntityManager associado ao DAO via inje��o de depend�ncia.
     * Ver {@link DaoJpaAbstrato#getEntityManager()}.
     * 
     * @return
     */
    protected HibernateEntityManager getHibernateEntityManager() {
        return (HibernateEntityManager) getEntityManager();
    }

    /**
     * M�todo de acesso a {@link Session} do Hibernate para uso de funcionalidades espec�ficas do Hibernate.
     * 
     * ATEN��O: Deve-se dar prefer�ncia ao uso dos recursos JPA dispon�veis via {@link #getEntityManager()}.
     * 
     * @return
     */
    protected Session getHibernateSession() {
        return getHibernateEntityManager().getSession();
    }

}
