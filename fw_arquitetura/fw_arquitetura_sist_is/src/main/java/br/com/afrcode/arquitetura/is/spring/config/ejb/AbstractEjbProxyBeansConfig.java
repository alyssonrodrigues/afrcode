package br.com.afrcode.arquitetura.is.spring.config.ejb;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.ejb.access.SimpleRemoteStatelessSessionProxyFactoryBean;

import br.com.afrcode.arquitetura.is.spring.config.util.ProfilesIS;

/**
 * Superclasse para configuração de beans proxies de EJBs 3.1.
 * 
 * Integração Spring => EJB. (Beans Spring vêem EJBs)
 * 
 * @param <T>
 *            tipo de interface de negócio associada ao proxy EJB 3.1
 */
public abstract class AbstractEjbProxyBeansConfig<T> implements
		ApplicationContextAware {

	private ApplicationContext applicationContext;

	private Class<T> classeProxy;

	public AbstractEjbProxyBeansConfig() {
		iniciar();
	}

	private void configurarParaTUsSeNecessario(
			SimpleRemoteStatelessSessionProxyFactoryBean ejbProxyFactory,
			String jndiName) {
		if (isProfileTUAtivo()) {
			OpenEJB3ConfigUtil.configurarParaTUs(ejbProxyFactory, jndiName);
		}
	}

	/**
	 * Método de criação um proxy EJB a partir do nome JNDI e da interface de
	 * negócio.
	 * 
	 * @param jndiName
	 *            O nome JNDI.
	 * @return O proxy EJB para o dado serviço cujo tipo será o tipo
	 *         parametrizado por TIPOINTERFACENEGOCIO.
	 * @throws NamingException
	 */
	protected T criarEjb3RemoteStatelessSessionProxy(String jndiName)
			throws NamingException {
		SimpleRemoteStatelessSessionProxyFactoryBean ejbProxyFactory = new SimpleRemoteStatelessSessionProxyFactoryBean();

		// Configuração para Proxy com alvo lazy, ou seja, o alvo do proxy (EJB)
		// só será verificado quando do primeiro acesso ao
		// proxy; e não no startup do contexto (setLookupHomeOnStartup). Esta
		// configuração é importante pois permite que um
		// cliente de EJBS (usuário de proxies) seja iniciado em parelelo ou
		// anteriormente ao hospedeiro dos EJBs (alvos).
		ejbProxyFactory.setLookupHomeOnStartup(false);

		ejbProxyFactory.setJndiName(jndiName);
		ejbProxyFactory.setResourceRef(false);
		ejbProxyFactory.setBusinessInterface(getClasseProxy());

		configurarParaTUsSeNecessario(ejbProxyFactory, jndiName);

		// A Factory de EJB3 proxies deve ser iniciada previamente para já
		// prover instâncias do proxy alvo.
		ejbProxyFactory.afterPropertiesSet();

		return (T) ejbProxyFactory.getObject();
	}

	protected Class<T> getClasseProxy() {
		return classeProxy;
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

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}