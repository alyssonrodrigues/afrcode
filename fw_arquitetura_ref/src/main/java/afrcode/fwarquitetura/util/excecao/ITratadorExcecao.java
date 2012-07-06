package afrcode.fwarquitetura.util.excecao;

/**
 * 
 * Interface base de implementação de tratadores de exceção.
 * 
 * @author alyssonfr
 * 
 * @param <TIPOEXCECAO> Subtipo de {@link Throwable}.
 */
public interface ITratadorExcecao<TIPOEXCECAO extends Throwable> {

    /**
     * Método responsável por dar tratamento adequado a exceção.
     * 
     * Exemplo de tratamentos típicos: enviar email, registrar erro em BD, registrar em LOG, formatar mensagem para exibir ao
     * usuário, etc.
     * 
     * @param excecao
     */
    public void tratarExcecao(TIPOEXCECAO excecao);

}
