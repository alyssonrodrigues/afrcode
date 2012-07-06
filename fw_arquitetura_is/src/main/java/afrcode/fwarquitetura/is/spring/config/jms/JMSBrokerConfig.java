package afrcode.fwarquitetura.is.spring.config.jms;

import static afrcode.fwarquitetura.is.spring.config.util.ProfilesIS.PROFILE_APLICACAO;
import static afrcode.fwarquitetura.is.spring.config.util.ProfilesIS.PROFILE_TESTES;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import afrcode.fwarquitetura.is.util.jms.JmsExceptionListener;

/**
 * Configurações para uso de JMS através do ActiveMQ.
 * 
 * Definições: pool de conexões com cache, jms template para envio/recebimento,
 * suporte a transações em jms, ouvinte de mensagens jms.
 * 
 * @author alyssonfr
 * 
 */
@Configuration
@Profile({PROFILE_APLICACAO, PROFILE_TESTES})
@ImportResource({"classpath:spring-jms-broker-beans.xml"})
public class JMSBrokerConfig {
    @Autowired
    private ActiveMQConnectionFactory amqConnectionFactory;

    @Autowired
    private JmsExceptionListener jmsExceptionListener;

    /**
     * CachingConnectionFactory definition, sessionCacheSize property is
     * the number of sessions to cache.
     * 
     * @return
     */
    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory =
                new CachingConnectionFactory(amqConnectionFactory);
        cachingConnectionFactory.setSessionCacheSize(100);
        cachingConnectionFactory.setExceptionListener(jmsExceptionListener);
        return cachingConnectionFactory;
    }

    /**
     * JmsTemplate PRODUCER padrão independente de fila.
     * 
     * @return
     */
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory());
        return jmsTemplate;
    }

}
