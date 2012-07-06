package afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.service;

import static afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.service.UmObjetoEmMemoria.NUM_OBJS_ASSOCIADOS;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Implementação de DAO em memória para fins de testes de exposição de serviços em memória.
 * 
 * TODO: Mover esta classe para src/test/java assim que for possível usar o jboss as embedded!
 * 
 * @author alyssonfr
 * 
 */
@Repository
public class DaoUmObjetoEmMemoria {
    private static final Logger LOG = Logger.getLogger(DaoUmObjetoEmMemoria.class);

    public static int NUM_OBJS_CRIADOS = 1000;

    public UmObjetoEmMemoria procurarPorId(Long id) {
        UmObjetoEmMemoria umObjetoPersistente = instaciarObjetoPersistivelParaTestes();
        umObjetoPersistente.setId(id);

        return umObjetoPersistente;
    }

    public void salvar(UmObjetoEmMemoria obj) {
        // Nada faz.
    }

    public void excluir(UmObjetoEmMemoria obj) {
        // Nada faz.
    }

    public void sincronizar() {
        // Nada faz.
    }

    public Collection<UmObjetoEmMemoria> recuperarTodos() {
        Collection<UmObjetoEmMemoria> objs = new ArrayList<UmObjetoEmMemoria>();
        for (int i = 1; i <= NUM_OBJS_CRIADOS; i++) {
            UmObjetoEmMemoria umObjetoPersistente = new UmObjetoEmMemoria();
            umObjetoPersistente.preencherComValoresPersistiveis();
            umObjetoPersistente.setId(new Long(i));
            objs.add(umObjetoPersistente);
        }

        int numObjs = (1 + NUM_OBJS_ASSOCIADOS + 1) * NUM_OBJS_CRIADOS;
        LOG.info("recuperarTodos(" + numObjs + ")");

        return objs;
    }

    public UmObjetoEmMemoria instaciarObjetoPersistivelParaTestes() {
        UmObjetoEmMemoria umObjetoPersistente = new UmObjetoEmMemoria();
        umObjetoPersistente.preencherComValoresPersistiveis();
        return umObjetoPersistente;
    }

}
