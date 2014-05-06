package br.com.afrcode.arquitetura.is.modelo.ejb.helloworld;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import br.com.afrcode.arquitetura.is.modelo.ejb.helloworld.service.IServicoHelloWorld;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteSemJpaEJta;

public class ServicoHelloWorldBeanTest extends AbstractCasoTesteSemJpaEJta {

	@EJB
	private IServicoHelloWorld servicoHelloWorld;

	@Test
	public void testarSrvHelloWorld() {
		Assert.assertNotNull(
				"Deveria ter sido obtido um proxy EJB3 via autowiring!",
				servicoHelloWorld);
		Assert.assertEquals("Mensagem de Hello World diferente do esperado!",
				"Hello Alysson", servicoHelloWorld.sayHello("Alysson"));
	}

}
