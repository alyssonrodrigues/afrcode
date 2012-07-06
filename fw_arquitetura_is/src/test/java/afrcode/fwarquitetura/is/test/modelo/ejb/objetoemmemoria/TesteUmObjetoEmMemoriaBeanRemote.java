package afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria;

import static afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.service.DaoUmObjetoEmMemoria.NUM_OBJS_CRIADOS;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.ejb.EJB;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.service.ISEjb3UmObjetoEmMemoria;
import afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.service.UmObjetoEmMemoria;
import afrcode.fwarquitetura.tu.util.junit.CasoTesteSemTransacao;

public class TesteUmObjetoEmMemoriaBeanRemote extends CasoTesteSemTransacao {
    private static final Logger LOG = Logger.getLogger(TesteUmObjetoEmMemoriaBeanRemote.class);

    @Autowired
    private StopWatch stopWatch;

    @EJB
    private ISEjb3UmObjetoEmMemoria umObjetoEmMemoriaBeanRemote;

    @Test
    public void testarListar() {
        stopWatch.start();

        Collection<UmObjetoEmMemoria> objs = umObjetoEmMemoriaBeanRemote.listar();

        stopWatch.stop();

        LOG.info("testarListar: " + stopWatch.toString());
        stopWatch.reset();

        assertNotNull("A coleção de objetos não deveria ser nula!", objs);
        assertTrue("A coleção de objetos não deveria estar vazia!", !objs.isEmpty());
        assertTrue("A coleção de objetos deveria ter " + NUM_OBJS_CRIADOS + " objetos!", objs.size() == NUM_OBJS_CRIADOS);
    }

    @Test
    public void testarRecuperarPorId() {
        Long id = 1L;

        stopWatch.start();

        UmObjetoEmMemoria umObj = umObjetoEmMemoriaBeanRemote.recuperarPorId(id);

        stopWatch.stop();

        LOG.info("testarRecuperarPorId: " + stopWatch.toString());
        stopWatch.reset();

        assertNotNull("O objeto não deveria ser nulo!", umObj);
        assertTrue("O id do objeto retornado é diferente do esperado!", id.equals(umObj.getId()));
    }
}
