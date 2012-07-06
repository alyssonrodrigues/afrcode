package afrcode.fwarquitetura.modelo.entidade.dao;

import java.util.Collection;

import afrcode.fwarquitetura.modelo.entidade.IEntidade;

/**
 * Interface de defini��o de DAO.
 * 
 * @author alyssonfr
 * 
 * @param <TIPOID> Tipo do ID (Long, Integer, String, etc.)
 * @param <TIPOENTIDADE> Subtipo de {@link IEntidade}
 */
public interface IDao<TIPOID, TIPOENTIDADE extends IEntidade<TIPOID>> {

    /**
     * M�todo respons�vel por procurar por um objeto cujo id seja o id
     * informado.
     * 
     * @param id id
     * @return objeto
     */
    public TIPOENTIDADE procurarPorId(TIPOID id);

    /**
     * M�todo respons�vel por salvar um objeto.
     * 
     * @param obj obj
     */
    public void salvar(TIPOENTIDADE obj);

    /**
     * M�todo respons�vel por excluir um objeto.
     * 
     * @param obj obj
     */
    public void excluir(TIPOENTIDADE obj);

    /**
     * M�todo de disparo de flush.
     * 
     * ATEN��O: O uso deste m�todo s� � necess�rio quando n�o h� contexto transacional envolvido, ou seja, em opera��es em lote.
     * 
     */
    public void sincronizar();

    /**
     * M�todo para clear de cache de primeiro n�vel.
     */
    public void limparCache();

    /**
     * M�todo respons�vel por obter todos os objetos de um determinado tipo.
     * 
     * @return
     */
    public Collection<TIPOENTIDADE> recuperarTodos();

    /**
     * M�todo para obte��o de uma inst�ncia real PARA TESTES, apta a ser persistida sem erros, do tipo gerido por este Dao.
     * 
     * @return
     */
    public TIPOENTIDADE instaciarObjetoPersistivelParaTestes();

}
