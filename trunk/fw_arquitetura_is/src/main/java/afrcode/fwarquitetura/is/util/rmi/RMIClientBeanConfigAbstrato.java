package afrcode.fwarquitetura.is.util.rmi;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.security.remoting.rmi.ContextPropagatingRemoteInvocationFactory;

public class RMIClientBeanConfigAbstrato<TIPO_INTERFACE_SOFTWARE> {
	
    protected TIPO_INTERFACE_SOFTWARE criarProxyRMIClient(String rmiName) {
        RmiProxyFactoryBean proxyRMIClientFactory = new RmiProxyFactoryBean();
        proxyRMIClientFactory.setServiceUrl(rmiName);
        proxyRMIClientFactory.setServiceInterface(getClasseInterfaceSoftware());
        proxyRMIClientFactory.setLookupStubOnStartup(false);
        
        /**
         * Integra��o com o Spring Security para propaga��o do SecurityContext.
         * Ver {@code ContextPropagatingRemoteInvocation}.
         */
        proxyRMIClientFactory.setRemoteInvocationFactory(new ContextPropagatingRemoteInvocationFactory());
        
        proxyRMIClientFactory.afterPropertiesSet();
        
        TIPO_INTERFACE_SOFTWARE proxyRMIClient =
        		(TIPO_INTERFACE_SOFTWARE) proxyRMIClientFactory.getObject();
        
        return proxyRMIClient;
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
