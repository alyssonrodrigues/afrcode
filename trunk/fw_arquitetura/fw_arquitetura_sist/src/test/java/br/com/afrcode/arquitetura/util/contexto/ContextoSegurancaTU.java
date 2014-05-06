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
import br.com.afrcode.arquitetura.spring.config.security.AuthorityGranterParaTU;
import br.com.afrcode.arquitetura.spring.config.security.LoginModuleParaTU;
import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Classe utilit�ria para obten��o de informa��es de autentica��o e autoriza��o
 * para o PROFILE_TU.
 * 
 * A autentica��o e a autoriza��o para TUs baseia-se via configura��es presentes
 * em SpringSecurityTUConfig.
 * 
 * 
 */
@Componente
@Profile({ Profiles.PROFILE_TU })
public class ContextoSegurancaTU extends ContextoSegurancaAbstrato {

	/**
	 * Ver SpringSecurityTUConfig para maiores informa��es acerca do
	 * DefaultJaasAuthenticationProvider em uso por TUs.
	 */
	@Autowired
	private DefaultJaasAuthenticationProvider defaultJaasAuthenticationProvider;

	/**
	 * M�todo respons�vel por criar autentica��o para o PROFILE_TU.
	 */
	@PostConstruct
	void iniciar() {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				LoginModuleParaTU.USER, LoginModuleParaTU.PASSWD);
		Authentication auth = defaultJaasAuthenticationProvider
				.authenticate(token);
		Validate.isTrue(auth.isAuthenticated(),
				"O usu�rio deveria ter sido autenticado com sucesso para TUs!");
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
				.createAuthorityList(AuthorityGranterParaTU.ROLE_USER);

		User usuario = new User(username, password, authorities);
		return usuario;
	}

}
