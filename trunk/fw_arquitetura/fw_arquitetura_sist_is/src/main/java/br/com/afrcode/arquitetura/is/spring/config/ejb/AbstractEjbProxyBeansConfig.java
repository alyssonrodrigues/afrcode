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
 * Superclasse para configura��o de beans proxies de EJBs 3.1.
 * 
 * Integra��o Spring => EJB. (Beans Spring v�em EJBs)
 * 
 * @param <T>
 *            tipo de interface de neg�cio associada ao proxy EJB 3.1
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
	 * M�todo de cria��o um proxy EJB a partir do nome JNDI e da interface de
	 * neg�cio.
	 * 
	 * @param jndiName
	 *            O nome JNDI.
	 * @return O proxy EJB para o dado servi�o cujo tipo ser� o tipo
	 *         parametrizado por TIPOINTERFACENEGOCIO.
	 * @throws NamingException
	 */
	protected T criarEjb3RemoteStatelessSessionProxy(String jndiName)
			throws NamingException {
		SimpleRemoteStatelessSessionProxyFactoryBean ejbProxyFactory = new SimpleRemoteStatelessSessionProxyFactoryBean();

		// Configura��o para Proxy com alvo lazy, ou seja, o alvo do proxy (EJB)
		// s� ser� verificado quando do primeiro acesso ao
		// proxy; e n�o no startup do contexto (setLookupHomeOnStartup). Esta
		// configura��o � importante pois permite que um
		// cliente de EJBS (usu�rio de proxies) seja iniciado em parelelo ou
		// anteriormente ao hospedeiro dos EJBs (alvos).
		ejbProxyFactory.setLookupHomeOnStartup(false);

		ejbProxyFactory.setJndiName(jndiName);
		ejbProxyFactory.setResourceRef(false);
		ejbProxyFactory.setBusinessInterface(getClasseProxy());

		configurarParaTUsSeNecessario(ejbProxyFactory, jndiName);

		// A Factory de EJB3 proxies deve ser iniciada previamente para j�
		// prover inst�ncias do proxy alvo.
		ejbProxyFactory.afterPropertiesSet();

		return (T) ejbProxyFactory.getObject();
	}

	protected Class<T> getClasseProxy() {
		return classeProxy;
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

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}