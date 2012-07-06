/**
 * 
 */
package curso.modelo.entidade.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import curso.modelo.entidade.EntidadeStub;
import curso.modelo.util.ContextoStub;

/**
 * @author alysson
 * 
 */
public class TesteDaoEntidadeStub extends TestCase {
	private DaoEntidadeStub dao = new DaoEntidadeStub();

	public void testarProcurar() {
		EntidadeStub objA = new EntidadeStub();
		objA.setId(1L);
		dao.salvar(objA, new ContextoStub());
		
		EntidadeStub objB = dao.procurar(1L);
		assertEquals(objA.getId(), objB.getId());
	}
	
	public void testarProcurarPorChave() {
		EntidadeStub objA = new EntidadeStub();
		objA.setId(1L);
		dao.salvar(objA, new ContextoStub());

		EntidadeStub objB = dao.procurar("id", 1L);
		assertNotNull(objB);
		assertEquals(objA.getId(), objB.getId());
	}
	
	public void testarProcurarPorParametros() {
		EntidadeStub objA = new EntidadeStub();
		objA.setId(1L);
		dao.salvar(objA, new ContextoStub());
		
		Map<String, Object> parametros =
			new HashMap<String, Object>();
		parametros.put("id", 1L);

		List<EntidadeStub> ents = dao.procurar(parametros);
		assertTrue(!ents.isEmpty());
		EntidadeStub objB = ents.iterator().next();
		assertEquals(objA.getId(), objB.getId());
	}
	
	public void testarProcurarPorExemplo() {
		EntidadeStub objA = new EntidadeStub();
		objA.setId(1L);
		dao.salvar(objA, new ContextoStub());

		Collection<EntidadeStub> ents = dao.procurarPorExemplo(objA);
		assertTrue("Deveria ter sido retornado ao menos um objeto!",
				!ents.isEmpty());
	}
	
	public void testarSalvar() {
		testarProcurar();
	}
	
	public void testarExcluir() {
		testarProcurar();
		EntidadeStub objA = dao.procurar(1L);
		dao.excluir(objA, new ContextoStub());
		
		EntidadeStub objB = dao.procurar(1L);
		assertNull(objB);
	}
	
	public void testarRecuperarTodos() {
		testarProcurar();
		int r = dao.recuperarTodos().size();
		assertEquals(1, r);
	}

}
