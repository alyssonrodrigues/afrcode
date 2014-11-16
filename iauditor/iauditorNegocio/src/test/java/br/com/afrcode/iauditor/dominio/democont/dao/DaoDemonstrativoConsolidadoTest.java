package br.com.afrcode.iauditor.dominio.democont.dao;

import java.util.List;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;
import br.com.afrcode.iauditor.dominio.democont.DemonstrativoConsolidado;
import br.com.afrcode.iauditor.dominio.elasticsearch.ExtendedStats;

public class DaoDemonstrativoConsolidadoTest extends AbstractCasoTesteEmMemoria {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Autowired
	private DaoDemonstrativoConsolidado daoDemonstrativoConsolidado;

	@BeforeClass
	public static void setupLocale() {
		Locale.setDefault(new Locale("pt", "BR"));
	}

	@Test
	public void recuperarDemonstrativosConsolidados() {
		final LocalDate dtMin = new LocalDate(2010, 3, 31);
		final LocalDate dtMax = new LocalDate(2014, 3, 31);
		List<DemonstrativoConsolidado> r = daoDemonstrativoConsolidado
				.recuperarDemonstrativosConsolidados("BB", dtMin, dtMax);
		Assert.assertNotNull("r nulo!", r);
	}

	@Test
	public void getExtendedStats() {
		final LocalDate dtMin = new LocalDate(2010, 3, 31);
		final LocalDate dtMax = new LocalDate(2014, 3, 31);
		ExtendedStats stats = daoDemonstrativoConsolidado.getExtendedStats(
				"BB", dtMin, dtMax, "LUCRO LÍQUIDO", "valor");
		Assert.assertNotNull("stats nulo!", stats);
	}

}
