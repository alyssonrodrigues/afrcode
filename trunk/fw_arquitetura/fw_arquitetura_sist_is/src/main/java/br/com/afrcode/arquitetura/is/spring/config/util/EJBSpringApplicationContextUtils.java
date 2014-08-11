package br.com.afrcode.arquitetura.is.spring.config.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;

import br.com.afrcode.arquitetura.util.contexto.ApplicationContextUtils;

/**
 * Classe utilit�ria para obte��o do BeanFactory Spring atrav�s do qual e
 * poss�vel acessar o contexto Spring para obten��o de beans e resources. Em
 * geral esta classe ser� usada onde o contexto Spring n�o � injet�vel
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
