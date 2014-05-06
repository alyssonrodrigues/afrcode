package br.com.afrcode.arquitetura.spring.config.util;

/**
 * Defini��es de "ambientes" para uso de Spring profiles.
 * 
 * Cada profile determina um conjunto de beans que ser�o geridos pelo Spring.
 * 
 * Use @Profile em classes @Configuration para informar uma lista de profiles
 * nos quais os beans declarados ser�o geridos pelo Spring.
 * 
 * 
 */
public class Profiles {
	// Profiles gerais de aplica��o e testes unit�rios.
	public static final String PROFILE_TU = "tu";
	public static final String PROFILE_APLICACAO = "aplicacao";
}