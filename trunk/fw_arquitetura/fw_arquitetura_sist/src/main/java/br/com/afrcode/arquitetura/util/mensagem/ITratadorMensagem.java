package br.com.afrcode.arquitetura.util.mensagem;

/**
 * Interface base para implementação de tratadores de IMensagem.
 * 
 * 
 * @param <T>
 *            Subtipo de IMensagem
 */
public interface ITratadorMensagem<T extends IMensagem> {

	/**
	 * Método responsável por dar tratamento adequado a mensagem.
	 * 
	 * Exemplo de tratamentos tópicos: enviar email, registrar mensagem BD,
	 * registrar em LOG, formatar mensagem para exibir ao usuário, etc.
	 * 
	 * @param mensagem
	 */
	void tratarMensagem(T mensagem);

}
