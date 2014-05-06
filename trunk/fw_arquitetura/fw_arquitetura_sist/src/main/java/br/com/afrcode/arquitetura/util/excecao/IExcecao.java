package br.com.afrcode.arquitetura.util.excecao;

import br.com.afrcode.arquitetura.util.mensagem.IMensagem;

/**
 * Interface base para implementa��es de exce��es que possuam mensagens associadas.
 * 
 * Ver IMensagem e ITratadorMensagem.
 * Cada mensagem ter� seu tratador que saber� como formatar e proceder quanto a mesma.
 * 
 * 
 * @param <T> Subtipo de IMensagem.
 * 
 */
public interface IExcecao<T extends IMensagem> {

    T getMensagem();

}
