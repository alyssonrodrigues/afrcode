package afrcode.fwarquitetura.spring.config;

import static afrcode.fwarquitetura.spring.config.util.Profiles.PROFILE_APLICACAO;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Classe central de configuração do contexto Spring para aplicação.
 * 
 * @author alyssonfr
 * 
 */
@Configuration
// Classes com estereotipos @Component, @Repository, @Controller,
// @Service ou @Configuration serao automaticamente declaradas como beans pelo Spring.
@ComponentScan(basePackages = {"afrcode"})
// Configuracoes para uso do AspectJ support do Spring com CGLIB.
// proxyTargetClass = true => Devemos usar CGLIB e não javaassist para que o autowiring por tipo do Spring continue funcional.
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Profile(PROFILE_APLICACAO)
public class SpringAppConfig {

    // Configuracao p/ uso de ${...} obtidas via @PropertySource em anotacoes @Value.
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }

}
