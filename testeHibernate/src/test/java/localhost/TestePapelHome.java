package localhost;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

// Caso 2: Papel (HBM) ...
// OK!
public class TestePapelHome {

	@Before
	public void antesDeExecutarMetodoTU() {

	}

	@After
	public void aposExecutarMetodoTU() {

	}
	
	@Test
	public void testarFindById() {
		PapelHome dao = new PapelHome();
		Long id = 1L;
		Papel papel = dao.findById(id);
		Assert.assertNotNull(papel);
		Assert.assertEquals("", id, papel.getId());
	}
}
