package afrcode.fwarquitetura.spring.config.tx;

import static afrcode.fwarquitetura.spring.config.util.Profiles.PROFILE_APLICACAO;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
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
@Profile({PROFILE_APLICACAO})
public class TransactionManagerConfig {

    @Bean
    public JtaTransactionManager transactionManager() {
        JtaTransactionManager transactionManager = new JtaTransactionManager();
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
