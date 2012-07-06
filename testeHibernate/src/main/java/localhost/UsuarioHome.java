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
 * Home object for domain model class Usuario.
 * @see localhost.Usuario
 * @author Hibernate Tools
 */
public class UsuarioHome {

	private static final Log log = LogFactory.getLog(UsuarioHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();
	
	private final Session session = getSession();

	protected SessionFactory getSessionFactory() {
		return SessionFactoryUtil.getSessionFactoryUtil().getSessionFactory();
	}
	
	protected Session getSession() {
		return getSessionFactory().openSession();
	}
	
	public void persist(Usuario transientInstance) {
		log.debug("persisting Usuario instance");
		try {
			session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Usuario instance) {
		log.debug("attaching dirty Usuario instance");
		try {
			session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usuario instance) {
		log.debug("attaching clean Usuario instance");
		try {
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Usuario persistentInstance) {
		log.debug("deleting Usuario instance");
		try {
			session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Usuario merge(Usuario detachedInstance) {
		log.debug("merging Usuario instance");
		try {
			Usuario result = (Usuario) session.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Usuario findById(long id) {
		log.debug("getting Usuario instance with id: " + id);
		try {
			Usuario instance = (Usuario) session.get("localhost.Usuario", id);
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

	public List findByExample(Usuario instance) {
		log.debug("finding Usuario instance by example");
		try {
			List results = session.createCriteria("localhost.Usuario")
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
