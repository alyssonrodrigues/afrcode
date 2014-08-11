package br.com.afrcode.arquitetura.is.util.ejb;

import java.util.concurrent.Callable;

/**
 * Caller utilitário para execução de TUs de EJBs segundo documentação
 * http://tomee.apache.org/unit-testing-transactions.html.
 */
public interface Caller {

    public <V> V call(Callable<V> callable) throws Exception;

}
