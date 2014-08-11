package br.com.afrcode.arquitetura.modelo.entidade.dao;

import org.hibernate.Session;

import br.com.afrcode.arquitetura.modelo.entidade.IEntidade;

/**
 * Subclasse de DaoJpaAbstrato cujo objetivo � expor servi�os espec�ficos da
 * solu��o Hibernate JPA.
 * 
 * Pretende-se que esta seja a superclasse de todos os Daos dispon�veis.
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
     * M�todo de acesso a Session do Hibernate para uso de funcionalidades
     * espec�ficas do Hibernate.
     * 
     * ATEN��O: Deve-se dar prefer�ncia ao uso dos recursos JPA dispon�veis via
     * getEntityManager().
     * 
     * @return
     */
    protected Session getHibernateSession() {
        return getEntityManager().unwrap(Session.class);
    }

}
