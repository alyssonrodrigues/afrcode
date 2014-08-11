package br.com.afrcode.arquitetura.is.util.excecao;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Classe base para lançamento de exceções, não previstas, obtidas em serviços
 * remotos. Este será o tipo de exceção não prevista visto pelos clientes de
 * serviços remotos.
 * 
 * Serviços remotos expõe serviços locais de sistemas que por sua vez
 * implementam regras de negócio locais. Exceções locais, não previstas, a estas
 * regras de negócio locais devem ser relançadas usando este, E SOMENTE este,
 * tipo, ExcecaoNaoPrevistaRemota.
 * 
 * É importante que a exceção causa seja convertida para String, pois esta tende
 * a ser de um tipo desconhecido pelo cliente do serviço remoto. Por isto o
 * atributo RuntimeException#getCause() será sempre nulo.
 * 
 * 
 */
public class ExcecaoNaoPrevistaRemota extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * A mensagem da própria RuntimeException contém toda a stacktrace do erro.
     * Este atributo expõe a mensagem "principal" do erro.
     */
    private String mensagem;

    /**
     * Construtor. Responsável por obter toda a stack trace da exceção cause em
     * formato String em conjunto com a mensagem requerida.
     * 
     * Não se deve atribuir a causa desta exceção a causa requerida diretamente
     * pois o cliente de um serviço remoto não necessariamente terá o tipo da
     * exceção causa em classpath.
     * 
     * @param mensagem
     * @param cause
     */
    public ExcecaoNaoPrevistaRemota(String mensagem, Throwable cause) {
        // message conterá a fullstracktrace
        super(mensagem + "#" + ExceptionUtils.getFullStackTrace(cause));
        // mensagem conterá a mensagem requerida pelo desenvolvedor
        this.mensagem = mensagem;
    }

    /**
     * Construtor. Responsável por obter toda a stack trace da exceção cause em
     * formato String.
     * 
     * Não se deve atribuir a causa desta exceção a causa requerida diretamente
     * pois o cliente de um serviço remoto não necessariamente terá o tipo da
     * exceção causa em classpath.
     * 
     * @param cause
     */
    public ExcecaoNaoPrevistaRemota(Throwable cause) {
        // message conterá a fullstracktrace
        super(ExceptionUtils.getFullStackTrace(cause));
    }

    /**
     * Retorna a mensagem da exceção
     * 
     * @return
     */
    public String getMensagem() {
        return this.mensagem;
    }

    /**
     * Seta a mensagem da exceção
     * 
     * @param mensagem
     */
    public void setMensagem(String mensagem) {

        this.mensagem = mensagem;
    }
}
