package afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.service;

import java.util.Collection;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import afrcode.fwarquitetura.is.test.util.junit.logging.EJB3LoggingInterceptor;

/**
 * Stateless session bean para exposição dos serviços definidos em {@link ISEjb3UmObjetoEmMemoria} via EJB3.1.
 * 
 * TODO: Mover esta classe para src/test/java assim que for possível usar o jboss as embedded!
 * 
 * @author alyssonfr
 * 
 */
@Stateless
@Remote(ISEjb3UmObjetoEmMemoria.class)
// Cool stuff (Integração EJB3/Spring): basta incluir um interceptor para usar DI de beans Springs (@Autowired).
@Interceptors({SpringBeanAutowiringInterceptor.class, EJB3LoggingInterceptor.class})
public class UmObjetoEmMemoriaBean implements ISEjb3UmObjetoEmMemoria {
    private static final Logger LOG = Logger.getLogger(UmObjetoEmMemoriaBean.class);

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
