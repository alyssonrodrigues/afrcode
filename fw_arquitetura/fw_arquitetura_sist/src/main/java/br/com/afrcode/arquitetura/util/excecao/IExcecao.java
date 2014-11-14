package br.com.afrcode.arquitetura.util.excecao;

import br.com.afrcode.arquitetura.util.mensagem.IMensagem;

/**
 * Interface base para implementações de exceções que possuam mensagens
 * associadas.
 * 
 * Ver IMensagem e ITratadorMensagem. Cada mensagem terá seu tratador que saberá
 * como formatar e proceder quanto a mesma.
 * 
 * 
 * @param <T>
 *            Subtipo de IMensagem.
 * 
 */
public interface IExcecao<T extends IMensagem> {

	T getMensagem();

}
