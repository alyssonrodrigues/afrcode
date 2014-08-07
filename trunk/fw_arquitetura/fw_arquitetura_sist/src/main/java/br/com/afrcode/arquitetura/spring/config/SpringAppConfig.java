package br.com.afrcode.arquitetura.spring.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Classe central de configuração do contexto Spring para aplicação.
 * 
 * 
 */
@Configuration
// Classes com estereotipos @Component, @Repository, @Controller,
// @Service ou @Configuration serao automaticamente declaradas como beans pelo
// Spring.
@ComponentScan(basePackages = { "br.com.afrcode" })
// Configuracoes para uso do AspectJ support do Spring com CGLIB.
// proxyTargetClass = true => Devemos usar CGLIB e não javaassist para que o
// autowiring por tipo do Spring continue funcional.
@EnableAspectJAutoProxy(proxyTargetClass = true)
// Configuracoes para uso do Spring TaskScheduler para execução de rotinas
// em background.
@EnableScheduling
@Profile({ Profiles.PROFILE_APLICACAO, Profiles.PROFILE_APLICACAO_BATCH })
public class SpringAppConfig {

	// Configuracao p/ uso de ${...} obtidas via @PropertySource em anotacoes
	// @Value.
	@Bean
	public static PropertySourcesPlaceholderConfigurer
			propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/**
	 * Bean responsável por obter mensagens através de seus códigos via arquivos
	 * de messages resources (.properties).
	 * 
	 * TODO: rever se este bean não deveria estar presente no profile de TUs.
	 * 
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource =
				new ResourceBundleMessageSource();

		// Resource bundle de mensages do Spring Security, locale pt_BR.
		String springSecurityMessages =
				"org.springframework.security.messages_pt_BR";

		// TODO: adicionar demais messages resources sob demanda!
		String[] messagesResources = new String[] { springSecurityMessages };
		messageSource.setBasenames(messagesResources);
		return messageSource;
	}

	@Bean
	public TaskScheduler taskScheduler() {
		ScheduledExecutorService scheduledExecutorService =
				Executors.newSingleThreadScheduledExecutor();
		return new ConcurrentTaskScheduler(scheduledExecutorService);

	}

}
