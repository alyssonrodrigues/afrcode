package afrcode.fwarquitetura.util.mensagem;

/**
 * Interface base para implementação de tratadores de {@link IMensagem}.
 * 
 * @author alyssonfr
 * 
 * @param <TIPOMENSAGEM> Subtipo de {@link IMensagem}
 */
public interface ITratadorMensagem<TIPOMENSAGEM extends IMensagem> {

    /**
     * Método responsável por dar tratamento adequado a mensagem.
     * 
     * Exemplo de tratamentos típicos: enviar email, registrar mensagem BD, registrar em LOG, formatar mensagem para exibir ao
     * usuário, etc.
     * 
     * @param mensagem
     */
    public void tratarMensagem(TIPOMENSAGEM mensagem);

    /**
     * Método de obtenção da mensagem textual em geral obtida através de um resource bundle java.
     * 
     * @param mensagem
     * @return
     */
    public String getMensagem(TIPOMENSAGEM mensagem);

}
