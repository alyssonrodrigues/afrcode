package br.com.afrcode.arquitetura.is.util.excecao;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Classe base para encapsular exceções de negócio lançadas por serviços
 * remotos. Este será o tipo de exceço de negócio visto pelos clientes de
 * serviços remotos.
 * 
 * Em geral estas exceções são conhecidas e tratadas gerando informações e
 * mensagens exibidas ou enviadas ao usuário final.
 * 
 * Serviços remotos expõe serviços locais de sistemas que por sua vez
 * implementam regras de negócio locais. Exceções de negócio locais a estas
 * regras de negócio devem ser relan�adas usando este, E SOMENTE este, tipo,
 * ExcecaoNegocioRemota.
 * 
 * É importante que a exceção causa seja convertida para String, pois esta tende
 * a ser de um tipo desconhecido pelo cliente do serviço remoto. Por isto o
 * atributo RuntimeException#getCause() será sempre nulo.
 * 
 * 
 */
public class ExcecaoNegocioRemota extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Enum que descreve a Severidade do erro
	 */
	public static enum Severidade {
		ERRO, ALERTA, INFO;
	}

	private Severidade severidade = Severidade.ERRO;

	private String mensagem;

	/**
	 * 
	 * @param mensagem
	 * @param cause
	 */
	public ExcecaoNegocioRemota(String mensagem, Throwable cause) {
		// message conterá a fullstracktrace
		super(mensagem + "#" + ExceptionUtils.getFullStackTrace(cause));
		// mensagem conterá a mensagem requerida pelo desenvolvedor
		this.mensagem = mensagem;
	}

	/**
	 * 
	 * @param cause
	 */
	public ExcecaoNegocioRemota(Throwable cause) {
		// message conterá a fullstracktrace
		super(ExceptionUtils.getFullStackTrace(cause));
	}

	/**
	 * Construtor para Erros de Negocio com a definição de serveridade
	 * 
	 * @param mensagem
	 * @param cause
	 * @param severidade
	 */
	public ExcecaoNegocioRemota(String mensagem, Throwable cause,
			Severidade severidade) {
		// message conterá a fullstracktrace
		super(mensagem + "#" + ExceptionUtils.getFullStackTrace(cause));
		this.severidade = severidade;
		// mensagem conterá a mensagem requerida pelo desenvolvedor
		this.mensagem = mensagem;
	}

	public Severidade getSeveridade() {
		return this.severidade;
	}

	public void setSeveridade(Severidade severidade) {
		this.severidade = severidade;
	}

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem
	 *            the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
