package afrcode.fwarquitetura.tu.spring.config;

import static afrcode.fwarquitetura.tu.spring.config.util.ProfilesTU.PROFILE_TESTES;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import org.apache.commons.lang.time.StopWatch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Classe central de configuração do contexto Spring para testes.
 * 
 * @author alyssonfr
 * 
 */
// Classes com estereotipos @Component, @Repository, @Controller
// ou @Service serao automaticamente declaradas como beans pelo Spring.
@ComponentScan(basePackages = {"afrcode"})
// Configuracoes para uso do AspectJ support do Spring com CGLIB.
// proxyTargetClass = true => Devemos usar CGLIB e não javaassist para que o autowiring por tipo do Spring continue funcional.
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Profile({PROFILE_TESTES})
public class SpringTestConfig {

    // Configuracao p/ uso de ${...} obtidas via @PropertySource em anotacoes @Value.
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }

    /**
     * Stowath para medições em tarefas para avaliação de desempenho.
     * 
     * As demarcações de início e fim de cada tarefa ficam a carga de cada cliente de medição.
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
