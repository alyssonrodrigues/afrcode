package br.com.afrcode.arquitetura.is.modelo.ejb.objetoemmemoria.service;

import java.util.Collection;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

/**
 * Stateless session bean para exposição dos serviços definidos em
 * IServicoConsultaUmObjetoEmMemoria via EJB3.1.
 * 
 * 
 */
@Stateless
@Remote(IServicoConsultaUmObjetoEmMemoria.class)
@Interceptors({ SpringBeanAutowiringInterceptor.class })
public class ServicoConsultaUmObjetoEmMemoriaBean implements IServicoConsultaUmObjetoEmMemoria {

    @Autowired
    private DaoUmObjetoEmMemoria daoUmObjetoEmMemoria;

    @Override
    public Collection<UmObjetoEmMemoria> listar() {
        Collection<UmObjetoEmMemoria> objs = daoUmObjetoEmMemoria.recuperarTodos();
        return objs;
    }

    @Override
    public UmObjetoEmMemoria recuperarPorId(Long id) {
        UmObjetoEmMemoria obj = daoUmObjetoEmMemoria.procurarPorId(id);
        return obj;
    }

}
