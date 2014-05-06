package br.com.afrcode.arquitetura.util.excecao;

import org.apache.commons.lang.Validate;

import br.com.afrcode.arquitetura.util.mensagem.MensagemNegocioAbstrata;

/**
 * Tratador de exece��es para o tipo espec�fico de exce��o ExcecaoNegocio.
 * 
 * 
 */
public class TratadorExcecaoNegocio implements ITratadorExcecao<ExcecaoNegocio> {

    @Override
    public void tratarExcecao(ExcecaoNegocio excecao) {
        Validate.notNull(excecao, "Uma inst�ncia de ExcecaoNegocio deve ser informada!");
        MensagemNegocioAbstrata mensagem = excecao.getMensagem();
        mensagem.tratarMensagem();
    }

}
