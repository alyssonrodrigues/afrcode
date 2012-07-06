package afrcode.fwarquitetura.jsf.excecao;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;


/**
 * Factory para obtenção do tratador de exceções JSF 2 implementado em {@link JSF2ExceptionHandler}.
 * 
 * @author alyssonfr
 * 
 */
public class JSF2ExceptionHandlerFactory extends ExceptionHandlerFactory {

    private ExceptionHandlerFactory parent;

    public JSF2ExceptionHandlerFactory(ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        ExceptionHandler exceptionHandler = new JSF2ExceptionHandler(parent.getExceptionHandler());
        return exceptionHandler;
    }

}
