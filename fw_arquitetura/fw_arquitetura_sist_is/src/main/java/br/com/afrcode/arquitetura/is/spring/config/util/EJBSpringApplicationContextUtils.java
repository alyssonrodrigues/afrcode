package br.com.afrcode.arquitetura.is.spring.config.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;

import br.com.afrcode.arquitetura.util.contexto.ApplicationContextUtils;

/**
 * Classe utilitaria para obtecao do BeanFactory Spring atraves do qual e possivel acessar o contexto Spring
 * para obtencao de beans e resources. Em geral esta classe sera usada onde o contexto Spring nao e injetavel declarativamente,
 * exemplo: InterceptorS EJB.
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

