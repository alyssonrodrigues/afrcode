/**
 * 
 */
package curso.modelo.entidade.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import curso.modelo.entidade.Entidade;
import curso.modelo.util.Contexto;

/**
 * @author alysson
 * @param C tipo de Entidade
 * @param T tipo Java do id da Entidade
 */
public interface Dao<T, C extends Entidade<T>> {

	/**
	 * Metodo responsavel por procurar por um objeto cujo id  seja o id 
	 * informado.
	 * @param id id
	 * @return objeto
	 */
	public C procurar(T id);
	
	/**
	 * Metodo responsavel por procurar por objetos cujo valor de uma chave
	 * seja o valor informado.
	 * @param chave chave
	 * @param valor valor
	 * @return objeto
	 */
	public C procurar(String chave, Object valor);
	
	/**
	 * Metodo responsavel por procurar por objetos cujo valor de algumas
	 * chaves sejam os valores informados. 
	 * @param parametros parametros
	 * @return objetos
	 */
	public List<C> procurar(Map<String, Object> parametros);
	
	/**
	 * Metodo de pesquisa por exemplo.
	 * @param obj exemplo a ser pesquisado
	 * @return Collection
	 */
	public Collection<C> procurarPorExemplo(C obj);
	
	/**
	 * Metodo responsavel por salvar um objeto.
	 * @param obj obj
	 * @param contexto contexto
	 */
	public void salvar(C obj, Contexto contexto);
	
	/**
	 * Metodo responsavel por excluir um objeto.
	 * @param obj obj
	 * @param contexto contexto
	 */
	public void excluir(C obj, Contexto contexto);
	
	/**
	 * Metodo responsavel por obter todos os objetos de um determinado tipo.
	 * @return
	 */
	public Collection<C> recuperarTodos();
}
