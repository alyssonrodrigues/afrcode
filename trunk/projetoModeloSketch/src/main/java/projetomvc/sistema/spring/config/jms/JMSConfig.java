package projetomvc.sistema.spring.config.jms;

import static projetomvc.sistema.spring.util.Profiles.PROFILE_DESENV;
import static projetomvc.sistema.spring.util.Profiles.PROFILE_PROD;

import javax.jms.Queue;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import projetomvc.sistema.util.jms.JmsExceptionListener;
import projetomvc.sistema.util.jms.QueueListener;

@Configuration
@Profile({PROFILE_DESENV, PROFILE_PROD})
public class JMSConfig {
	@Autowired
	private ActiveMQConnectionFactory amqConnectionFactory;
	
	@Autowired
	private JmsExceptionListener jmsExceptionListener;
	
	@Autowired
	private Queue queueId;
	
	@Autowired
	private QueueListener queueListener;
	
	/**
	 * CachingConnectionFactory definition, sessionCacheSize property is
	 * the number of sessions to cache.
	 * @return
	 */
	@Bean
	public CachingConnectionFactory cachedConnectionFactory() {
		CachingConnectionFactory cachedConnectionFactory =
			new CachingConnectionFactory(amqConnectionFactory);
		cachedConnectionFactory.setSessionCacheSize(100);
		cachedConnectionFactory.setExceptionListener(jmsExceptionListener);
		return cachedConnectionFactory;
	}
	
	/**
	 * JmsTemplate PRODUCER Definition.
	 * @return
	 */
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate(cachedConnectionFactory());
		jmsTemplate.setDefaultDestination(queueId);
		return jmsTemplate;
	}
	
	/**
	 * JmsTransactionManager Spring provided.
	 * @return
	 */
	@Bean
	public JmsTransactionManager jmsTransactionManager() {
		JmsTransactionManager jmsTransactionManager =
			new JmsTransactionManager();
		jmsTransactionManager.setConnectionFactory(cachedConnectionFactory());
		return jmsTransactionManager;
	}
	
	/**
	 * CONSUMER listener container definition using the jms namespace, concurrency is
	 * the max number of concurrent listeners that can be started.
	 * @return
	 */
	@Bean
	public DefaultMessageListenerContainer messageListenerContainer() {
		DefaultMessageListenerContainer messageListenerContainer =
			new DefaultMessageListenerContainer();
		messageListenerContainer.setConnectionFactory(cachedConnectionFactory());
		messageListenerContainer.setTransactionManager(jmsTransactionManager());
		messageListenerContainer.setDestination(queueId);
		MessageListenerAdapter messageListenerAdapter =
			new MessageListenerAdapter(queueListener);
		messageListenerContainer.setMessageListener(messageListenerAdapter);
		return messageListenerContainer;
	}

}
