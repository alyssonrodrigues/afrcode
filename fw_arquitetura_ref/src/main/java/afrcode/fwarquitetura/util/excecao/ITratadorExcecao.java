package afrcode.fwarquitetura.util.excecao;

/**
 * 
 * Interface base de implementa��o de tratadores de exce��o.
 * 
 * @author alyssonfr
 * 
 * @param <TIPOEXCECAO> Subtipo de {@link Throwable}.
 */
public interface ITratadorExcecao<TIPOEXCECAO extends Throwable> {

    /**
     * M�todo respons�vel por dar tratamento adequado a exce��o.
     * 
     * Exemplo de tratamentos t�picos: enviar email, registrar erro em BD, registrar em LOG, formatar mensagem para exibir ao
     * usu�rio, etc.
     * 
     * @param excecao
     */
    public void tratarExcecao(TIPOEXCECAO excecao);

}
