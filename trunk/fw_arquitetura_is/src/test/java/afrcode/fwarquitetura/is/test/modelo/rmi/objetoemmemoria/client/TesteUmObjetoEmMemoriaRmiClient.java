package afrcode.fwarquitetura.is.test.modelo.rmi.objetoemmemoria.client;

import static afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.service.DaoUmObjetoEmMemoria.NUM_OBJS_CRIADOS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.service.UmObjetoEmMemoria;
import afrcode.fwarquitetura.is.test.modelo.rmi.objetoemmemoria.service.IServicoUmObjetoEmMemoriaRMI;
import afrcode.fwarquitetura.tu.util.junit.CasoTesteSemTransacao;

/**
 * Este teste visa exercitar e validar a infra disponível para exposição de serviços RMI via Spring remoting.
 * 
 * @author alyssonfr
 * 
 */
public class TesteUmObjetoEmMemoriaRmiClient extends CasoTesteSemTransacao {
    private static final Logger LOG = Logger.getLogger(TesteUmObjetoEmMemoriaRmiClient.class);

    /**
     * Acesso a instância remota de serviço exposto via RMI.
     */
    @Autowired
    @Qualifier("umObjetoEmMemoriaRmiClient")
    private IServicoUmObjetoEmMemoriaRMI umObjetoEmMemoriaRmiClient;

    @Autowired
    private StopWatch stopWatch;

    @Test
    public void testarListar() {
        validarDI();

        stopWatch.start();

        Collection<UmObjetoEmMemoria> objs = umObjetoEmMemoriaRmiClient.listar();

        stopWatch.stop();

        LOG.info("testarListar: " + stopWatch.toString());
        stopWatch.reset();

        assertNotNull("A coleção de objetos não deveria ser nula!", objs);
        assertTrue("A coleção de objetos não deveria estar vazia!", !objs.isEmpty());
        assertTrue("A coleção de objetos deveria ter " + NUM_OBJS_CRIADOS + " objetos!", objs.size() == NUM_OBJS_CRIADOS);
    }

    @Test
    public void testarRecuperarPorId() {
        validarDI();
        Long id = 1L;

        stopWatch.start();

        UmObjetoEmMemoria umObj = umObjetoEmMemoriaRmiClient.recuperarPorId(id);

        stopWatch.stop();

        LOG.info("testarRecuperarPorId: " + stopWatch.toString());
        stopWatch.reset();

        assertNotNull("O objeto não deveria ser nulo!", umObj);
        assertEquals("O id do objeto retornado é diferente do esperado!", id, umObj.getId());
    }

    private void validarDI() {
        assertNotNull("Deveria ter sido obtido um proxy para acesso ao serviço exposto via RMI!", umObjetoEmMemoriaRmiClient);
    }

}
