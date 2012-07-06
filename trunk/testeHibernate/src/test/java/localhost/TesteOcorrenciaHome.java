package localhost;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

// Caso 4: Ocorrencia (HBM) many-to-one com Chamado (@Entity) ...
// OK!
public class TesteOcorrenciaHome {

	@Before
	public void antesDeExecutarMetodoTU() {

	}

	@After
	public void aposExecutarMetodoTU() {

	}
	
	@Test
	public void testarFindById() {
		OcorrenciaHome dao = new OcorrenciaHome();
		Long id = 1L;
		Ocorrencia o = dao.findById(id);
		Assert.assertNotNull(o);
		Assert.assertEquals("", id, o.getId());
	}

}
