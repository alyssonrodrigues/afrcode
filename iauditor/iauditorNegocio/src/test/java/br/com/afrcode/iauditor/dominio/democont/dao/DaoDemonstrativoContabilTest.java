package br.com.afrcode.iauditor.dominio.democont.dao;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;

public class DaoDemonstrativoContabilTest extends AbstractCasoTesteEmMemoria {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Autowired
	private DaoDemonstrativoContabil daoDemonstrativoContabil;

	@Test
	public void getExtendedStats() {
		String r = daoDemonstrativoContabil.getExtendedStats("LUCRO LÍQUIDO");
		Assert.assertNotNull("r nulo!", r);
	}

}
