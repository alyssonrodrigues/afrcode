package br.com.afrcode.arquitetura.is.util.ejb;

import java.util.concurrent.Callable;

/**
 * Caller utilit�rio para execu��o de TUs de EJBs segundo documenta��o
 * http://tomee.apache.org/unit-testing-transactions.html.
 */
public interface Caller {

    public <V> V call(Callable<V> callable) throws Exception;

}
