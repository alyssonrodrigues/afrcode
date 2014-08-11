package br.com.afrcode.arquitetura.is.util.excecao;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Classe base para encapsular exce��es de neg�cio lan�adas por servi�os
 * remotos. Este ser� o tipo de exce��o de neg�cio visto pelos clientes de
 * servi�os remotos.
 * 
 * Em geral estas exce��es s�o conhecidas e tratadas gerando informa��es e
 * mensagens exibidas ou enviadas ao usu�rio final.
 * 
 * Servi�os remotos exp�e servi�os locais de sistemas que por sua vez
 * implementam regras de neg�cio locais. Exce��es de neg�cio locais a estas
 * regras de neg�cio devem ser relan�adas usando este, E SOMENTE este, tipo,
 * ExcecaoNegocioRemota.
 * 
 * � importante que a exce��o causa seja convertida para String, pois esta tende
 * a ser de um tipo desconhecido pelo cliente do servi�o remoto. Por isto o
 * atributo RuntimeException#getCause() ser� sempre nulo.
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
        // message conter� a fullstracktrace
        super(mensagem + "#" + ExceptionUtils.getFullStackTrace(cause));
        // mensagem conter� a mensagem requerida pelo desenvolvedor
        this.mensagem = mensagem;
    }

    /**
     * 
     * @param cause
     */
    public ExcecaoNegocioRemota(Throwable cause) {
        // message conter� a fullstracktrace
        super(ExceptionUtils.getFullStackTrace(cause));
    }

    /**
     * Construtor para Erros de Negocio com a defini��o de serveridade
     * 
     * @param mensagem
     * @param cause
     * @param severidade
     */
    public ExcecaoNegocioRemota(String mensagem, Throwable cause, Severidade severidade) {
        // message conter� a fullstracktrace
        super(mensagem + "#" + ExceptionUtils.getFullStackTrace(cause));
        this.severidade = severidade;
        // mensagem conter� a mensagem requerida pelo desenvolvedor
        this.mensagem = mensagem;
    }

    /**
     * Retorna a serveridade do erro de neg�cio remoto
     * 
     * @return
     */
    public Severidade getSeveridade() {
        return this.severidade;
    }

    /**
     * Seta a severidade do erro de neg�cio remoto
     * 
     * @param severidade
     */
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
