package br.com.afrcode.arquitetura.util.excecao;

/**
 * 
 * Interface base de implementa��o de tratadores de exce��o.
 * 
 * 
 * @param <T> Subtipo de Throwable.
 */
public interface ITratadorExcecao<T extends Throwable> {

    /**
     * M�todo respons�vel por dar tratamento adequado a exce��o.
     * 
     * Exemplo de tratamentos t�picos: enviar email, registrar erro em BD, registrar em LOG, formatar mensagem para exibir ao
     * usu�rio, etc.
     * 
     * @param excecao
     */
    void tratarExcecao(T excecao);

}
