package br.com.afrcode.arquitetura.is.spring.config.rmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.remoting.rmi.RmiServiceExporter;

import br.com.afrcode.arquitetura.is.modelo.rmi.objetoemmemoria.service.IServicoConsultaUmObjetoEmMemoriaRmi;
import br.com.afrcode.arquitetura.is.spring.config.rmi.AbstractRMIServiceExporterBeansConfig;
import br.com.afrcode.arquitetura.is.spring.config.util.ProfilesIS;

/**
 * Configura��es Spring para exposi��o de servi�os via RMI.
 * 
 * 
 */
@Configuration
@Profile(ProfilesIS.PROFILE_TU)
public class ServicoConsultaUmObjetoEmMemoriaRmiServiceExporterBeansConfig
		extends
		AbstractRMIServiceExporterBeansConfig<IServicoConsultaUmObjetoEmMemoriaRmi> {
	/**
	 * Inst�ncia local do servi�o a ser exposto.
	 */
	@Autowired
	@Qualifier("servicoConsultaUmObjetoEmMemoriaRmi")
	private IServicoConsultaUmObjetoEmMemoriaRmi servicoConsultaUmObjetoEmMemoriaRmi;

	/**
	 * Configura��es para expor servi�o via RMI.
	 * 
	 * If you have ever created an RMI service WITHOUT Spring, you know that it
	 * involves the following steps: 1) Write the service implementation class
	 * with methods that throw java.rmi.RemoteException. 2) Create the service
	 * interface to extend java.rmi.Remote. 3) Run the RMI compiler (rmic) to
	 * produce client stub and server skeleton classes. 4) Start an RMI registry
	 * to host the services. 5) Register the service in the RMI registry.
	 * 
	 * O RmiServiceExporter, Spring, � respons�vel por: 1) iniciar um RMI
	 * registry, se ainda n�o iniciado; 2) registrar servi�os via RMI registry
	 * na m�quina local, porta 1099; 3) expor o beans geridos (POJOs)
	 * encapsulando-os em um adaptador (RMI Service Adapter).
	 * 
	 * Este adaptador por sua fez: 1) � registrado, pelo Spring, no RMI
	 * registry; 2) responder� por chamadas aos servi�os expostos pelos beans
	 * geridos.
	 * 
	 * @return
	 */
	@Bean
	public RmiServiceExporter servicoConsultaUmObjetoEmMemoriaRmiServiceExporter() {
		return createRmiServiceExporter(
				"servicoConsultaUmObjetoEmMemoriaRmiService",
				servicoConsultaUmObjetoEmMemoriaRmi);
	}

}
