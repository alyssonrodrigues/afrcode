package br.com.afrcode.arquitetura.spring.config.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import br.com.afrcode.arquitetura.util.excecao.ExcecaoNaoPrevista;

/**
 * Classe utilitária para tratamento de falhas de login.
 * 
 * 
 */
public class AuthenticationFailureHandlerImpl extends
		SimpleUrlAuthenticationFailureHandler {
	private static final Logger LOG = Logger
			.getLogger(AuthenticationFailureHandlerImpl.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		registrarMensagemErroLogin(request, response, exception);
		super.onAuthenticationFailure(request, response, exception);
	}

	private void registrarMensagemErroLogin(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception) {

		LOG.error("Erro ao efetuar login: ", exception);

		// TODO: Obter msg de um resource bundle ou criar arquivos de mensagens
		// para o Spring!!!
		String msgSummary = exception.getMessage();
		String msg = msgSummary;

		try {
			setDefaultFailureUrl("/web/login.xhtml?erroLogin="
					+ URLEncoder.encode(msg, "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			throw new ExcecaoNaoPrevista(e);
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("Login inv�lido: " + msg);
		}
	}
}
