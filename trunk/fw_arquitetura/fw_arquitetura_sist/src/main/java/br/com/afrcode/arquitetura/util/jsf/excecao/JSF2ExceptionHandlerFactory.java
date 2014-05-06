package br.com.afrcode.arquitetura.util.jsf.excecao;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Factory para obtenção do tratador de exceções JSF 2 implementado em JSF2ExceptionHandler.
 * 
 * 
 */
public class JSF2ExceptionHandlerFactory extends ExceptionHandlerFactory {

    private ExceptionHandlerFactory parent;

    public JSF2ExceptionHandlerFactory(ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new JSF2ExceptionHandler(parent.getExceptionHandler());
    }

}
