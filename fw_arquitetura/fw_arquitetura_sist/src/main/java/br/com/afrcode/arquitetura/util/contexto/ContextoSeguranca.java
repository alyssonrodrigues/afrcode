package br.com.afrcode.arquitetura.util.contexto;

import java.util.Collection;

import org.apache.commons.lang.Validate;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.afrcode.arquitetura.spring.anotacoes.Componente;
import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Classe utilit�ria para obten��o de informa��es de autentica��o e autoriza��o
 * para o PROFILE_APLICACAO.
 * 
 * 
 */
@Componente
@Profile(Profiles.PROFILE_APLICACAO)
public class ContextoSeguranca extends ContextoSegurancaAbstrato {

	@Override
	public User getUsuarioAutenticado() {
		User usuario = null;
		Authentication auth = getAuthentication();
		Object principal = auth.getPrincipal();
		Validate.notNull(principal, "Deveria existir um usu�rio autenticado!");

		if (User.class.isAssignableFrom(principal.getClass())) {
			usuario = User.class.cast(principal);
		} else if (AbstractAuthenticationToken.class.isAssignableFrom(auth
				.getClass())) {
			usuario = converterAbstractAuthenticationTokenEmUsuario(auth);
		}

		return usuario;
	}

	/**
	 * Convers�o de tipos. Em acessos an�nimos, ou via servlets de testes, o
	 * token de autentica��o n�o � Usuario e sim AbstractAuthenticationToken.
	 * 
	 * @param principal
	 * @return
	 */
	private User converterAbstractAuthenticationTokenEmUsuario(
			Authentication principal) {
		AbstractAuthenticationToken token = AbstractAuthenticationToken.class
				.cast(principal);
		String username = token.getName();
		String password = String.class.cast(token.getCredentials());
		Collection<GrantedAuthority> authorities = token.getAuthorities();
		User usuario = new User(username, password, authorities);
		return usuario;
	}

}
