package br.com.afrcode.arquitetura.util.mensagem;

/**
 * Interface base de defini��o de mensagem.
 * 
 * Podem existir mensagens de diferentes tipos (erro, alerta, informa��o, etc.)
 * e seus respectivos tratadores. Ver ITratadorMensagem. Cada tratador de
 * mensagens saber� como formatar e como proceder em rela��o a mensagem.
 * 
 * A mensagem literal em geral ser� obtida de algum resource bundle java.
 * 
 * 
 */
public interface IMensagem {

    /**
     * M�todo de acesso ao c�dido da mensagem.
     * 
     * @return
     */
    String getCodMensagem();

    /**
     * M�todo de atribui��o do c�digo da mensagem.
     * 
     * @param codMensagem
     */
    void setCodMensagem(String codMensagem);

    /**
     * M�todo de obten��o da mensagem associada em geral obtida de algum
     * resource bundle.
     * 
     * @return
     */
    String getMensagem();

    /**
     * M�todo de atribui��o de messagem em geral obtida de algum resource
     * bundle.
     * 
     * @param mensagem
     */
    void setMensagem(String mensagem);

    /**
     * M�todo para tratar a mensagem em geral atrav�s de uma implementa��o de
     * ITratadorMensagem.
     */
    void tratarMensagem();

}
