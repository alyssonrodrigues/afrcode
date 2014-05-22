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
@Profile({ ProfilesIS.PROFILE_APLICACAO, ProfilesIS.PROFILE_APLICACAO_BATCH })
public class JMSBeansConfig {
	@Autowired
	private QueueListener queueListener;

	@Autowired
	private CachingConnectionFactory cachingConnectionFactory;

	/**
	 * JmsTransactionManager Spring provided.
	 * 
	 * TODO: Rever diante do uso de JTA!!!
	 * 
	 * This local strategy is an alternative to executing JMS operations within
	 * JTA transactions. Its advantage is that it is able to work in any
	 * environment, for example a standalone application or a test suite, with
	 * any message broker as target. However, this strategy is not able to
	 * provide XA transactions, for example in order to share transactions
	 * between messaging and database access. A full JTA/XA setup is required
	 * for XA transactions, typically using Spring's
	 * org.springframework.transaction.jta.JtaTransactionManager as strategy.
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

}
