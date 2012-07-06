package afrcode.fwarquitetura.util.mensagem;

/**
 * Interface base para implementa��o de tratadores de {@link IMensagem}.
 * 
 * @author alyssonfr
 * 
 * @param <TIPOMENSAGEM> Subtipo de {@link IMensagem}
 */
public interface ITratadorMensagem<TIPOMENSAGEM extends IMensagem> {

    /**
     * M�todo respons�vel por dar tratamento adequado a mensagem.
     * 
     * Exemplo de tratamentos t�picos: enviar email, registrar mensagem BD, registrar em LOG, formatar mensagem para exibir ao
     * usu�rio, etc.
     * 
     * @param mensagem
     */
    public void tratarMensagem(TIPOMENSAGEM mensagem);

    /**
     * M�todo de obten��o da mensagem textual em geral obtida atrav�s de um resource bundle java.
     * 
     * @param mensagem
     * @return
     */
    public String getMensagem(TIPOMENSAGEM mensagem);

}
