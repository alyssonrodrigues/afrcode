package br.com.afrcode.arquitetura.is.spring.config.ejb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Subclasse de AnnotationConfigApplicationContext com classificador de contexto
 * para EJBs.
 * 
 * 
 */
public class SpringEjbAnnotationConfigApplicationContext extends
		AnnotationConfigApplicationContext {
	public static final String CTX_SUFFIX = "_ejbSpringApplicationContext";

	public SpringEjbAnnotationConfigApplicationContext(String... basePackages) {
		super();
		setId(getId().concat(CTX_SUFFIX));
		scan(basePackages);
		refresh();
	}

}
