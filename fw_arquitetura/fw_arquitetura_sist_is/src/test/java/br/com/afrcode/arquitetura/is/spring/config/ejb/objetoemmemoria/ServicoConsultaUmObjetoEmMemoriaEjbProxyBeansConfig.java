package br.com.afrcode.arquitetura.is.spring.config.ejb.objetoemmemoria;

import javax.naming.NamingException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import br.com.afrcode.arquitetura.is.modelo.ejb.objetoemmemoria.service.IServicoConsultaUmObjetoEmMemoria;
import br.com.afrcode.arquitetura.is.spring.config.ejb.AbstractEjbProxyBeansConfig;
import br.com.afrcode.arquitetura.is.spring.config.util.ProfilesIS;

/**
 * Configura��es, por EJB, necess�rias para DI de EJBs em beans Spring.
 * 
 * Integra��o Spring => EJB. (Beans Spring v�em EJBs)
 * 
 * 
 */
@Configuration
@Profile(ProfilesIS.PROFILE_TU)
public class ServicoConsultaUmObjetoEmMemoriaEjbProxyBeansConfig extends
        AbstractEjbProxyBeansConfig<IServicoConsultaUmObjetoEmMemoria> {

    @Bean
    @Lazy
    public IServicoConsultaUmObjetoEmMemoria servicoConsultaUmObjetoEmMemoriaBeanRemote() throws NamingException {
        return criarEjb3RemoteStatelessSessionProxy("ServicoConsultaUmObjetoEmMemoriaBeanRemote");
    }

}
