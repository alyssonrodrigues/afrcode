package br.com.afrcode.arquitetura.is.spring.config.jms;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import br.com.afrcode.arquitetura.is.spring.config.util.ProfilesIS;
import br.com.afrcode.arquitetura.is.util.jms.JmsExceptionListener;

/**
 * Configurações para uso de JMS através do ActiveMQ.
 * 
 * Definições: pool de conexões com cache, jms template para envio/recebimento,
 * suporte a transações em jms, ouvinte de mensagens jms.
 * 
 * 
 */
@Configuration
@Profile({ ProfilesIS.PROFILE_APLICACAO, ProfilesIS.PROFILE_APLICACAO_BATCH,
		ProfilesIS.PROFILE_TU })
@ImportResource({ "classpath:spring-jms-broker-beans.xml" })
public class JMSBrokerConfig {
	@Autowired
	private ActiveMQConnectionFactory amqConnectionFactory;

	@Autowired
	private JmsExceptionListener jmsExceptionListener;

	/**
	 * CachingConnectionFactory definition, sessionCacheSize property is the
	 * number of sessions to cache.
	 * 
	 * @return
	 */
	@Bean
	public CachingConnectionFactory cachingConnectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(
				amqConnectionFactory);
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
		return new JmsTemplate(cachingConnectionFactory());
	}

}
