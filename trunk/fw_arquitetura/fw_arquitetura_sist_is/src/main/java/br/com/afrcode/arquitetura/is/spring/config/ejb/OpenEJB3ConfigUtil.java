package br.com.afrcode.arquitetura.is.spring.config.ejb;

import java.net.URL;
import java.util.Properties;

import javax.naming.Context;

import org.apache.commons.lang.Validate;
import org.springframework.ejb.access.SimpleRemoteStatelessSessionProxyFactoryBean;

public class OpenEJB3ConfigUtil {

	private static final String MSG_DEP_ARQ_SIST_IS_TESTS = "Vefique em seu pom.xml a dependência ADICIONAL para "
			+ "fw_arquitetura_sist_is, scope test, classifier tests!";
	private static final String OPENEJB_CONF_FILE = "openejb.conf.file";
	private static final String JAVA_SECURITY_AUTH_LOGIN_CONFIG = "java.security.auth.login.config";

	private OpenEJB3ConfigUtil() {

	}

	public static void configurarParaTUs(
			SimpleRemoteStatelessSessionProxyFactoryBean ejbProxyFactory,
			String jndiName) {
		String ejbJndiNameParaTU = jndiName;
		int posInicialBeanName = ejbJndiNameParaTU.lastIndexOf('/');
		if (posInicialBeanName != -1) {
			ejbJndiNameParaTU = ejbJndiNameParaTU
					.substring(posInicialBeanName + 1) + "Remote";
			ejbProxyFactory.setJndiName(ejbJndiNameParaTU);
		}

		Properties props = new Properties();
		configurarLoginModule(props);
		configurarOpenEJB(props);
		ejbProxyFactory.setJndiEnvironment(props);
	}

	private static void configurarOpenEJB(Properties props) {
		// APENAS PARA TESTES: configurações de context factory JNDI do OpenEJB.
		// O OpenEJB será iniciado preguiçosamente no primeiro acesso ao context
		// factory JNDI informado.
		// Para uso real em aplicações não é necessário informar o context
		// factory JNDI, pois o mesmo será obtido via JBoss EJB Module.
		props.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.openejb.client.LocalInitialContextFactory");
		System.setProperty("openejb.logger.external", "true");
		final ClassLoader ctxCl = Thread.currentThread()
				.getContextClassLoader();
		Validate.notNull(ctxCl, "ContextClassLoader null!");
		URL resource = ctxCl.getResource("openejb.xml");
		Validate.notNull(resource, MSG_DEP_ARQ_SIST_IS_TESTS);
		String urlOpenEjbConfigStr = resource.toExternalForm();
		System.setProperty(OPENEJB_CONF_FILE, urlOpenEjbConfigStr);
		props.put(OPENEJB_CONF_FILE, urlOpenEjbConfigStr);
	}

	private static void configurarLoginModule(Properties props) {
		final ClassLoader ctxCl = Thread.currentThread()
				.getContextClassLoader();
		// APENAS PARA TESTES: configurações de login module JAAS.
		// Para uso real em aplicações não é necessário informar o login module,
		// pois o mesmo será obtido via configuração do JBoss Security Domain
		// (SpringSecurityConfig.LOGIN_CONTEXT_NAME).
		Validate.notNull(ctxCl, "ContextClassLoader null!");
		URL resource = ctxCl.getResource("login-module-tu.config");
		Validate.notNull(resource, MSG_DEP_ARQ_SIST_IS_TESTS);
		String urlLoginConfigStr = resource.toExternalForm();
		System.setProperty(JAVA_SECURITY_AUTH_LOGIN_CONFIG, urlLoginConfigStr);
		props.put(JAVA_SECURITY_AUTH_LOGIN_CONFIG, urlLoginConfigStr);
	}
}
