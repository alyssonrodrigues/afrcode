package br.com.afrcode.arquitetura.util.contexto;

import javax.naming.NamingException;
import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jndi.JndiTemplate;
import org.springframework.web.context.ServletContextAware;

import br.com.afrcode.arquitetura.spring.anotacoes.Componente;
import br.com.afrcode.arquitetura.spring.config.util.Profiles;
import br.com.afrcode.arquitetura.util.excecao.ExcecaoNaoPrevista;

/**
 * Bean respons�vel por armazenar informa��es do contexto de aplica��o web
 * corrente para uso em camadas inferiores.
 * 
 * 
 */
@Componente
public class ContextoAplicacaoWeb implements ApplicationContextAware,
		ServletContextAware {

	private ApplicationContext applicationContext;

	private JndiTemplate jndiTemplate = new JndiTemplate();

	/**
	 * Faz-se uso do ServletContext para obten��o de informa��es apenas. N�O �
	 * permitido expor o ServletContext para as camadas inferiores!!!
	 */
	private ServletContext servletContext;

	/**
	 * M�todo de recupera��o do caminho absoluto, no servidor de aplica��o, do
	 * contexto web corrente.
	 * 
	 * @return
	 */
	public String getContextRealPath() {
		return servletContext.getRealPath("/");
	}

	/**
	 * M�todo de recupera��o do nome do contexto web corrente.
	 * 
	 * @return
	 */
	public String getNomeContextoWeb() {
		if (isAmbienteTU()) {
			return applicationContext.getId();
		} else {
			try {
				return jndiTemplate.lookup("java:app/AppName").toString();
			} catch (NamingException e) {
				throw new ExcecaoNaoPrevista(e);
			}
		}
	}

	public boolean isAmbienteTU() {
		return Profiles.isProfileTUAtivo(applicationContext.getEnvironment());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
