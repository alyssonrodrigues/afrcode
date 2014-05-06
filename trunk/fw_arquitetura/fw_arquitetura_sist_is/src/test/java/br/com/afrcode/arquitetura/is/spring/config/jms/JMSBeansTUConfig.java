package br.com.afrcode.arquitetura.is.spring.config.jms;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import br.com.afrcode.arquitetura.is.spring.config.util.ProfilesIS;
import br.com.afrcode.arquitetura.is.util.jms.QueueListener;

@Configuration
@Profile(ProfilesIS.PROFILE_TU)
public class JMSBeansTUConfig {
	@Autowired
	private QueueListener queueListener;

	@Autowired
	private CachingConnectionFactory cachingConnectionFactory;

	/**
	 * Spring JmsTransactionManager para testes (non-XA).
	 * 
	 * @return
	 */
	public JmsTransactionManager jmsTransactionManager() {
		JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
		jmsTransactionManager.setConnectionFactory(cachingConnectionFactory);
		return jmsTransactionManager;
	}

	/**
	 * Fila queue.test.
	 * 
	 * @return
	 */
	@Bean
	public Queue queueTest() {
		return new ActiveMQQueue("queue.test");
	}

	/**
	 * Consumidor para fila queue.test.
	 * 
	 * @return
	 */
	@Bean
	public DefaultMessageListenerContainer msgListenerQueueTest() {
		DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
		messageListenerContainer.setConnectionFactory(cachingConnectionFactory);
		messageListenerContainer.setTransactionManager(jmsTransactionManager());
		messageListenerContainer.setDestination(queueTest());
		MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(
				queueListener);
		messageListenerContainer.setMessageListener(messageListenerAdapter);
		return messageListenerContainer;
	}

	/**
	 * Fila queue.dummy.
	 * 
	 * @return
	 */
	@Bean
	public Queue queueDummy() {
		Queue queueTest = new ActiveMQQueue("queue.dummy");
		return queueTest;
	}

}
