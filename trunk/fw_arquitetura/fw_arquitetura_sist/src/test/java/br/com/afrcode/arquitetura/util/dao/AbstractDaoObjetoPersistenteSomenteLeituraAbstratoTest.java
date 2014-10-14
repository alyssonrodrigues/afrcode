package br.com.afrcode.arquitetura.util.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.afrcode.arquitetura.modelo.entidade.IEntidade;
import br.com.afrcode.arquitetura.modelo.entidade.dao.DaoJpaAbstrato;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTeste;

/**
 * Classe base para testes de implementa��es de IDao de
 * ObjetoPersistenteAbstrato de leitura apenas - tipicamente Entidades mapeadas
 * como Immutable.
 * 
 * Subclasses desta classe herdar�o testes para as opera��es CRUD b�sicas.
 * 
 * Pretende-se que esta seja a super classe de testes de todos os testes de
 * implementa��es de IDao de ObjetoPersistenteAbstrato de leitura apenas -
 * tipicamente Entidades mapeadas como Immutable.
 * 
 * 
 * @param <T>
 *            Tipo do ID (Long, Integer, String, etc.)
 * @param <E>
 *            Subtipo de IEntidade
 */
public abstract class AbstractDaoObjetoPersistenteSomenteLeituraAbstratoTest<T extends Comparable<T>, E extends IEntidade<T>>
        extends AbstractCasoTeste {

    protected abstract DaoJpaAbstrato<T, E> getDao();

    @Autowired
    private ExecutorTUCRUDDaoUtil testadorDaoUtil;

    @Test
    public void testarRecuperarTodosJaCadastradosComPaginacao() {
        testadorDaoUtil.testarRecuperarTodosJaCadastradosComPaginacao(getDao());
    }

}