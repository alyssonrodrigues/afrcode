package br.com.afrcode.arquitetura.util.contexto;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

import br.com.afrcode.arquitetura.spring.anotacoes.Componente;
import br.com.afrcode.arquitetura.spring.config.util.Profiles;

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

	/**
	 * Faz-se uso do ServletContext para obtenção de informações apenas. NÃO é
	 * permitido expor o ServletContext para as camadas inferiores!!!
	 */
	private ServletContext servletContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * Método de recuperação do nome do contexto web corrente.
	 * 
	 * @return
	 */
	public String getNomeContextoWeb() {
		return applicationContext.getId();
	}

	/**
	 * Método de recuperação do caminho absoluto, no servidor de aplicação, do
	 * contexto web corrente.
	 * 
	 * @return
	 */
	public String getContextRealPath() {
		return servletContext.getRealPath("/");
	}

	public boolean isAmbienteTU() {
		List<String> profilesAtivos = Arrays.asList(applicationContext
				.getEnvironment().getActiveProfiles());
		return profilesAtivos.contains(Profiles.PROFILE_TU);
	}

}
