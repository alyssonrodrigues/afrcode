package br.com.afrcode.arquitetura.util.mensagem;

/**
 * Interface base de definição de mensagem.
 * 
 * Podem existir mensagens de diferentes tipos (erro, alerta, informação, etc.)
 * e seus respectivos tratadores. Ver ITratadorMensagem. Cada tratador de
 * mensagens saberá como formatar e como proceder em relação a mensagem.
 * 
 * A mensagem literal em geral será obtida de algum resource bundle java.
 * 
 * 
 */
public interface IMensagem {

	/**
	 * Método de acesso ao códido da mensagem.
	 * 
	 * @return
	 */
	String getCodMensagem();

	/**
	 * Método de atribuição do código da mensagem.
	 * 
	 * @param codMensagem
	 */
	void setCodMensagem(String codMensagem);

	/**
	 * Método de obtenção da mensagem associada em geral obtida de algum
	 * resource bundle.
	 * 
	 * @return
	 */
	String getMensagem();

	/**
	 * Método de atribuição de messagem em geral obtida de algum resource
	 * bundle.
	 * 
	 * @param mensagem
	 */
	void setMensagem(String mensagem);

	/**
	 * Método para tratar a mensagem em geral através de uma implementação de
	 * ITratadorMensagem.
	 */
	void tratarMensagem();

}
