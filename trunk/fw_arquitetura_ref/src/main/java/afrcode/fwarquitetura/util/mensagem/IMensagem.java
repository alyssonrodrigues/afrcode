package afrcode.fwarquitetura.util.mensagem;

/**
 * Interface base de defini��o de mensagem.
 * 
 * Podem existir mensagens de diferentes tipos (erro, alerta, informa��o, etc.) e seus respectivos tratadores.
 * Ver {@link ITratadorMensagem}. Cada tratador de mensagens saber� como formatar e como proceder em rela��o a mensagem.
 * 
 * A mensagem literal em geral ser� obtida de algum resource bundle java.
 * 
 * @author alyssonfr
 * 
 */
public interface IMensagem {

    /**
     * M�todo de acesso ao c�dido da mensagem.
     * 
     * @return
     */
    public String getCodMensagem();

    /**
     * M�todo de atribui��o do c�digo da mensagem.
     * 
     * @param codMensagem
     */
    public void setCodMensagem(String codMensagem);

    /**
     * M�todo de obten��o da mensagem associada em geral obtida de algum resource bundle.
     * 
     * @return
     */
    public String getMensagem();

    /**
     * M�todo de atribui��o de messagem em geral obtida de algum resource bundle.
     * 
     * @param mensagem
     */
    public void setMensagem(String mensagem);

    /**
     * M�todo para tratar a mensagem em geral atrav�s de uma implementa��o de {@link ITratadorMensagem}.
     */
    public void tratarMensagem();

}
