package br.com.afrcode.arquitetura.util.mensagem;

/**
 * Interface base para implementa��o de tratadores de IMensagem.
 * 
 * 
 * @param <T>
 *            Subtipo de IMensagem
 */
public interface ITratadorMensagem<T extends IMensagem> {

    /**
     * M�todo respons�vel por dar tratamento adequado a mensagem.
     * 
     * Exemplo de tratamentos t�picos: enviar email, registrar mensagem BD,
     * registrar em LOG, formatar mensagem para exibir ao usu�rio, etc.
     * 
     * @param mensagem
     */
    void tratarMensagem(T mensagem);

}
