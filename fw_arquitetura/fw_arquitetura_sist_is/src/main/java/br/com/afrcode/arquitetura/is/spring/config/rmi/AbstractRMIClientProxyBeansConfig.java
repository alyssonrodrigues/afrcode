package br.com.afrcode.arquitetura.is.spring.config.rmi;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.security.remoting.rmi.ContextPropagatingRemoteInvocationFactory;

import br.com.afrcode.arquitetura.is.spring.config.util.ProfilesIS;

/**
 * Superclasse para configura��o de beans proxies de EJBs 3.1.
 * 
 * Integra��o Spring => RMI. (Beans Spring v�em RMIs)
 * 
 * 
 */
public abstract class AbstractRMIClientProxyBeansConfig<T> implements
		ApplicationContextAware {

	private ApplicationContext applicationContext;

	private Class<T> classeProxy;

	public AbstractRMIClientProxyBeansConfig() {
		iniciar();
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * M�todo de obten��o do tipo associado ao proxy.
	 */
	private void iniciar() {
		Class<?> clazz = this.getClass();
		Type superClazz = clazz.getGenericSuperclass();
		// Em geral uma classe de configura��o de proxy cont�m apenas um
		// supertipo gen�rico, por�m mais de um supertipo gen�rico
		// pode surgir na presen�a de aspectos associados a esta classe.
		while (!ParameterizedType.class.isAssignableFrom(superClazz.getClass())) {
			clazz = clazz.getSuperclass();
			superClazz = clazz.getGenericSuperclass();
		}
		ParameterizedType tipoParametrizado = (ParameterizedType) superClazz;
		Type[] params = tipoParametrizado.getActualTypeArguments();
		classeProxy = (Class<T>) params[0];
	}

	protected boolean isProfileTUAtivo() {
		boolean profileTUAtivo = false;

		List<String> profilesAtivos = Arrays.asList(applicationContext
				.getEnvironment().getActiveProfiles());
		profileTUAtivo = profilesAtivos.contains(ProfilesIS.PROFILE_TU);

		return profileTUAtivo;
	}

	protected T criarRmiProxy(String serviceName) {
		RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();

		// Configura��o para Proxy com alvo lazy, ou seja, o alvo do proxy (RMI
		// service) s� ser� verificado quando do primeiro
		// acesso ao proxy; e n�o no startup do contexto
		// (setLookupStubOnStartup).
		// Esta configura��o � importante pois permite que um cliente de RMI
		// services (usu�rio de proxies) seja iniciado em
		// parelelo ou anteriormente ao hospedeiro dos EJBs (alvos).
		rmiProxyFactory.setLookupStubOnStartup(false);

		rmiProxyFactory.setServiceUrl("rmi://localhost/" + serviceName);
		rmiProxyFactory.setServiceInterface(getClasseProxy());
		/**
		 * Integra��o com o Spring Security para propaga��o do SecurityContext.
		 * Ver {@code ContextPropagatingRemoteInvocation}.
		 */
		rmiProxyFactory
				.setRemoteInvocationFactory(new ContextPropagatingRemoteInvocationFactory());

		configurarParaTUsSeNecessario(rmiProxyFactory);

		T rmiProxy = (T) rmiProxyFactory.getObject();

		return rmiProxy;
	}

	private void configurarParaTUsSeNecessario(
			RmiProxyFactoryBean rmiProxyFactory) {

		if (!isProfileTUAtivo()) {
			return;
		}

		// A Factory de RMI proxies deve ser iniciada previamente para j� prover
		// inst�ncias do proxy alvo.
		rmiProxyFactory.afterPropertiesSet();
	}

	protected Class<T> getClasseProxy() {
		return classeProxy;
	}

}