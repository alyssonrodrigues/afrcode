package afrcode.fwarquitetura.is.test.spring.config.ejb;

import static afrcode.fwarquitetura.is.spring.config.util.ProfilesIS.PROFILE_TESTES;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.ejb.access.SimpleRemoteStatelessSessionProxyFactoryBean;

import afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.service.IServicoUmObjetoEmMemoriaBean;
import afrcode.fwarquitetura.is.util.ejb.EJB3BeanConfigAbstrato;

/**
 * Configurações, por EJB, necessárias para DI de EJBs em beans Spring.
 * 
 * Integração Spring => EJB. (Beans Spring vêem EJBs)
 * 
 * @author alyssonfr
 * 
 */
@Configuration
@Profile(PROFILE_TESTES)
public class UmObjetoEmMemoriaBeanEJB3BeanConfig 
    extends EJB3BeanConfigAbstrato<IServicoUmObjetoEmMemoriaBean> {

    @Bean
    @Lazy
    public IServicoUmObjetoEmMemoriaBean umObjetoEmMemoriaBeanRemote() throws NamingException {
    	IServicoUmObjetoEmMemoriaBean umObjetoEmMemoriaBeanRemote = 
    			criarProxyEJB3("java:global/fw_arquitetura_is/UmObjetoEmMemoriaBean");
        return umObjetoEmMemoriaBeanRemote;
    }

}
