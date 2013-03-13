package afrcode.fwarquitetura.is.test.spring.config.rmi.service;

import static afrcode.fwarquitetura.is.spring.config.util.ProfilesIS.PROFILE_TESTES;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.remoting.rmi.RmiServiceExporter;

import afrcode.fwarquitetura.is.test.modelo.rmi.objetoemmemoria.service.IServicoUmObjetoEmMemoriaRMI;

/**
 * Configurações Spring para exposição de serviços via RMI.
 * 
 * @author alyssonfr
 * 
 */
@Configuration
@Profile({PROFILE_TESTES})
public class RMIServiceExporterBeansTUConfig {
    /**
     * Instância local do serviço a ser exposto.
     */
    @Autowired
    @Qualifier("umObjetoEmMemoriaRmiService")
    private IServicoUmObjetoEmMemoriaRMI umObjetoEmMemoriaRmiService;

    /**
     * Configurações para expor serviço via RMI.
     * 
     * If you have ever created an RMI service WITHOUT Spring, you know that it involves
     * the following steps:
     * 1) Write the service implementation class with methods that throw
     * java.rmi.RemoteException.
     * 2) Create the service interface to extend java.rmi.Remote.
     * 3) Run the RMI compiler (rmic) to produce client stub and server skeleton
     * classes.
     * 4) Start an RMI registry to host the services.
     * 5) Register the service in the RMI registry.
     * 
     * O {@link RmiServiceExporter}, Spring, é responsável por:
     * 1) iniciar um RMI registry, se ainda não iniciado;
     * 2) registrar serviços via RMI registry na máquina local, porta 1099;
     * 3) expor o beans geridos (POJOs) encapsulando-os em um adaptador (RMI Service Adapter).
     * 
     * Este adaptador por sua fez:
     * 1) é registrado, pelo Spring, no RMI registry;
     * 2) responderá por chamadas aos serviços expostos pelos beans geridos.
     * 
     * @return
     */
    @Bean
    public RmiServiceExporter umObjetoEmMemoriaRmiServiceExporter() {
        RmiServiceExporter umObjetoPersistenteRmiServiceExporter = new RmiServiceExporter();
        umObjetoPersistenteRmiServiceExporter.setServiceName("umObjetoEmMemoriaRmiService");
        umObjetoPersistenteRmiServiceExporter.setService(umObjetoEmMemoriaRmiService);
        umObjetoPersistenteRmiServiceExporter.setServiceInterface(IServicoUmObjetoEmMemoriaRMI.class);
        return umObjetoPersistenteRmiServiceExporter;
    }

}
