package afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.service;

import java.util.Collection;

/**
 * Interface de serviço exposto via EJB3.1.
 * 
 * TODO: Mover esta classe para src/test/java assim que for possível usar o jboss as embedded!
 * 
 * @author alyssonfr
 * 
 */
public interface IServicoUmObjetoEmMemoriaBean {

    public Collection<UmObjetoEmMemoria> listar();

    public UmObjetoEmMemoria recuperarPorId(Long id);

}
