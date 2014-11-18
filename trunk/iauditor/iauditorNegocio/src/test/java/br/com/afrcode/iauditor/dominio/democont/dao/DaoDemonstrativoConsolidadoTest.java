package br.com.afrcode.iauditor.dominio.democont.dao;

import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Assert;
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

	@Test
	public void recuperarDemonstrativosConsolidados() {
		final LocalDate dtMin = new LocalDate(2010, 3, 31);
		final LocalDate dtMax = new LocalDate(2014, 3, 31);
		List<DemonstrativoConsolidado> r = daoDemonstrativoConsolidado
				.recuperarDemonstrativosConsolidados("BB", dtMin, dtMax);
		Assert.assertNotNull("r nulo!", r);
	}

	@Test
	public void getContaExtendedStats() {
		final LocalDate dtMin = new LocalDate(2010, 3, 31);
		final LocalDate dtMax = new LocalDate(2014, 3, 31);
		ExtendedStats stats = daoDemonstrativoConsolidado
				.getContaExtendedStats("BB", dtMin, dtMax, "LUCRO LÍQUIDO",
						"valor");
		Assert.assertNotNull("stats nulo!", stats);
	}

	@Test
	public void getSubcontaExtendedStats() {
		final LocalDate dtMin = new LocalDate(2010, 3, 31);
		final LocalDate dtMax = new LocalDate(2014, 3, 31);

		ExtendedStats stats = daoDemonstrativoConsolidado
				.getSubcontaExtendedStats("BB", dtMin, dtMax,
						"RECEITAS DE INTERMEDIAÇÃO FINANCEIRA",
						"Operações de arrendamento mercantil", "valor");
		Assert.assertNotNull("stats nulo!", stats);
	}

}
