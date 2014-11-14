package br.com.afrcode.arquitetura.apresentacao.managedbean;

import br.com.afrcode.arquitetura.util.mensagem.IMensagem;

/**
 * Interface de definição de JSF managed beans.
 * 
 * 
 */
public interface IManagedBean {

	/**
	 * Método central de adição de mensagens cujo tratamento depende de seu
	 * tipo.
	 * 
	 * Ver IMensagem e ITratadorMensagem.
	 * 
	 * @param mensagem
	 */
	void adicionarMensagem(IMensagem mensagem);

	/**
	 * Método de adição de objetos em sessão HTTP.
	 * 
	 * Não é permitida a inclusão de Entidades (objetos IEntidade) em sessão
	 * HTTP - problemas relacionados: detached object, stalled data, lazy
	 * initialization exceptions, resources leak, etc.
	 * 
	 * @param chave
	 *            chave única do objeto em sessão
	 * @param objetoEmSessao
	 *            um objeto para adição em sessão
	 * 
	 * @throws IllegalArgumentException
	 *             quando o objeto é na verdade uma IEntidade
	 */
	void adicionarObjetoEmSessaoHttp(String chave, Object objetoEmSessao);

	/**
	 * Método de remoção de objetos em sessão HTTP através de chave única
	 * identificadora.
	 * 
	 * @param chave
	 */
	void removerObjetoEmSessaoHttp(String chave);

	/**
	 * Método de obtenção de objetos em sessão HTTP através de chave única
	 * identificadora.
	 * 
	 * @param chave
	 * @return null ou o objeto em sessão encontrado
	 */
	Object getObjetoEmSessao(String chave);

}
