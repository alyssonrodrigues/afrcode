package br.com.afrcode.arquitetura.spring.config.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.env.Environment;

/**
 * Definições de "ambientes" para uso de Spring profiles.
 * 
 * Cada profile determina um conjunto de beans que serão geridos pelo Spring.
 * 
 * Use @Profile em classes @Configuration para informar uma lista de profiles
 * nos quais os beans declarados serão geridos pelo Spring.
 * 
 * 
 */
public class Profiles {
	// Profiles gerais de aplicação e testes unitários.
	public static final String PROFILE_TU = "tu";
	public static final String PROFILE_APLICACAO = "aplicacao";
	public static final String PROFILE_APLICACAO_BATCH = "batch";

	private static List<String> getActiveProfiles(Environment environment) {
		List<String> activeProfiles = Arrays.asList(environment
				.getActiveProfiles());
		return activeProfiles;
	}

	public static boolean isProfileAplicacaoAtivo(Environment environment) {
		List<String> activeProfiles = getActiveProfiles(environment);
		return activeProfiles.contains(PROFILE_APLICACAO)
				|| activeProfiles.contains(PROFILE_APLICACAO_BATCH);
	}

	public static boolean isProfileTUAtivo(Environment environment) {
		List<String> activeProfiles = getActiveProfiles(environment);
		return activeProfiles.contains(PROFILE_TU);
	}

}
