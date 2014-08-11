package br.com.afrcode.arquitetura.is.modelo.rmi.objetoemmemoria.client;

import java.util.Collection;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.com.afrcode.arquitetura.is.modelo.ejb.objetoemmemoria.service.DaoUmObjetoEmMemoria;
import br.com.afrcode.arquitetura.is.modelo.ejb.objetoemmemoria.service.UmObjetoEmMemoria;
import br.com.afrcode.arquitetura.is.modelo.rmi.objetoemmemoria.service.IServicoConsultaUmObjetoEmMemoriaRmi;
import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteSemJpaEJta;

/**
 * Este teste visa exercitar e validar a infra disponível para exposição de
 * serviços RMI via Spring remoting.
 * 
 * 
 */
public class ServicoConsultaUmObjetoEmMemoriaRmiTest extends AbstractCasoTesteSemJpaEJta {
    private static final Logger LOG = Logger.getLogger(ServicoConsultaUmObjetoEmMemoriaRmiTest.class);

    /**
     * Acesso a instância remota de serviço exposto via RMI.
     */
    @Autowired
    @Qualifier("servicoConsultaUmObjetoEmMemoriaRmiClient")
    private IServicoConsultaUmObjetoEmMemoriaRmi servicoConsultaUmObjetoEmMemoriaRmiClient;

    @Autowired
    private StopWatch stopWatch;

    @Test
    public void testarListar() {
        validarDI();

        stopWatch.start();

        Collection<UmObjetoEmMemoria> objs = servicoConsultaUmObjetoEmMemoriaRmiClient.listar();

        stopWatch.stop();

        LOG.info("testarListar: " + stopWatch.toString());
        stopWatch.reset();

        Assert.assertNotNull("A coleção de objetos não deveria ser nula!", objs);
        Assert.assertTrue("A coleção de objetos não deveria estar vazia!", !objs.isEmpty());
        Assert.assertTrue("A coleção de objetos deveria ter " + DaoUmObjetoEmMemoria.NUM_OBJS_CRIADOS + " objetos!",
                objs.size() == DaoUmObjetoEmMemoria.NUM_OBJS_CRIADOS);
    }

    @Test
    public void testarRecuperarPorId() {
        validarDI();
        Long id = 1L;

        stopWatch.start();

        UmObjetoEmMemoria umObj = servicoConsultaUmObjetoEmMemoriaRmiClient.recuperarPorId(id);

        stopWatch.stop();

        LOG.info("testarRecuperarPorId: " + stopWatch.toString());
        stopWatch.reset();

        Assert.assertNotNull("O objeto não deveria ser nulo!", umObj);
        Assert.assertEquals("O id do objeto retornado é diferente do esperado!", id, umObj.getId());
    }

    private void validarDI() {
        Assert.assertNotNull("Deveria ter sido obtido um proxy para acesso ao serviço exposto via RMI!",
                servicoConsultaUmObjetoEmMemoriaRmiClient);
    }

}
