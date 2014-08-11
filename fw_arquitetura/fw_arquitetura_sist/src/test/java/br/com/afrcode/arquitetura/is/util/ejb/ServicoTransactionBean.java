package br.com.afrcode.arquitetura.is.util.ejb;

import java.util.concurrent.Callable;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import br.com.afrcode.arquitetura.is.util.excecao.ExcecaoNaoPrevistaRemota;

/**
 * EJB utilitário para execução de TUs de EJBs segundo documentação
 * http://tomee.apache.org/unit-testing-transactions.html.
 */
@Stateless
@Remote(Caller.class)
public class ServicoTransactionBean extends AbstractServicoEJB implements Caller {
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public <V> V call(final Callable<V> callable) throws Exception {
        return transactionTemplate.execute(new TransactionCallback<V>() {
            @Override
            public V doInTransaction(TransactionStatus status) {
                try {
                    // Chamada ao código cliente de TU.
                    return callable.call();
                } catch (Exception e) {
                    throw new ExcecaoNaoPrevistaRemota(e);
                } finally {
                    // Demarcação de rollback por padrão para TU.
                    status.setRollbackOnly();
                }
            }
        });
    }

}
