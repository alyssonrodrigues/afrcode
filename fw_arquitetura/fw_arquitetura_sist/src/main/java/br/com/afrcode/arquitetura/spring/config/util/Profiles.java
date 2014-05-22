package br.com.afrcode.arquitetura.spring.config.util;

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
}
