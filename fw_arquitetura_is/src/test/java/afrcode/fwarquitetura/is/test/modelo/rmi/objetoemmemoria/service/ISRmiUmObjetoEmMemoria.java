package afrcode.fwarquitetura.is.test.modelo.rmi.objetoemmemoria.service;

import java.util.Collection;

import afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.service.UmObjetoEmMemoria;

/**
 * Interface de serviço exposto remotamente via RMI para fins de testes sobre o Spring remoting.
 * 
 * @author alyssonfr
 * 
 */
public interface ISRmiUmObjetoEmMemoria {

    public Collection<UmObjetoEmMemoria> listar();

    public UmObjetoEmMemoria recuperarPorId(Long id);

}
