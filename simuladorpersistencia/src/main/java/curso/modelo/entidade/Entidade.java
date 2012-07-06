/**
 * 
 */
package curso.modelo.entidade;

import curso.modelo.util.Contexto;

/**
 * @author alysson
 * @param T tipo Java do id da Entidade
 */
public interface Entidade<T> {

	/**
	 * Metodo de obtencao da propriedade id.
	 * @return id
	 */
	public T getId();
	
	/**
	 * Metodo de atribuicao da propriedade id.
	 * @param id id
	 */
	public void setId(T id);
	
	/**
	 * Metodo para validacao de regras de negocio anteriores ao salvar.
	 * @param contexto contexto
	 */
	public void validarSalvamento(Contexto contexto);
	
	/**
	 * Metodo para validacao de regras de negocio anteriores ao excluir.
	 * @param contexto contexto
	 */
	public void validarExclusao(Contexto contexto);
	
	/**
	 * Metodo para criacao de instancias preenchidas com valores padroes.
	 */
	public void preencherComValoresDefault();
	
}
