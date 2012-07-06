package afrcode.fwarquitetura.is.test.modelo.rmi.objetoemmemoria.service;

import java.util.Collection;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.service.DaoUmObjetoEmMemoria;
import afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.service.UmObjetoEmMemoria;

/**
 * Implementação de serviço exposto ({@link ISRmiUmObjetoEmMemoria}) via RMI para fins de testes sobre o Spring remoting.
 * 
 * @author alyssonfr
 * 
 */
@Component
public class UmObjetoEmMemoriaRmiService implements ISRmiUmObjetoEmMemoria {
    private static final Logger LOG = Logger.getLogger(UmObjetoEmMemoriaRmiService.class);

    @Autowired
    private DaoUmObjetoEmMemoria daoUmObjetoPersistente;

    @Autowired
    private StopWatch stopWatch;

    @Override
    public Collection<UmObjetoEmMemoria> listar() {
        stopWatch.start();

        Collection<UmObjetoEmMemoria> objs = daoUmObjetoPersistente.recuperarTodos();

        stopWatch.stop();
        LOG.info("listar: " + stopWatch.toString());
        stopWatch.reset();

        return objs;
    }

    @Override
    public UmObjetoEmMemoria recuperarPorId(Long id) {
        stopWatch.start();

        UmObjetoEmMemoria obj = daoUmObjetoPersistente.procurarPorId(id);

        stopWatch.stop();
        LOG.info("recuperarPorId: " + stopWatch.toString());
        stopWatch.reset();

        return obj;
    }

}
