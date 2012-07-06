package afrcode.fwarquitetura.test.spring.config.tx;

import static afrcode.fwarquitetura.spring.config.util.Profiles.PROFILE_TESTES;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Configurações pra uso de JTA via Hibernate EntityManager.
 * 
 * @author alyssonfr
 * 
 */
@Configuration
// Configuracoes para uso de transacoes declarativas (@Transaction)
// e nao declarativas.
@EnableTransactionManagement
@Profile({PROFILE_TESTES})
public class TransactionManagerTUConfig {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    /**
     * Bean para uso de transações programaticamente.
     * Ver {@link TransactionTemplate}.
     * 
     * @return
     */
    @Bean
    public TransactionTemplate transactionTemplate() {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager());
        return transactionTemplate;
    }

}
