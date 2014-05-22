package br.com.afrcode.arquitetura;

import org.springframework.boot.SpringApplication;

import br.com.afrcode.arquitetura.spring.config.SpringAppConfig;

public class BatchRunner {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(
				SpringAppConfig.class);
		springApplication.setWebEnvironment(false);
		springApplication.run("--debug -Dspring.profiles.active=batch");
	}

}
