package br.com.afrcode.arquitetura.teste.unitario.spring.config;

import org.apache.commons.lang.time.StopWatch;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import br.com.afrcode.arquitetura.teste.unitario.spring.config.util.ProfilesTU;

/**
 * Classe central de configuração do contexto Spring para testes.
 * 
 * 
 */
@Configuration
// Classes com estereotipos @Component, @Repository, @Controller
// ou @Service serao automaticamente declaradas como beans pelo Spring.
@ComponentScan(basePackages = { "br.com.afrcode" })
// Configuracoes para uso do AspectJ support do Spring com CGLIB.
// proxyTargetClass = true => Devemos usar CGLIB e não javaassist para que o
// autowiring por tipo do Spring continue funcional.
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Profile({ ProfilesTU.PROFILE_TU })
public class SpringTestConfig {

	// Configuracao p/ uso de ${...} obtidas via @PropertySource em anotacoes
	// @Value.
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/**
	 * Stowath para medições em tarefas para avaliação de desempenho.
	 * 
	 * As demarcações de início e fim de cada tarefa ficam a carga de cada
	 * cliente de medição.
	 * 
	 * @return
	 */
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public StopWatch stopWatch() {
		return new StopWatch();
	}

}
