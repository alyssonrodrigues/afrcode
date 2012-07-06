package afrcode.fwarquitetura.test.modelo.entidade.objetopersistente.povoador;

import static afrcode.fwarquitetura.test.modelo.entidade.objetopersistente.povoador.PovoadorUmObjetoPersistente.NUM_MAX_OBJS_CRIADOS;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import afrcode.fwarquitetura.test.modelo.entidade.objetopersistente.UmObjetoPersistente;
import afrcode.fwarquitetura.test.modelo.entidade.objetopersistente.dao.DaoUmObjetoPersistente;
import afrcode.fwarquitetura.tu.util.junit.CasoTesteEmMemoria;

public class TestePovoadorUmObjetoPersistente extends CasoTesteEmMemoria {
    @Autowired
    private PovoadorUmObjetoPersistente povoadorUmObjetoPersistente;

    @Autowired
    private DaoUmObjetoPersistente daoUmObjetoPersistente;

    @Test
    public void testarPovoadorUmObjetoPersistente() {
        povoadorUmObjetoPersistente.povoar();
        Collection<UmObjetoPersistente> objs = daoUmObjetoPersistente.recuperarTodos();
        assertEquals("Deveriam ter sido criados " + NUM_MAX_OBJS_CRIADOS + " objetos persistentes!", NUM_MAX_OBJS_CRIADOS,
                objs.size());
    }

}
