package br.com.afrcode.arquitetura.is.spring.config.rmi.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

import br.com.afrcode.arquitetura.is.modelo.rmi.objetoemmemoria.service.IServicoConsultaUmObjetoEmMemoriaRmi;
import br.com.afrcode.arquitetura.is.spring.config.rmi.AbstractRMIClientProxyBeansConfig;
import br.com.afrcode.arquitetura.is.spring.config.util.ProfilesIS;

/**
 * Configurações para acesso a serviços remotos expostos via RMI.
 * 
 * 
 */
@Configuration
@Profile(ProfilesIS.PROFILE_TU)
public class ServicoConsultaUmObjetoEmMemoriaRmiClientBeansConfig extends
		AbstractRMIClientProxyBeansConfig<IServicoConsultaUmObjetoEmMemoriaRmi> {

	/**
	 * Configurações para acesso ao serviço remoto exposto via RMI.
	 * 
	 * O Spring através de RmiProxyFactoryBean beans expõe serviços (POJOs)
	 * encapsulando todo o acesso ao RMI registry. é necessário: 1) informar a
	 * url de acesso ao serviço; 2) informar e possuir em classpath a interface
	 * referente ao serviço.
	 * 
	 * @return
	 */
	@Bean
	@DependsOn("servicoConsultaUmObjetoEmMemoriaRmiServiceExporter")
	public IServicoConsultaUmObjetoEmMemoriaRmi servicoConsultaUmObjetoEmMemoriaRmiClient() {
		return criarRmiProxy("servicoConsultaUmObjetoEmMemoriaRmiService");
	}

}
