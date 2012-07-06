package afrcode.fwarquitetura.jsf.excecao;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import afrcode.fwarquitetura.util.excecao.ExcecaoNegocio;
import afrcode.fwarquitetura.util.excecao.TratadorConstraintViolationException;
import afrcode.fwarquitetura.util.excecao.TratadorExcecaoNegocio;

/**
 * Classe central de tratamento de exce��es para o JSF 2.
 * 
 * Determinados tipos de exce��es s�o tratados de alguma forma a partir desta classe - gerando mensagens ao usu�rio, logs,
 * redirecionamento p/ p�ginas de erros inesperados, etc. Ver {@link #tratarExcecao(Throwable)}.
 * 
 * ATEN��O: o tratamento da exce��o quanto a transa��es � feito anteriormente a este tratador, ou seja, neste ponto a transa��o
 * corrente j� deixou de existir (via rollback - {@link Transactional}).
 * 
 * @author alyssonfr
 * 
 */
public class JSF2ExceptionHandler extends ExceptionHandlerWrapper {
    private static Logger LOG = Logger.getLogger(JSF2ExceptionHandler.class);

    private ExceptionHandler wrapped;

    public JSF2ExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {
        for (Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents().iterator(); it.hasNext();) {
            ExceptionQueuedEvent event = it.next();
            ExceptionQueuedEventContext ctx = event.getContext();
            Throwable te = obterExpcetionCause(ctx.getException());
            boolean excecaoTratada = false;
            try {
                excecaoTratada = tratarExcecao(te);
            } finally {
                if (excecaoTratada) {
                    // Se a exce��o foi tratada devemos retir�-la da fila de eventos de exce��es do JSF 2.
                    it.remove();
                }
            }
        }
        // Exce��es n�o tratadas por este tratador s�o retornadas ao tratador padr�o do JSF 2.
        super.handle();
    }

    private Throwable obterExpcetionCause(Throwable exception) {
        Throwable te = exception;
        while (te.getCause() != null) {
            te = te.getCause();
        }
        return te;
    }

    private boolean tratarExcecao(Throwable te) {
        boolean excecaoTratada = false;

        if (te instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) te;
            tratarConstraintViolationException(cve);
            excecaoTratada = true;
        } else if (te instanceof ExcecaoNegocio) {
            ExcecaoNegocio exn = (ExcecaoNegocio) te;
            tratarExcecaoNegocio(exn);
            excecaoTratada = true;
        }

        return excecaoTratada;
    }

    private void tratarExcecaoNegocio(ExcecaoNegocio exn) {
        new TratadorExcecaoNegocio().tratarExcecao(exn);
    }

    private void tratarConstraintViolationException(ConstraintViolationException cve) {
        new TratadorConstraintViolationException().tratarExcecao(cve);
    }

}
