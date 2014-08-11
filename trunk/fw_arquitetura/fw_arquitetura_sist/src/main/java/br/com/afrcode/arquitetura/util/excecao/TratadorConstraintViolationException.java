package br.com.afrcode.arquitetura.util.excecao;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import br.com.afrcode.arquitetura.util.mensagem.erro.MensagemErroNegocio;

/**
 * Tratador de execeções para o tipo específico de exceção
 * ConstraintViolationException.
 * 
 * 
 */
public class TratadorConstraintViolationException implements ITratadorExcecao<ConstraintViolationException> {

    @Override
    public void tratarExcecao(ConstraintViolationException cve) {
        // Exceções de violações de constraints do beans validations serão
        // convertidas para mensagens de erro de negócio e
        // portanto serão tratadas pelo respectivo tratador de mensagens de erro
        // de negócio.
        for (ConstraintViolation<?> cv : cve.getConstraintViolations()) {
            String propPath = cv.getPropertyPath().toString();
            MensagemErroNegocio mensagemErroNegocio = new MensagemErroNegocio();
            mensagemErroNegocio.setCodMensagem(propPath);
            mensagemErroNegocio.setMensagem(propPath + cv.getMessage());
            mensagemErroNegocio.tratarMensagem();
        }
    }

}
