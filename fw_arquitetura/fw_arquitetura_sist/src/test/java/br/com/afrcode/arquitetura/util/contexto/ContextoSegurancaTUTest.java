package br.com.afrcode.arquitetura.util.contexto;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;

public class ContextoSegurancaTUTest extends AbstractCasoTesteEmMemoria {

	@Autowired
	private IContextoSeguranca contextoSeguranca;

	@Test
	public void testarContextoSeguranca() {
		User usuario = contextoSeguranca.getUsuarioAutenticado();
		Assert.assertNotNull(
				"Deveria existir um usuário autenticado para TUs!", usuario);
	}

}
