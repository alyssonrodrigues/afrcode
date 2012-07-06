package afrcode.fwarquitetura.spring.config.jpa;

import static afrcode.fwarquitetura.spring.config.util.Profiles.PROFILE_APLICACAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configurações para uso de JPA via Hibernate EntityManager gerido pelo container JBoss.
 * 
 * @author alyssonfr
 * 
 */
@Configuration
@Profile(PROFILE_APLICACAO)
public class EntityManagerFactoryConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() throws NamingException {
        Context context = new InitialContext();
        EntityManagerFactory entityManagerFactory =
                (EntityManagerFactory) context.lookup("java:comp/env/persistence/EntityManagerFactory");
        return entityManagerFactory;
    }

}
