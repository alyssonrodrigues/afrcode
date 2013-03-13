package afrcode.fwarquitetura.is.test.spring.config.rmi.client;

import static afrcode.fwarquitetura.is.spring.config.util.ProfilesIS.PROFILE_TESTES;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import afrcode.fwarquitetura.is.test.modelo.rmi.objetoemmemoria.service.IServicoUmObjetoEmMemoriaRMI;
import afrcode.fwarquitetura.is.util.rmi.RMIClientBeanConfigAbstrato;

/**
 * Configura��es para acesso a servi�os remotos expostos via RMI.
 * 
 * @author alyssonfr
 * 
 */
@Configuration
@Profile({PROFILE_TESTES})
public class UmObjetoEmMemoriaRmiClientBeanConfig
    extends RMIClientBeanConfigAbstrato<IServicoUmObjetoEmMemoriaRMI> {

    /**
     * Configura��es para acesso ao servi�o remoto exposto via RMI.
     * 
     * O Spring atrav�s de {@link RmiProxyFactoryBean} beans exp�e servi�os (POJOs) encapsulando todo o acesso ao RMI registry.
     * � necess�rio:
     * 1) informar a url de acesso ao servi�o;
     * 2) informar e possuir em classpath a interface referente ao servi�o.
     * 
     * @return
     */
    @Bean
    @Lazy
    public IServicoUmObjetoEmMemoriaRMI umObjetoEmMemoriaRmiClient() {
    	IServicoUmObjetoEmMemoriaRMI umObjetoEmMemoriaRmiClient =
    			criarProxyRMIClient("rmi://localhost/umObjetoEmMemoriaRmiService");
    	return umObjetoEmMemoriaRmiClient;
    }

}
