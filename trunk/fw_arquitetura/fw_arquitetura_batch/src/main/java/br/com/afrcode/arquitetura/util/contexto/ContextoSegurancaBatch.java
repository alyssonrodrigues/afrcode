package br.com.afrcode.arquitetura.util.contexto;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.DefaultJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.JaasAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import br.com.afrcode.arquitetura.spring.anotacoes.Componente;
import br.com.afrcode.arquitetura.spring.config.security.AuthorityGranterParaBatch;
import br.com.afrcode.arquitetura.spring.config.security.LoginModuleParaBatch;
import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Classe utilitária para obtenção de informações de autenticação e autorização
 * para o Batch.
 * 
 * A autenticação e a autorização para batchES baseia-se em configurações
 * presentes em SpringSecurityBatchConfig.
 * 
 * 
 */
@Componente
@Profile({ Profiles.PROFILE_APLICACAO_BATCH })
public class ContextoSegurancaBatch extends ContextoSegurancaAbstrato {

	/**
	 * Ver SpringSecurityBatchConfig para maiores informações acerca do
	 * DefaultJaasAuthenticationProvider em uso por batchES.
	 */
	@Autowired
	private DefaultJaasAuthenticationProvider defaultJaasAuthenticationProvider;

	/**
	 * Método responsável por criar autenticação para o Batch.
	 */
	@PostConstruct
	void iniciar() {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				LoginModuleParaBatch.USER, LoginModuleParaBatch.PASSWD);
		Authentication auth = defaultJaasAuthenticationProvider
				.authenticate(token);
		Validate.isTrue(auth.isAuthenticated(),
				"O usuário deveria ter sido autenticado com sucesso para batchES!");
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Override
	public User getUsuarioAutenticado() {
		JaasAuthenticationToken jaasAuthenticationToken = JaasAuthenticationToken.class
				.cast(getAuthentication());
		String username = jaasAuthenticationToken.getName();
		String password = String.class.cast(jaasAuthenticationToken
				.getCredentials());
		List<GrantedAuthority> authorities = AuthorityUtils
				.createAuthorityList(AuthorityGranterParaBatch.ROLE_USER);

		User usuario = new User(username, password, authorities);
		return usuario;
	}

}
