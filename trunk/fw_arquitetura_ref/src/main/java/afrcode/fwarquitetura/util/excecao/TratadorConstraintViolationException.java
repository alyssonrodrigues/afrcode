package afrcode.fwarquitetura.util.excecao;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import afrcode.fwarquitetura.util.mensagem.erro.MensagemErroNegocio;

/**
 * Tratador de exece��es para o tipo espec�fico de exce��o {@link ConstraintViolationException}.
 * 
 * @author alyssonfr
 * 
 */
public class TratadorConstraintViolationException implements ITratadorExcecao<ConstraintViolationException> {

    @Override
    public void tratarExcecao(ConstraintViolationException cve) {
        // Exce��es de viola��es de constraints do beans validations ser�o convertidas para mensagens de erro de neg�cio e
        // portanto ser�o tratadas pelo respectivo tratador de mensagens de erro de neg�cio.
        for (ConstraintViolation<?> cv : cve.getConstraintViolations()) {
            String propPath = cv.getPropertyPath().toString();
            MensagemErroNegocio mensagemErroNegocio = new MensagemErroNegocio();
            mensagemErroNegocio.setCodMensagem(propPath);
            mensagemErroNegocio.setMensagem(cv.getMessage());
            mensagemErroNegocio.tratarMensagem();
        }
    }

}
