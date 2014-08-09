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
 * Bean responsável por armazenar informações do contexto de aplicação web
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
	 * Faz-se uso do ServletContext para obtenção de informações apenas. NÃO é
	 * permitido expor o ServletContext para as camadas inferiores!!!
	 */
	private ServletContext servletContext;

	/**
	 * Método de recuperação do caminho absoluto, no servidor de aplicação, do
	 * contexto web corrente.
	 * 
	 * @return
	 */
	public String getContextRealPath() {
		return servletContext.getRealPath("/");
	}

	/**
	 * Método de recuperação do nome do contexto web corrente.
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
