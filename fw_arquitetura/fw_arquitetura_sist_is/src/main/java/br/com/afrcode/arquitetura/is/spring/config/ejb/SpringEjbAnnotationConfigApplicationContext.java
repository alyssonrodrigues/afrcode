package br.com.afrcode.arquitetura.is.spring.config.ejb;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import br.com.afrcode.arquitetura.util.contexto.ApplicationContextUtils;

/**
 * Subclasse de GenericApplicationContext com classificador de contexto para EJBs.
 */
public class SpringEjbAnnotationConfigApplicationContext extends GenericApplicationContext {
    private static final Logger LOG = Logger.getLogger(SpringEjbAnnotationConfigApplicationContext.class);

    public SpringEjbAnnotationConfigApplicationContext(String... basePackages) {
        super();
        ApplicationContext appCtx = ApplicationContextUtils.getApplicationContext();
        if (appCtx == null) {
            appCtx = new AnnotationConfigApplicationContext(basePackages);
            LOG.warn("ATENCAO: Um ApplicationContext Spring sera iniciado especificamente para EJBs da aplicacao [" +
                    ApplicationContextUtils.getNomeAplicacao() + "] " +
                    "Nao foi possivel recuperar um ApplicationContext Spring ja iniciado para compartilhar com EJBs!");
        }
        setParent(appCtx);
        refresh();
    }

}
