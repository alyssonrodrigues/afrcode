package afrcode.fwarquitetura.spring.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Extensão responsável por obter dados de usuário para validação diante de credenciais informadas no processo de
 * autenticação.
 * 
 * @author alyssonfr
 * 
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SrvUsuarioMock srvUsuarioMock;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        UsuarioMock usuarioMock = new UsuarioMock("admin");
        String login = usuarioMock.getLogin();
        String password = usuarioMock.getSenha();
        boolean enabled = usuarioMock.getSeUsuarioAtivo();
        boolean accountNonExpired = usuarioMock.getSeContaExpirada();
        boolean credentialsNonExpired = usuarioMock.getSeSenhaExpirada();
        boolean accountNonLocked = usuarioMock.getSeContaBloqueada();

        List<String> funcoesLiberadas = srvUsuarioMock.recuperarRolesPorUsuario(login);
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities = AuthorityUtils.createAuthorityList(funcoesLiberadas.toArray(new String[funcoesLiberadas.size()]));

        User user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        return user;

    }

}