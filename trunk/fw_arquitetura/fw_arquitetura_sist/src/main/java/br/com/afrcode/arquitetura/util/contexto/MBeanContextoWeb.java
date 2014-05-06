package br.com.afrcode.arquitetura.util.contexto;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Profile;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

@ManagedBean
@SessionScoped
@Profile({ Profiles.PROFILE_APLICACAO })
public class MBeanContextoWeb implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(MBeanContextoWeb.class);

	@ManagedProperty(value = "#{contextoAplicacaoWeb}")
	private ContextoAplicacaoWeb contextoAplicacaoWeb;

	@ManagedProperty("#{contextoSeguranca}")
	private ContextoSeguranca contextoSeguranca;

	public MBeanContextoWeb() {
		LOG.debug("Criando MBeanContextoWeb...");
	}

	public void setContextoAplicacaoWeb(
			ContextoAplicacaoWeb contextoAplicacaoWeb) {
		this.contextoAplicacaoWeb = contextoAplicacaoWeb;
	}

	/**
	 * @return the contextoAplicacaoWeb
	 */
	public ContextoAplicacaoWeb getContextoAplicacaoWeb() {
		return contextoAplicacaoWeb;
	}

	public void setContextoSeguranca(ContextoSeguranca contextoSeguranca) {
		this.contextoSeguranca = contextoSeguranca;
	}

	public ContextoSeguranca getContextoSeguranca() {
		return contextoSeguranca;
	}

}
