/**
 * 
 */
package cursojsf.test.modelo.entidade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import curso.modelo.util.ContextoStub;
import cursojsf.modelo.entidade.ObjetoPersistente;
import cursojsf.modelo.entidade.dao.DaoJpaAbstrato;
import cursojsf.test.modelo.sistema.CasoTeste;

/**
 * @author alysson
 *
 */
public abstract class TesteDaoObjetoPersistente<E extends ObjetoPersistente> 
	extends CasoTeste {
	
	protected abstract DaoJpaAbstrato<E> getDao();
	
	@Test
	public void testarSalvarEProcurar() {
		E stubA = getDao().instanciarObjetoPadrao();
		getDao().salvar(stubA, new ContextoStub());
		E stubB = getDao().instanciarObjetoPadrao();
		getDao().salvar(stubB, new ContextoStub());
		E r = getDao().procurar(stubA.getId());
		assertEquals("O objeto nao foi persistido e/ou recuperado!", stubA, r);
	}
	
	@Test
	public void testarExcluir() {
		E stub = getDao().instanciarObjetoPadrao();
		getDao().salvar(stub, new ContextoStub());
		Long id = stub.getId();
		getDao().excluir(stub, new ContextoStub());
		E r = getDao().procurar(id);
		assertNull("O objeto nao foi excluido!", r);
	}

	@Test
	public void testarProcurar() {
		E stubA = getDao().instanciarObjetoPadrao();
		getDao().salvar(stubA, new ContextoStub());
		
		Long id = stubA.getId();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		E stubB = getDao().instanciarObjetoPadrao();
		getDao().salvar(stubB, new ContextoStub());
		
		Collection<E> objs = getDao().procurar(parametros);
		assertTrue("Nao foram recuperados os objetos desejados!", 
				!objs.isEmpty());
		assertTrue("Foi recuperado mais de um objeto!", objs.size() == 1);
		E r = objs.iterator().next();
		assertEquals("O objeto recuperado nao foi o esperado!", id, r.getId());
	}
	
	@Test
	public void testarRecuperarTodos() {
		E stubA = getDao().instanciarObjetoPadrao();
		getDao().salvar(stubA, new ContextoStub());
		E stubB = getDao().instanciarObjetoPadrao();
		getDao().salvar(stubB, new ContextoStub());
		Collection<E> objs = new ArrayList<E>();
		objs.add(stubA);
		objs.add(stubB);
		
		Collection<E> r = getDao().recuperarTodos();
		assertTrue("Os objetos recuperados nao sao os esperados", 
				r.containsAll(objs));
	}
	
	@Test
	public void testarProcurarPorExemplo() {
		E stub = getDao().instanciarObjetoPadrao();
		getDao().salvar(stub, new ContextoStub());
		Collection<E> objs = getDao().procurarPorExemplo(stub);
		assertTrue("Deveria ter sido retornado ao menos um objeto!",
				!objs.isEmpty());
	}

}
