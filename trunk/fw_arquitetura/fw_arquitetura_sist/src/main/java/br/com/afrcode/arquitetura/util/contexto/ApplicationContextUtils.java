package br.com.afrcode.arquitetura.util.contexto;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import br.com.afrcode.arquitetura.is.spring.config.ejb.SpringEjbAnnotationConfigApplicationContext;

/**
 * Classe utilitária para uso do ApplicationContext Spring em classes onde não é
 * possível fazer injeção declarativa (via anotação Autowired).
 * 
 * Cenário de uso: Classes não geridas pelo Spring, instanciadas pelo
 * programador ou por outro framework qualquer, que necessitem usar beans
 * geridos pelo Spring.
 * 
 * 
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static <T> T getBean(Class<T> tipoBean) {
		return getApplicationContext().getBean(tipoBean);
	}

	public static boolean isEjbSpringApplicationContext(
			ApplicationContext appCtx) {
		return appCtx.getId().endsWith(
				SpringEjbAnnotationConfigApplicationContext.CTX_SUFFIX);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
