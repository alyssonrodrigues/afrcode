package localhost;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

// Caso 3: Chamado (@Entity) extends ObjetoPersistente (@MappedSuperclass) ...
// OK!
// Caso 3: Chamado (@Entity) one-to-one com Ocorrencia (HBM) ...
// OK!
public class TesteChamadoHome {

	@Before
	public void antesDeExecutarMetodoTU() {

	}

	@After
	public void aposExecutarMetodoTU() {

	}
	
	@Test
	public void testarFindById() {
		ChamadoHome dao = new ChamadoHome();
		Long id = 1L;
		Chamado c = dao.findById(id);
		Assert.assertNotNull(c);
		Assert.assertEquals("", id, c.getId());
	}
}
