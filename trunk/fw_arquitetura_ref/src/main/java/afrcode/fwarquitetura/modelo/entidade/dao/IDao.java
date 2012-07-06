package afrcode.fwarquitetura.modelo.entidade.dao;

import java.util.Collection;

import afrcode.fwarquitetura.modelo.entidade.IEntidade;

/**
 * Interface de definição de DAO.
 * 
 * @author alyssonfr
 * 
 * @param <TIPOID> Tipo do ID (Long, Integer, String, etc.)
 * @param <TIPOENTIDADE> Subtipo de {@link IEntidade}
 */
public interface IDao<TIPOID, TIPOENTIDADE extends IEntidade<TIPOID>> {

    /**
     * Método responsável por procurar por um objeto cujo id seja o id
     * informado.
     * 
     * @param id id
     * @return objeto
     */
    public TIPOENTIDADE procurarPorId(TIPOID id);

    /**
     * Método responsável por salvar um objeto.
     * 
     * @param obj obj
     */
    public void salvar(TIPOENTIDADE obj);

    /**
     * Método responsável por excluir um objeto.
     * 
     * @param obj obj
     */
    public void excluir(TIPOENTIDADE obj);

    /**
     * Método de disparo de flush.
     * 
     * ATENÇÃO: O uso deste método só é necessário quando não há contexto transacional envolvido, ou seja, em operações em lote.
     * 
     */
    public void sincronizar();

    /**
     * Método para clear de cache de primeiro nível.
     */
    public void limparCache();

    /**
     * Método responsável por obter todos os objetos de um determinado tipo.
     * 
     * @return
     */
    public Collection<TIPOENTIDADE> recuperarTodos();

    /**
     * Método para obteção de uma instância real PARA TESTES, apta a ser persistida sem erros, do tipo gerido por este Dao.
     * 
     * @return
     */
    public TIPOENTIDADE instaciarObjetoPersistivelParaTestes();

}
