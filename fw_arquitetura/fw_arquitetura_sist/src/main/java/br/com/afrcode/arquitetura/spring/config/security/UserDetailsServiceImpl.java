package br.com.afrcode.arquitetura.spring.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;
import br.com.afrcode.arquitetura.util.contexto.ContextoAplicacaoWeb;

@Service
@Profile(Profiles.PROFILE_APLICACAO)
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ContextoAplicacaoWeb contextoAplicacaoWeb;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		return new User(username, username,
				AuthorityUtils.createAuthorityList("ROLE_USER"));

	}

}