package br.com.afrcode.arquitetura.util.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.afrcode.arquitetura.modelo.entidade.IEntidade;
import br.com.afrcode.arquitetura.modelo.entidade.dao.DaoJpaAbstrato;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;

/**
 * Classe base para testes, EM MEM�RIA, de implementa��es de IDao. Subclasses
 * desta classe herdar�o testes para as opera��es CRUD b�sicas.
 * 
 * Pretende-se que esta seja a super classe de testes, EM MEM�RIA, de todos os
 * testes de implementa��es de IDao.
 * 
 * 
 * @param <T>
 *            Tipo do ID (Long, Integer, String, etc.)
 * @param <E>
 *            Subtipo de IEntidade
 */
public abstract class AbstractDaoObjetoPersistenteEmMemoriaAbstratoTest<T extends Comparable<T>, E extends IEntidade<T>>
        extends AbstractCasoTesteEmMemoria {

    protected abstract DaoJpaAbstrato<T, E> getDao();

    @Autowired
    private ExecutorTUCRUDDaoUtil testadorDaoUtil;

    @Test
    public void testarSalvarEProcurar() {
        testadorDaoUtil.testarSalvarEProcurar(getDao());
    }

    @Test
    public void testarExcluir() {
        testadorDaoUtil.testarExcluir(getDao());
    }

    @Test
    public void testarProcurarPorId() {
        testadorDaoUtil.testarProcurarPorId(getDao());
    }

    @Test
    public void testarRecuperarObjetosComPaginacao() {
        testadorDaoUtil.testarRecuperarObjetosComPaginacao(getDao());
    }

}
