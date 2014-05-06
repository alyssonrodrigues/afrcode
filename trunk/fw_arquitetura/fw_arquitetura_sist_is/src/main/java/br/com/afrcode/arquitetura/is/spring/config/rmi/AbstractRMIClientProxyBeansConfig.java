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
 * Superclasse para configuração de beans proxies de EJBs 3.1.
 * 
 * Integração Spring => RMI. (Beans Spring vêem RMIs)
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
	 * Método de obtenção do tipo associado ao proxy.
	 */
	private void iniciar() {
		Class<?> clazz = this.getClass();
		Type superClazz = clazz.getGenericSuperclass();
		// Em geral uma classe de configuração de proxy contém apenas um
		// supertipo genérico, porém mais de um supertipo genérico
		// pode surgir na presença de aspectos associados a esta classe.
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

		// Configuração para Proxy com alvo lazy, ou seja, o alvo do proxy (RMI
		// service) só será verificado quando do primeiro
		// acesso ao proxy; e não no startup do contexto
		// (setLookupStubOnStartup).
		// Esta configuração é importante pois permite que um cliente de RMI
		// services (usuário de proxies) seja iniciado em
		// parelelo ou anteriormente ao hospedeiro dos EJBs (alvos).
		rmiProxyFactory.setLookupStubOnStartup(false);

		rmiProxyFactory.setServiceUrl("rmi://localhost/" + serviceName);
		rmiProxyFactory.setServiceInterface(getClasseProxy());
		/**
		 * Integração com o Spring Security para propagação do SecurityContext.
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

		// A Factory de RMI proxies deve ser iniciada previamente para já prover
		// instâncias do proxy alvo.
		rmiProxyFactory.afterPropertiesSet();
	}

	protected Class<T> getClasseProxy() {
		return classeProxy;
	}

}