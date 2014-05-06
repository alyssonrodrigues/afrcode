package br.com.afrcode.arquitetura.is.modelo.ejb.helloworld.service;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.afrcode.arquitetura.is.util.ejb.AbstractServicoEJB;

@Stateless
@Remote(IServicoHelloWorld.class)
public class ServicoHelloWorldBean extends AbstractServicoEJB implements
		IServicoHelloWorld {

	@Override
	@RolesAllowed("FUNCAO_QUALQUER_A_SER_IGNORADA_EM_TU")
	public String sayHello(String name) {
		return "Hello " + name;
	}

}
