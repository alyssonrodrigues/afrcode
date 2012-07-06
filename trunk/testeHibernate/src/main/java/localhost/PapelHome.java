package localhost;

// Generated 27/09/2011 10:38:52 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import localhost.util.SessionFactoryUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Papel.
 * @see localhost.Papel
 * @author Hibernate Tools
 */
public class PapelHome {

	private static final Log log = LogFactory.getLog(PapelHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();
	
	private final Session session = getSession();

	protected SessionFactory getSessionFactory() {
		return SessionFactoryUtil.getSessionFactoryUtil().getSessionFactory();
	}
	
	protected Session getSession() {
		return getSessionFactory().openSession();
	}

	public void persist(Papel transientInstance) {
		log.debug("persisting Papel instance");
		try {
			session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Papel instance) {
		log.debug("attaching dirty Papel instance");
		try {
			session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Papel instance) {
		log.debug("attaching clean Papel instance");
		try {
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Papel persistentInstance) {
		log.debug("deleting Papel instance");
		try {
			session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Papel merge(Papel detachedInstance) {
		log.debug("merging Papel instance");
		try {
			Papel result = (Papel) session.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Papel findById(long id) {
		log.debug("getting Papel instance with id: " + id);
		try {
			Papel instance = (Papel) session.get(
					"localhost.Papel", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Papel instance) {
		log.debug("finding Papel instance by example");
		try {
			List results = session.createCriteria("localhost.Papel")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
