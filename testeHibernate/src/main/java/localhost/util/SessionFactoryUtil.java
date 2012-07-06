package localhost.util;

import java.net.URL;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class SessionFactoryUtil {
	private SessionFactory sessionFactory = null;
	private static SessionFactoryUtil instance = null;
	
	private SessionFactoryUtil() {
		sessionFactory = initSessionFactory();
	}
	
	public static SessionFactoryUtil getSessionFactoryUtil() {
		if (instance == null) {
			synchronized (SessionFactoryUtil.class) {
				if (instance == null) {
					instance = new SessionFactoryUtil();
				}
			}
		}
		return instance;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	private SessionFactory initSessionFactory() {
		AnnotationConfiguration ac = new AnnotationConfiguration();
		URL hibernatecfgURL = SessionFactoryUtil.class.
			getResource("/hibernate.cfg.xml");
		ac.configure(hibernatecfgURL);
		SessionFactory sessionFactory =	ac.buildSessionFactory();
		return sessionFactory;
	}

}
