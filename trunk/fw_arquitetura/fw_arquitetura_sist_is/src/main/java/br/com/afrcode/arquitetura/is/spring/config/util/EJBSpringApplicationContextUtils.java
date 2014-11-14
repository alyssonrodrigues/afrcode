package br.com.afrcode.arquitetura.is.spring.config.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;

import br.com.afrcode.arquitetura.util.contexto.ApplicationContextUtils;

/**
 * Classe utilitária para obteção do BeanFactory Spring através do qual e
 * possível acessar o contexto Spring para obtenção de beans e resources. Em
 * geral esta classe será usada onde o contexto Spring não é injetável
 * declarativamente, exemplo: InterceptorS EJB.
 * 
 * 
 */
public class EJBSpringApplicationContextUtils {

	public static BeanFactory getBeanFactory() {
		return getApplicationContext();
	}

	public static ApplicationContext getApplicationContext() {
		return ApplicationContextUtils.getApplicationContext();
	}

	public static <T> T getBean(Class<T> tipoBean) {
		return getApplicationContext().getBean(tipoBean);
	}

}
