package afrcode.fwarquitetura.is.spring.config;

import static afrcode.fwarquitetura.is.spring.config.util.ProfilesIS.PROFILE_APLICACAO;
import static afrcode.fwarquitetura.is.spring.config.util.ProfilesIS.PROFILE_TESTES;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import org.apache.commons.lang.time.StopWatch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

/**
 * Classe central de configura��o do contexto Spring para aplica��o.
 * 
 * @author alyssonfr
 * 
 */
@Configuration
// Classes com estereotipos @Component, @Repository, @Controller,
// @Service ou @Configuration serao automaticamente declaradas como beans pelo Spring.
@ComponentScan(basePackages = {"afrcode"})
// Configuracoes para uso do AspectJ support do Spring com CGLIB.
// proxyTargetClass = true => Devemos usar CGLIB e n�o javaassist para que o autowiring por tipo do Spring continue funcional.
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Profile({PROFILE_APLICACAO, PROFILE_TESTES})
public class SpringISConfig {

    /**
     * Stowath para medi��es em tarefas para avalia��o de desempenho.
     * 
     * As demarca��es de in�cio e fim de cada tarefa ficam a carga de cada cliente de medi��o.
     * 
     * @return
     */
    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public StopWatch stopWatch() {
        StopWatch stopWatch = new StopWatch();
        return stopWatch;
    }

}