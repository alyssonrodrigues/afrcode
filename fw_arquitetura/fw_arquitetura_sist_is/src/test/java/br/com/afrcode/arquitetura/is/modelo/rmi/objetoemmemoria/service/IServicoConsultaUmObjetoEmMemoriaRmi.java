package br.com.afrcode.arquitetura.is.modelo.rmi.objetoemmemoria.service;

import java.util.Collection;

import br.com.afrcode.arquitetura.is.modelo.ejb.objetoemmemoria.service.UmObjetoEmMemoria;

/**
 * Interface de serviço exposto remotamente via RMI para fins de testes sobre o
 * Spring remoting.
 * 
 * 
 */
public interface IServicoConsultaUmObjetoEmMemoriaRmi {

	public Collection<UmObjetoEmMemoria> listar();

	public UmObjetoEmMemoria recuperarPorId(Long id);

}
