package br.com.afrcode.arquitetura.is.util.ejb;

import javax.interceptor.Interceptors;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

/**
 * Classe base para defini��o de EJBs.
 * 
 */
@Interceptors({ SpringBeanAutowiringInterceptor.class,
		ExcecoesInterceptor.class })
public abstract class AbstractServicoEJB {

}
