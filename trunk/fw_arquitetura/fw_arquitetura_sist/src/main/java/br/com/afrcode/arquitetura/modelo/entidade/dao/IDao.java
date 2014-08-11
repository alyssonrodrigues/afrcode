package br.com.afrcode.arquitetura.modelo.entidade.dao;

import java.util.Collection;
import java.util.Map;

import br.com.afrcode.arquitetura.modelo.entidade.IEntidade;

/**
 * Interface de defini��o de DAO.
 * 
 * 
 * @param <T>
 *            Tipo do ID (Long, Integer, String, etc.)
 * @param <E>
 *            Subtipo de IEntidade
 */
public interface IDao<T extends Comparable<T>, E extends IEntidade<T>> {

    /**
     * M�todo respons�vel por procurar por um objeto cujo id seja o id
     * informado.
     * 
     * @param id
     *            id
     * @return objeto
     */
    E procurarPorId(T id);

    /**
     * M�todo respons�vel por salvar um objeto.
     * 
     * @param obj
     *            obj
     */
    void salvar(E obj);

    /**
     * M�todo respons�vel por excluir um objeto.
     * 
     * @param obj
     *            obj
     */
    void excluir(E obj);

    /**
     * M�todo respons�vel por excluir uma cole��o de objetos em lote. Envolve:
     * limpeza de cache de primeiro n�vel a cada processamento de um lote.
     * 
     * @param objs
     * @param numObjsPorLote
     *            n�mero de objetos processados por lote
     */
    void excluirEmLote(Collection<E> objs, int numObjsPorLote);

    /**
     * M�todo de disparo de flush.
     * 
     * ATEN��O: O uso deste m�todo s� � necess�rio quando n�o h� contexto
     * transacional envolvido, ou seja, em opera��es em lote.
     * 
     */
    void sincronizar();

    /**
     * M�todo para clear de cache de primeiro n�vel.
     */
    void limparCache();

    /**
     * M�todo respons�vel por obter todos os objetos de um determinado tipo.
     * 
     * @return
     */
    Collection<E> recuperarTodos();

    /**
     * M�todo respons�vel por obter todos os objetos de um determinado tipo com
     * pagina��o em banco de dados.
     * 
     * @param pagina
     *            �ndice da p�gina atual em requisi��o
     * @param quantidadeDeItens
     *            quantidade de itens por p�gina em requisi��o
     * @return
     */
    Collection<E> recuperarTodos(int pagina, int quantidadeDeItens);

    /**
     * M�todo respons�vel por obter objetos de um determinado tipo com pagina��o
     * em banco de dados.
     * 
     * @param qlString
     *            consulta ql (HQL) a ser executada. Exemplo de qlString: from
     *            Objeto.class.getName as obj where obj.atributoX =
     *            :param_atributoX and obj.atributoY = :param_atributoY
     * @param params
     *            par�metros da consulta {[nome, valor]}. Exemplo de par�metros:
     *            {[param_atributoX, valorX], [param_atributoY, valorY]}
     * @param pagina
     *            �ndice da p�gina atual em requisi��o
     * @param quantidadeDeItens
     *            quantidade de itens por p�gina em requisi��o
     * @return
     */
    Collection<E> recuperarObjetos(String qlString, Map<String, Object> params, int pagina, int quantidadeDeItens);

    /**
     * M�todo respons�vel por salvar uma cole��o de objetos em lote. Envolve:
     * limpeza de cache de primeiro n�vel a cada processamento de um lote.
     * 
     * @param objs
     * @param numObjsPorLote
     *            n�mero de objetos processados por lote
     */
    void salvarEmLote(Collection<E> objs, int numObjsPorLote);

    /**
     * M�todo para obten��o de uma inst�ncia, apta a ser persistida sem erros,
     * do tipo gerido por este Dao.
     * 
     * @return
     */
    E instanciarObjetoPersistivel();

}
