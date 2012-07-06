package afrcode.fwarquitetura.util.excecao;

import afrcode.fwarquitetura.util.mensagem.IMensagem;
import afrcode.fwarquitetura.util.mensagem.ITratadorMensagem;

/**
 * Interface base para implementações de exceções que possuam mensagens associadas.
 * 
 * Ver {@link IMensagem} e {@link ITratadorMensagem}.
 * Cada mensagem terá seu tratador que saberá como formatar e proceder quanto a mesma.
 * 
 * @author alyssonfr
 * 
 * @param <TIPOMENSAGEM> Subtipo de {@link IMensagem}.
 * 
 */
public interface IExcecao<TIPOMENSAGEM extends IMensagem> {

    public TIPOMENSAGEM getMensagem();

}
