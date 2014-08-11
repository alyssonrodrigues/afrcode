package br.com.afrcode.arquitetura.modelo.entidade.objetopersistente.povoador;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.afrcode.arquitetura.modelo.entidade.objetopersistente.UmObjetoPersistente;
import br.com.afrcode.arquitetura.modelo.entidade.objetopersistente.dao.DaoUmObjetoPersistente;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;

public class PovoadorUmObjetoPersistenteTest extends AbstractCasoTesteEmMemoria {
    @Autowired
    private PovoadorUmObjetoPersistente povoadorUmObjetoPersistente;

    @Autowired
    private DaoUmObjetoPersistente daoUmObjetoPersistente;

    @Test
    public void testarPovoadorUmObjetoPersistente() {
        povoadorUmObjetoPersistente.povoar();
        Collection<UmObjetoPersistente> objs = daoUmObjetoPersistente.recuperarTodos();
        Assert.assertEquals("Deveriam ter sido criados " + PovoadorUmObjetoPersistente.NUM_MAX_OBJS_CRIADOS
                + " objetos persistentes!", PovoadorUmObjetoPersistente.NUM_MAX_OBJS_CRIADOS, objs.size());
    }

}
