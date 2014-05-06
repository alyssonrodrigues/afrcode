package br.com.afrcode.arquitetura.is.util.jms;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Tratador central de exceções ocorridas durante o uso de JMS.
 * 
 * TODO: Implementar tratamento adequado.
 * 
 * 
 */
@Component
public class JmsExceptionListener implements ExceptionListener {

	private static final Logger LOG = Logger
			.getLogger(JmsExceptionListener.class);

	@Override
	public void onException(JMSException exception) {
		LOG.error("Erro: ", exception);
	}

}
