package br.com.afrcode.arquitetura.is.spring.config.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.ApplicationContext;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

/**
 * Classe utilitária para obteção do BeanFactory Spring através do qual é
 * possível acessar o contexto Spring para obtenção de beans e resources. Em
 * geral esta classe será usada onde o contexto Spring não é injetável
 * declarativamente, exemplo: InterceptorS EJB.
 * 
 * 
 */
public class EJBSpringApplicationContextUtils {

	public static BeanFactory getBeanFactory() {
		BeanFactoryLocator beanFactoryLocator = ContextSingletonBeanFactoryLocator
				.getInstance();
		BeanFactoryReference beanFactoryReference = beanFactoryLocator
				.useBeanFactory(null);
		return beanFactoryReference.getFactory();
	}

	public static ApplicationContext getApplicationContext() {
		ApplicationContext applicationContext = null;
		BeanFactory beanFactory = getBeanFactory();
		if (beanFactory instanceof ApplicationContext) {
			applicationContext = (ApplicationContext) beanFactory;
		} else {
			throw new RuntimeException(
					"O contexto Spring não foi iniciado adequadamente. "
							+ "Verique o tipo deste contexto em: "
							+ "fw_arquitetura_sist_is/src/main/resources/beanRefContext.xml");
		}
		return applicationContext;
	}

}
