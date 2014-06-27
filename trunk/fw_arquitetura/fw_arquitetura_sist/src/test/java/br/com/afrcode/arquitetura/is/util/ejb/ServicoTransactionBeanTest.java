package br.com.afrcode.arquitetura.is.util.ejb;

import java.util.concurrent.Callable;

import javax.ejb.EJB;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;

public class ServicoTransactionBeanTest extends AbstractCasoTesteEmMemoria {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@EJB
	private Caller caller;

	@Test
	public void testar() throws Exception {
		caller.call(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				// Nada faz.
				return null;
			}
		});
	}

}
