package afrcode.fwarquitetura.is.test.spring.config.rmi.client;

import static afrcode.fwarquitetura.is.spring.config.util.ProfilesIS.PROFILE_TESTES;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.security.remoting.rmi.ContextPropagatingRemoteInvocationFactory;

import afrcode.fwarquitetura.is.test.modelo.rmi.objetoemmemoria.service.ISRmiUmObjetoEmMemoria;

/**
 * Configurações para acesso a serviços remotos expostos via RMI.
 * 
 * @author alyssonfr
 * 
 */
@Configuration
@Profile({PROFILE_TESTES})
public class RMIClientProxyBeansTUConfig {

    /**
     * Configurações para acesso ao serviço remoto exposto via RMI.
     * 
     * O Spring através de {@link RmiProxyFactoryBean} beans expõe serviços (POJOs) encapsulando todo o acesso ao RMI registry.
     * É necessário:
     * 1) informar a url de acesso ao serviço;
     * 2) informar e possuir em classpath a interface referente ao serviço.
     * 
     * @return
     */
    @Bean
    @DependsOn({"umObjetoEmMemoriaRmiServiceExporter"})
    public RmiProxyFactoryBean umObjetoEmMemoriaRmiClient() {
        RmiProxyFactoryBean umObjetoEmMemoriaRmiClient = new RmiProxyFactoryBean();
        umObjetoEmMemoriaRmiClient.setServiceUrl("rmi://localhost/umObjetoEmMemoriaRmiService");
        umObjetoEmMemoriaRmiClient.setServiceInterface(ISRmiUmObjetoEmMemoria.class);
        /**
         * Integração com o Spring Security para propagação do SecurityContext.
         * Ver {@code ContextPropagatingRemoteInvocation}.
         */
        umObjetoEmMemoriaRmiClient.setRemoteInvocationFactory(new ContextPropagatingRemoteInvocationFactory());
        return umObjetoEmMemoriaRmiClient;
    }

}
