package br.com.afrcode.arquitetura.modelo.entidade.dao;

import org.hibernate.Session;

import br.com.afrcode.arquitetura.modelo.entidade.IEntidade;

/**
 * Subclasse de DaoJpaAbstrato cujo objetivo é expor serviços específicos da
 * solução Hibernate JPA.
 * 
 * Pretende-se que esta seja a superclasse de todos os Daos disponíveis.
 * 
 * 
 * @param <T>
 *            Tipo do ID (Long, Integer, String, etc.)
 * @param <E>
 *            Subtipo de IEntidade
 */
public abstract class DaoHibernateJpaAbstrato<T extends Comparable<T>, E extends IEntidade<T>> extends
        DaoJpaAbstrato<T, E> {

    /**
     * Método de acesso a Session do Hibernate para uso de funcionalidades
     * específicas do Hibernate.
     * 
     * ATENÇÃO: Deve-se dar preferência ao uso dos recursos JPA disponíveis via
     * getEntityManager().
     * 
     * @return
     */
    protected Session getHibernateSession() {
        return getEntityManager().unwrap(Session.class);
    }

}
