package br.com.afrcode.arquitetura.is.spring.config.rmi.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

import br.com.afrcode.arquitetura.is.modelo.rmi.objetoemmemoria.service.IServicoConsultaUmObjetoEmMemoriaRmi;
import br.com.afrcode.arquitetura.is.spring.config.rmi.AbstractRMIClientProxyBeansConfig;
import br.com.afrcode.arquitetura.is.spring.config.util.ProfilesIS;

/**
 * Configura��es para acesso a servi�os remotos expostos via RMI.
 * 
 * 
 */
@Configuration
@Profile(ProfilesIS.PROFILE_TU)
public class ServicoConsultaUmObjetoEmMemoriaRmiClientBeansConfig extends
        AbstractRMIClientProxyBeansConfig<IServicoConsultaUmObjetoEmMemoriaRmi> {

    /**
     * Configura��es para acesso ao servi�o remoto exposto via RMI.
     * 
     * O Spring atrav�s de RmiProxyFactoryBean beans exp�e servi�os (POJOs)
     * encapsulando todo o acesso ao RMI registry. � necess�rio: 1) informar a
     * url de acesso ao servi�o; 2) informar e possuir em classpath a interface
     * referente ao servi�o.
     * 
     * @return
     */
    @Bean
    @DependsOn("servicoConsultaUmObjetoEmMemoriaRmiServiceExporter")
    public IServicoConsultaUmObjetoEmMemoriaRmi servicoConsultaUmObjetoEmMemoriaRmiClient() {
        return criarRmiProxy("servicoConsultaUmObjetoEmMemoriaRmiService");
    }

}
