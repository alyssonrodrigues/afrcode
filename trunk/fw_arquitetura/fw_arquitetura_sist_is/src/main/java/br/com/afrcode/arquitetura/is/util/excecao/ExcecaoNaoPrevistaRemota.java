package br.com.afrcode.arquitetura.is.util.excecao;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Classe base para lan�amento de exce��es, n�o previstas, obtidas em servi�os
 * remotos. Este ser� o tipo de exce��o n�o prevista visto pelos clientes de
 * servi�os remotos.
 * 
 * Servi�os remotos exp�e servi�os locais de sistemas que por sua vez
 * implementam regras de neg�cio locais. Exce��es locais, n�o previstas, a estas
 * regras de neg�cio locais devem ser relan�adas usando este, E SOMENTE este,
 * tipo, ExcecaoNaoPrevistaRemota.
 * 
 * � importante que a exce��o causa seja convertida para String, pois esta tende
 * a ser de um tipo desconhecido pelo cliente do servi�o remoto. Por isto o
 * atributo RuntimeException#getCause() ser� sempre nulo.
 * 
 * 
 */
public class ExcecaoNaoPrevistaRemota extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * A mensagem da pr�pria RuntimeException cont�m toda a stacktrace do erro.
     * Este atributo exp�e a mensagem "principal" do erro.
     */
    private String mensagem;

    /**
     * Construtor. Respons�vel por obter toda a stack trace da exce��o cause em
     * formato String em conjunto com a mensagem requerida.
     * 
     * N�o se deve atribuir a causa desta exce��o a causa requerida diretamente
     * pois o cliente de um servi�o remoto n�o necessariamente ter� o tipo da
     * exce��o causa em classpath.
     * 
     * @param mensagem
     * @param cause
     */
    public ExcecaoNaoPrevistaRemota(String mensagem, Throwable cause) {
        // message conter� a fullstracktrace
        super(mensagem + "#" + ExceptionUtils.getFullStackTrace(cause));
        // mensagem conter� a mensagem requerida pelo desenvolvedor
        this.mensagem = mensagem;
    }

    /**
     * Construtor. Respons�vel por obter toda a stack trace da exce��o cause em
     * formato String.
     * 
     * N�o se deve atribuir a causa desta exce��o a causa requerida diretamente
     * pois o cliente de um servi�o remoto n�o necessariamente ter� o tipo da
     * exce��o causa em classpath.
     * 
     * @param cause
     */
    public ExcecaoNaoPrevistaRemota(Throwable cause) {
        // message conter� a fullstracktrace
        super(ExceptionUtils.getFullStackTrace(cause));
    }

    /**
     * Retorna a mensagem da exce��o
     * 
     * @return
     */
    public String getMensagem() {
        return this.mensagem;
    }

    /**
     * Seta a mensagem da exce��o
     * 
     * @param mensagem
     */
    public void setMensagem(String mensagem) {

        this.mensagem = mensagem;
    }
}
