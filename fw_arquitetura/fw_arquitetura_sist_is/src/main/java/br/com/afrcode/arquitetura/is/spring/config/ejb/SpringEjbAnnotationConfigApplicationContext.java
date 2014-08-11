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
    private static final Logger LOG = Logger.getLogger(SpringEjbAnnotationConfigApplicationContext.class);

    public SpringEjbAnnotationConfigApplicationContext(String... basePackages) {
        super();
        ApplicationContext appCtx = ApplicationContextUtils.getApplicationContext();
        if (appCtx == null) {
            appCtx = new AnnotationConfigApplicationContext(basePackages);
            LOG.warn("ATENÇÃO: Um ApplicationContext Spring será iniciado especificamente para EJBs da aplicação [" +
                    ApplicationContextUtils.getNomeAplicacao() + "] " +
                    "Não foi possível recuperar um ApplicationContext Spring já iniciado para compartilhar com EJBs!");
        }
        setParent(appCtx);
        refresh();
    }

}
