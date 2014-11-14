package br.com.afrcode.arquitetura.modelo.entidade.dao;

import java.util.Collection;
import java.util.Map;

import br.com.afrcode.arquitetura.modelo.entidade.IEntidade;

/**
 * Interface de definição de DAO.
 * 
 * 
 * @param <T>
 *            Tipo do ID (Long, Integer, String, etc.)
 * @param <E>
 *            Subtipo de IEntidade
 */
public interface IDao<T extends Comparable<T>, E extends IEntidade<T>> {

	/**
	 * Método responsável por procurar por um objeto cujo id seja o id
	 * informado.
	 * 
	 * @param id
	 *            id
	 * @return objeto
	 */
	E procurarPorId(T id);

	/**
	 * Método responsável por salvar um objeto.
	 * 
	 * @param obj
	 *            obj
	 */
	void salvar(E obj);

	/**
	 * Método responsável por excluir um objeto.
	 * 
	 * @param obj
	 *            obj
	 */
	void excluir(E obj);

	/**
	 * Método responsável por excluir uma coleção de objetos em lote. Envolve:
	 * limpeza de cache de primeiro nível a cada processamento de um lote.
	 * 
	 * @param objs
	 * @param numObjsPorLote
	 *            número de objetos processados por lote
	 */
	void excluirEmLote(Collection<E> objs, int numObjsPorLote);

	/**
	 * Método de disparo de flush.
	 * 
	 * ATENÇÃO: O uso deste método só é necessário quando não há contexto
	 * transacional envolvido, ou seja, em operações em lote.
	 * 
	 */
	void sincronizar();

	/**
	 * Método para clear de cache de primeiro nível.
	 */
	void limparCache();

	/**
	 * Método responsável por obter todos os objetos de um determinado tipo.
	 * 
	 * @return
	 */
	Collection<E> recuperarTodos();

	/**
	 * Método responsável por obter todos os objetos de um determinado tipo com
	 * paginação em banco de dados.
	 * 
	 * @param pagina
	 *            índice da página atual em requisição
	 * @param quantidadeDeItens
	 *            quantidade de itens por página em requisição
	 * @return
	 */
	Collection<E> recuperarTodos(int pagina, int quantidadeDeItens);

	/**
	 * Método responsável por obter objetos de um determinado tipo com paginação
	 * em banco de dados.
	 * 
	 * @param qlString
	 *            consulta ql (HQL) a ser executada. Exemplo de qlString: from
	 *            Objeto.class.getName as obj where obj.atributoX =
	 *            :param_atributoX and obj.atributoY = :param_atributoY
	 * @param params
	 *            parâmetros da consulta {[nome, valor]}. Exemplo de parâmetros:
	 *            {[param_atributoX, valorX], [param_atributoY, valorY]}
	 * @param pagina
	 *            índice da página atual em requisição
	 * @param quantidadeDeItens
	 *            quantidade de itens por página em requisição
	 * @return
	 */
	Collection<E> recuperarObjetos(String qlString, Map<String, Object> params,
			int pagina, int quantidadeDeItens);

	/**
	 * Método responsável por salvar uma coleção de objetos em lote. Envolve:
	 * limpeza de cache de primeiro nível a cada processamento de um lote.
	 * 
	 * @param objs
	 * @param numObjsPorLote
	 *            número de objetos processados por lote
	 */
	void salvarEmLote(Collection<E> objs, int numObjsPorLote);

	/**
	 * Método para obtenção de uma instância, apta a ser persistida sem erros,
	 * do tipo gerido por este Dao.
	 * 
	 * @return
	 */
	E instanciarObjetoPersistivel();

}
