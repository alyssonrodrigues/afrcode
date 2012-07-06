package afrcode.fwarquitetura.util.excecao;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import afrcode.fwarquitetura.util.mensagem.erro.MensagemErroNegocio;

/**
 * Tratador de execeções para o tipo específico de exceção {@link ConstraintViolationException}.
 * 
 * @author alyssonfr
 * 
 */
public class TratadorConstraintViolationException implements ITratadorExcecao<ConstraintViolationException> {

    @Override
    public void tratarExcecao(ConstraintViolationException cve) {
        // Exceções de violações de constraints do beans validations serão convertidas para mensagens de erro de negócio e
        // portanto serão tratadas pelo respectivo tratador de mensagens de erro de negócio.
        for (ConstraintViolation<?> cv : cve.getConstraintViolations()) {
            String propPath = cv.getPropertyPath().toString();
            MensagemErroNegocio mensagemErroNegocio = new MensagemErroNegocio();
            mensagemErroNegocio.setCodMensagem(propPath);
            mensagemErroNegocio.setMensagem(cv.getMessage());
            mensagemErroNegocio.tratarMensagem();
        }
    }

}
