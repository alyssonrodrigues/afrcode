package localhost;

// Generated 27/09/2011 12:33:46 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import localhost.util.SessionFactoryUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Ocorrencia.
 * @see localhost.Ocorrencia
 * @author Hibernate Tools
 */
public class OcorrenciaHome {

	private static final Log log = LogFactory.getLog(OcorrenciaHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();
	
	private final Session session = getSession();

	protected SessionFactory getSessionFactory() {
		return SessionFactoryUtil.getSessionFactoryUtil().getSessionFactory();
	}
	
	protected Session getSession() {
		return getSessionFactory().openSession();
	}

	public void persist(Ocorrencia transientInstance) {
		log.debug("persisting Ocorrencia instance");
		try {
			session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Ocorrencia instance) {
		log.debug("attaching dirty Ocorrencia instance");
		try {
			session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Ocorrencia instance) {
		log.debug("attaching clean Ocorrencia instance");
		try {
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Ocorrencia persistentInstance) {
		log.debug("deleting Ocorrencia instance");
		try {
			session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Ocorrencia merge(Ocorrencia detachedInstance) {
		log.debug("merging Ocorrencia instance");
		try {
			Ocorrencia result = (Ocorrencia) session
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Ocorrencia findById(long id) {
		log.debug("getting Ocorrencia instance with id: " + id);
		try {
			Ocorrencia instance = (Ocorrencia) session.get("localhost.Ocorrencia", id);
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

	public List findByExample(Ocorrencia instance) {
		log.debug("finding Ocorrencia instance by example");
		try {
			List results = session
					.createCriteria("localhost.Ocorrencia")
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
