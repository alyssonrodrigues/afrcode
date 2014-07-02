package br.com.afrcode.arquitetura.util.contexto;

import org.junit.Assert;
import org.junit.Test;

import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;

public class ApplicationContextUtilsTest extends AbstractCasoTesteEmMemoria {

	@Test
	public void testarGetApplicationContext() {
		Assert.assertNotNull("ApplicationContext não deve ser nulo!",
				ApplicationContextUtils.getApplicationContext());
	}

}
