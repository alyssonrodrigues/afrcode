package br.com.afrcode.arquitetura.util.contexto;

import java.util.Arrays;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Component;

import br.com.afrcode.arquitetura.is.spring.config.util.ProfilesIS;

/**
 * Classe utilitaria para uso do ApplicationContext Spring em classes onde nao e
 * possivel fazer injecao declarativa (via anotacao Autowired).
 * 
 * Cenario de uso:
 * Classes nao geridas pelo Spring, instanciadas pelo programador ou por outro framework qualquer,
 * que necessitem usar beans geridos pelo Spring.
 * 
 * 
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {
    private static final Logger LOG = Logger.getLogger(ApplicationContextUtils.class);

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> tipoBean) {
        return getApplicationContext().getBean(tipoBean);
    }

    public static boolean isEjbSpringApplicationContext(ApplicationContext appCtx) {
        return false;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        log();
    }

    private void log() {
        String nomeAplicacao = getNomeAplicacao();
        LOG.info("Spring ApplicationContext iniciado hashcode[" + applicationContext.hashCode() + "] para " + nomeAplicacao +
                "...");
    }

    public static String getNomeAplicacao() {
        String nomeAplicacao = applicationContext.getId();
        if (!Arrays.asList(applicationContext.getEnvironment().getActiveProfiles()).contains(ProfilesIS.PROFILE_TU)) {
            JndiTemplate jndiTemplate = new JndiTemplate();
            try {
                nomeAplicacao = jndiTemplate.lookup("java:app/AppName").toString();
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
        }
        return nomeAplicacao;
    }

}
