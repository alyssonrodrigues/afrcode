package afrcode.fwarquitetura.apresentacao.managedbean;

import afrcode.fwarquitetura.util.mensagem.IMensagem;
import afrcode.fwarquitetura.util.mensagem.ITratadorMensagem;

/**
 * Interface de definição de JSF managed beans.
 * 
 * @author alyssonfr
 * 
 */
public interface IManagedBean {

    /**
     * Método central de adição de mensagens cujo tratamento depende de seu tipo.
     * 
     * Ver {@link IMensagem} e {@link ITratadorMensagem}.
     * 
     * @param mensagem
     */
    public void adicionarMensagem(IMensagem mensagem);

}
