package br.com.afrcode.arquitetura.is.modelo.ejb.objetoemmemoria.service;

import java.util.Collection;

/**
 * Interface de servi�o exposto via EJB3.1.
 * 
 * 
 */
public interface IServicoConsultaUmObjetoEmMemoria {

    public Collection<UmObjetoEmMemoria> listar();

    public UmObjetoEmMemoria recuperarPorId(Long id);

}
