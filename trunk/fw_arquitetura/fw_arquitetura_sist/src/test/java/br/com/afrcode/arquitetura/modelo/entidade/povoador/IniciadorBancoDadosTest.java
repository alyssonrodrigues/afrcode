package br.com.afrcode.arquitetura.modelo.entidade.povoador;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;

public class IniciadorBancoDadosTest extends AbstractCasoTesteEmMemoria {
	private static final Logger LOG = Logger
			.getLogger(IniciadorBancoDadosTest.class);

	@Autowired
	private IniciadorBancoDados iniciadorBancoDados;

	@Test
	public void testarIniciadorBancoDados() {
		try {
			iniciadorBancoDados.povoar();
		} catch (Exception e) {
			LOG.error(e);
			Assert.fail(e.getMessage());
		}
	}

}
