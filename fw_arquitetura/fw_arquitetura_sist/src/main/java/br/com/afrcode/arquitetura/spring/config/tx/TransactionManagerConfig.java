package br.com.afrcode.arquitetura.spring.config.tx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Configurações pra uso de JTA via Hibernate EntityManager.
 * 
 * 
 */
@Configuration
// Configuracoes para uso de transacoes declarativas (@Transaction)
// e nao declarativas.
@EnableTransactionManagement
@Profile(Profiles.PROFILE_APLICACAO)
public class TransactionManagerConfig {

    @Bean
    public JtaTransactionManager transactionManager() {
        return new JtaTransactionManager();
    }

    /**
     * Bean para uso de transações programaticamente. Ver TransactionTemplate.
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
