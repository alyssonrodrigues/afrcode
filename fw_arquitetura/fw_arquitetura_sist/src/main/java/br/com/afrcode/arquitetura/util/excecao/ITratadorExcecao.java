package br.com.afrcode.arquitetura.util.excecao;

/**
 * 
 * Interface base de implementação de tratadores de exceção.
 * 
 * 
 * @param <T> Subtipo de Throwable.
 */
public interface ITratadorExcecao<T extends Throwable> {

    /**
     * Método responsável por dar tratamento adequado a exceção.
     * 
     * Exemplo de tratamentos típicos: enviar email, registrar erro em BD, registrar em LOG, formatar mensagem para exibir ao
     * usuário, etc.
     * 
     * @param excecao
     */
    void tratarExcecao(T excecao);

}
