package br.com.afrcode.arquitetura.is.spring.config;

import org.apache.commons.lang.time.StopWatch;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import br.com.afrcode.arquitetura.is.spring.config.util.ProfilesIS;

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
@Profile({ ProfilesIS.PROFILE_APLICACAO, ProfilesIS.PROFILE_APLICACAO_BATCH,
		ProfilesIS.PROFILE_TU })
public class SpringISConfig {

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