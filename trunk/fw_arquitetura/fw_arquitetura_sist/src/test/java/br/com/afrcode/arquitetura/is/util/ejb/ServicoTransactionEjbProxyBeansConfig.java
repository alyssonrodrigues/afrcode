package br.com.afrcode.arquitetura.is.util.ejb;

import javax.naming.NamingException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import br.com.afrcode.arquitetura.is.spring.config.ejb.AbstractEjbProxyBeansConfig;
import br.com.afrcode.arquitetura.spring.config.util.Profiles;

@Configuration
@Profile(Profiles.PROFILE_TU)
public class ServicoTransactionEjbProxyBeansConfig extends AbstractEjbProxyBeansConfig<Caller> {

    @Bean
    @Lazy
    public Caller servicoTransactionBeanRemote() throws NamingException {
        return criarEjb3RemoteStatelessSessionProxy("ServicoTransactionBeanRemote");
    }

}
