package br.com.afrcode.iauditor.dominio.democont.dao;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;
import br.com.afrcode.iauditor.dominio.elasticsearch.ExtendedStats;

public class DaoDemonstrativoContabilTest extends AbstractCasoTesteEmMemoria {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Autowired
	private DaoDemonstrativoContabil daoDemonstrativoContabil;

	@Test
	public void recuperarContas() {
		LocalDate dtMin = LocalDate.now().minusYears(10);
		LocalDate dtMax = LocalDate.now();
		String r = daoDemonstrativoContabil.recuperarContas("BB", dtMin, dtMax);
		Assert.assertNotNull("r nulo!", r);
	}

	@Test
	public void getExtendedStats() {
		ExtendedStats stats = daoDemonstrativoContabil
				.getExtendedStats("LUCRO LÍQUIDO");
		Assert.assertNotNull("stats nulo!", stats);
	}

}
