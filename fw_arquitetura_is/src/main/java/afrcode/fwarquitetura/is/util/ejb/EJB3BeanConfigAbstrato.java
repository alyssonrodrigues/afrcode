package afrcode.fwarquitetura.is.util.ejb;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.ejb.access.SimpleRemoteStatelessSessionProxyFactoryBean;

import afrcode.fwarquitetura.is.spring.config.util.ProfilesIS;

public abstract class EJB3BeanConfigAbstrato<TIPO_INTERFACE_SOFTWARE> 
    implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;

    protected TIPO_INTERFACE_SOFTWARE criarProxyEJB3(String jndiName) 
    		throws NamingException {
        SimpleRemoteStatelessSessionProxyFactoryBean proxyEJB3Factory =
                new SimpleRemoteStatelessSessionProxyFactoryBean();
        proxyEJB3Factory.setBusinessInterface(getClasseInterfaceSoftware());
        proxyEJB3Factory.setJndiName(jndiName);
        proxyEJB3Factory.setLookupHomeOnStartup(false);

        configurarParaTUSeNecessario(proxyEJB3Factory);

        proxyEJB3Factory.afterPropertiesSet();
        
        TIPO_INTERFACE_SOFTWARE umProxyEJB3 = 
        		(TIPO_INTERFACE_SOFTWARE) proxyEJB3Factory.getObject();
        
        return umProxyEJB3;
    }

	private void configurarParaTUSeNecessario(
			SimpleRemoteStatelessSessionProxyFactoryBean proxyEJB3Factory) {
		if (isProfileTUAtivo()) {
		    proxyEJB3Factory.setResourceRef(true);
		    String jndiName = proxyEJB3Factory.getJndiName();
		    int i = jndiName.lastIndexOf("/") + 1;
			jndiName = jndiName.substring(i) + "Remote";
		    proxyEJB3Factory.setJndiName(jndiName);
            // APENAS PARA TESTES: � necess�rio informar o context factory JNDI 
		    // para o container EJB embedded openEJB. Para uso real em 
		    // aplica��es n�o � necess�rio informar o context factory JNDI 
		    // pois o mesmo ser� feito atrav�s do JBoss AS.
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, 
            		"org.apache.openejb.client.LocalInitialContextFactory");
            proxyEJB3Factory.setJndiEnvironment(props);
		}
	}

	private boolean isProfileTUAtivo() {
		String[] activeProfiles = applicationContext.getEnvironment().
				getActiveProfiles();
		boolean profileTUAtivo = Arrays.asList(activeProfiles).contains(
				ProfilesIS.PROFILE_TESTES);
		return profileTUAtivo;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
    /**
     * M�todo de obten��o do tipo associado via generic parameterers.
     */
    private Class<TIPO_INTERFACE_SOFTWARE> getClasseInterfaceSoftware() {
        Class<?> clazz = this.getClass();
        Type superClazz = clazz.getGenericSuperclass();
        // Em geral subclasses cont�m apenas um supertipo gen�rico, 
        // por�m mais de um supertipo gen�rico pode surgir na presen�a de
        // aspectos.
        while (!ParameterizedType.class.isAssignableFrom(superClazz.getClass())) {
            clazz = clazz.getSuperclass();
            superClazz = clazz.getGenericSuperclass();
        }
        ParameterizedType tipoParametrizado = (ParameterizedType) superClazz;
        Type[] params = tipoParametrizado.getActualTypeArguments();
        Class<TIPO_INTERFACE_SOFTWARE> classeInterfaceSoftware = 
        		(Class<TIPO_INTERFACE_SOFTWARE>) params[0];
        return classeInterfaceSoftware;
    }
	
}
