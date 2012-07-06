package afrcode.fwarquitetura.is.test.spring.config.ejb;

import static afrcode.fwarquitetura.is.spring.config.util.ProfilesIS.PROFILE_TESTES;

import java.util.Properties;

import javax.naming.Context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.ejb.access.SimpleRemoteStatelessSessionProxyFactoryBean;

import afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.service.ISEjb3UmObjetoEmMemoria;

/**
 * Configura��es, por EJB, necess�rias para DI de EJBs em beans Spring.
 * 
 * Integra��o Spring => EJB. (Beans Spring v�em EJBs)
 * 
 * @author alyssonfr
 * 
 */
@Configuration
@Profile(PROFILE_TESTES)
public class ISEJB3BeansConfig {

    @Bean
    @Lazy
    public SimpleRemoteStatelessSessionProxyFactoryBean umObjetoEmMemoriaBeanRemote() {
        SimpleRemoteStatelessSessionProxyFactoryBean umObjetoEmMemoriaBeanRemote =
                new SimpleRemoteStatelessSessionProxyFactoryBean();
        umObjetoEmMemoriaBeanRemote.setJndiName("UmObjetoEmMemoriaBeanRemote");
        umObjetoEmMemoriaBeanRemote.setResourceRef(true);
        umObjetoEmMemoriaBeanRemote.setBusinessInterface(ISEjb3UmObjetoEmMemoria.class);

        // APENAS PARA TESTES: � necess�rio informar o context factory JNDI para o container EJB embedded openEJB.
        // Para uso real em aplica��es n�o � necess�rio informar o context factory JNDI pois o mesmo ser� feito atrav�s do JBoss
        // AS.
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
        umObjetoEmMemoriaBeanRemote.setJndiEnvironment(props);

        return umObjetoEmMemoriaBeanRemote;
    }

}
