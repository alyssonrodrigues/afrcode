package localhost;

// Generated 27/09/2011 12:16:47 by Hibernate Tools 3.4.0.CR1

import localhost.util.SessionFactoryUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Chamado.
 * @see localhost.Chamado
 * @author Hibernate Tools
 */
public class ChamadoHome {

	private static final Log log = LogFactory.getLog(ChamadoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();
	
	private final Session session = getSession();

	protected SessionFactory getSessionFactory() {
		return SessionFactoryUtil.getSessionFactoryUtil().getSessionFactory();
	}
	
	protected Session getSession() {
		return getSessionFactory().openSession();
	}

	public void persist(Chamado transientInstance) {
		log.debug("persisting Chamado instance");
		try {
			session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Chamado persistentInstance) {
		log.debug("removing Chamado instance");
		try {
			session.delete(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Chamado merge(Chamado detachedInstance) {
		log.debug("merging Chamado instance");
		try {
			Chamado result = (Chamado) session.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Chamado findById(Long id) {
		log.debug("getting Chamado instance with id: " + id);
		try {
			Chamado instance = (Chamado) session.get(Chamado.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
