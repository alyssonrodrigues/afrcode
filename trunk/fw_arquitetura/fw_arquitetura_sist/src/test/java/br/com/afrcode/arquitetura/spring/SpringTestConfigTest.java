package br.com.afrcode.arquitetura.spring;

import javax.ejb.EJB;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.junit.Assert;
import org.junit.Test;

import br.com.afrcode.arquitetura.is.modelo.ejb.helloworld.service.IServicoHelloWorld;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;

/**
 * Teste unitário de startup do contexto Spring específico para o ambiente de
 * desenvolvimento.
 * 
 * Objetivo: validar o startup do contexto Spring através de DI para o
 * EntityManagerFactory definido em EntityManagerFactoryTUConfig.
 * 
 * Observações: faz uso de SGBD em memória, o HSQLDB.
 * 
 * 
 */
public class SpringTestConfigTest extends AbstractCasoTesteEmMemoria {
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@EJB
	private IServicoHelloWorld servicoHelloWorld;

	@Test
	public void testarSpringTestContext() {
		Assert.assertNotNull(
				"Um EntityManagerFactory deveria ter sido obtido com sucesso!",
				entityManagerFactory);
		Assert.assertNotNull(
				"Um IServicoHelloWorld deveria ter sido obtido com sucesso!",
				servicoHelloWorld);
		Assert.assertEquals("Mensagem de boas vindas diferente do esperado!",
				"Hello Alysson", servicoHelloWorld.sayHello("Alysson"));
	}

}
