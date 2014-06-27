package br.com.afrcode.arquitetura.is.modelo.ejb.helloworld.service;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless
@Remote(IServicoHelloWorld.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class ServicoHelloWorldBean implements IServicoHelloWorld {

	@Override
	@RolesAllowed("FUNCAO_QUALQUER_A_SER_IGNORADA_EM_TU")
	public String sayHello(String name) {
		return "Hello " + name;
	}

}
