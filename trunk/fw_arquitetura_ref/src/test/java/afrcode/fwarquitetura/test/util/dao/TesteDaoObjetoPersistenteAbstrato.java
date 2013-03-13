package afrcode.fwarquitetura.test.util.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import afrcode.fwarquitetura.modelo.entidade.IEntidade;
import afrcode.fwarquitetura.modelo.entidade.dao.DaoJpaAbstrato;
import afrcode.fwarquitetura.modelo.entidade.dao.IDao;
import afrcode.fwarquitetura.tu.util.junit.CasoTeste;
import afrcode.fwarquitetura.tu.util.junit.CasoTesteEmMemoria;

/**
 * Classe base para testes de implementa��es de {@link IDao}. Subclasses desta classe herdar�o testes para as opera��es CRUD
 * b�sicas.
 * 
 * Pretende-se que esta seja a super classe de testes de todos os testes de implementa��es de {@link IDao}.
 * 
 * TODO: rever se ser� subtipo de {@link CasoTeste} ou {@link CasoTesteEmMemoria}!
 * 
 * @author alyssonfr
 * 
 * @param <TIPOID> Tipo do ID (Long, Integer, String, etc.)
 * @param Subtipo de {@link IEntidade}
 */
public abstract class TesteDaoObjetoPersistenteAbstrato<TIPOID extends Comparable<TIPOID>, TIPOENTIDADE extends IEntidade<TIPOID>>
        extends CasoTesteEmMemoria {

    protected abstract DaoJpaAbstrato<TIPOID, TIPOENTIDADE> getDao();

    @Test
    public void testarSalvarEProcurar() {
        TIPOENTIDADE stubA = getDao().instaciarObjetoPersistivelParaTestes();
        getDao().salvar(stubA);
        TIPOENTIDADE stubB = getDao().instaciarObjetoPersistivelParaTestes();
        getDao().salvar(stubB);
        TIPOENTIDADE r = getDao().procurarPorId(stubA.getId());
        assertEquals("O objeto n�o foi persistido e/ou recuperado!", stubA, r);
    }

    @Test
    public void testarExcluir() {
        TIPOENTIDADE stub = getDao().instaciarObjetoPersistivelParaTestes();
        getDao().salvar(stub);
        TIPOID id = stub.getId();
        getDao().excluir(stub);
        TIPOENTIDADE r = getDao().procurarPorId(id);
        assertNull("O objeto n�o foi exclu�do!", r);
    }

    @Test
    public void testarProcurarPorId() {
        TIPOENTIDADE stubA = getDao().instaciarObjetoPersistivelParaTestes();
        getDao().salvar(stubA);
        TIPOID id = stubA.getId();
        TIPOENTIDADE stubB = getDao().instaciarObjetoPersistivelParaTestes();
        getDao().salvar(stubB);
        TIPOENTIDADE r = getDao().procurarPorId(id);
        assertEquals("O objeto recuperado n�o foi o esperado!", id, r.getId());
    }

    @Test
    public void testarRecuperarTodos() {
        TIPOENTIDADE stubA = getDao().instaciarObjetoPersistivelParaTestes();
        getDao().salvar(stubA);
        TIPOENTIDADE stubB = getDao().instaciarObjetoPersistivelParaTestes();
        getDao().salvar(stubB);
        Collection<TIPOENTIDADE> objs = new ArrayList<TIPOENTIDADE>();
        objs.add(stubA);
        objs.add(stubB);

        Collection<TIPOENTIDADE> r = getDao().recuperarTodos();
        assertTrue("Os objetos recuperados n�o s�o os esperados",
                r.containsAll(objs));
    }

}
