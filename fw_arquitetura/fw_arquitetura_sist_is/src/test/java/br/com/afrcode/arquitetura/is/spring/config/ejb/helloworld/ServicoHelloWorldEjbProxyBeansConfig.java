package br.com.afrcode.arquitetura.is.spring.config.ejb.helloworld;

import javax.naming.NamingException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import br.com.afrcode.arquitetura.is.modelo.ejb.helloworld.service.IServicoHelloWorld;
import br.com.afrcode.arquitetura.is.spring.config.ejb.AbstractEjbProxyBeansConfig;
import br.com.afrcode.arquitetura.is.spring.config.util.ProfilesIS;

/**
 * Configurações, por EJB, necessárias para DI de EJBs em beans Spring.
 * 
 * Integração Spring => EJB. (Beans Spring vêem EJBs)
 * 
 * 
 */
@Configuration
@Profile(ProfilesIS.PROFILE_TU)
public class ServicoHelloWorldEjbProxyBeansConfig extends AbstractEjbProxyBeansConfig<IServicoHelloWorld> {

    @Bean
    @Lazy
    public IServicoHelloWorld servicoHelloWorldBeanRemote() throws NamingException {
        return criarEjb3RemoteStatelessSessionProxy("ServicoHelloWorldBeanRemote");
    }

}
