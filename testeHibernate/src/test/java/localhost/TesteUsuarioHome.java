package localhost;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

// Caso 1: Usuario (HBM) extends ObjetoPersistente (@MappedSuperclass)...
// Nao OK!
public class TesteUsuarioHome {

	@Before
	public void antesDeExecutarMetodoTU() {

	}

	@After
	public void aposExecutarMetodoTU() {

	}
	
	@Test
	public void testarFindById() {
		UsuarioHome dao = new UsuarioHome();
		Long id = 1L;
		Usuario u = dao.findById(1L);
		Assert.assertNotNull(u);
		Assert.assertEquals("", id, u.getId());
	}
}
