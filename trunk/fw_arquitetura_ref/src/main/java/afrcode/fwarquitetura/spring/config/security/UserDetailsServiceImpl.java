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
 * @author eldontc
 * 
 */

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SrvUsuarioMock srvUsuarioMock;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        User user = null;
        // Substituir a linha abaixo por uma chamada ao serviço que recupera uma instância de tcu.util.seguranca.Usuario
        UsuarioMock usuarioTCU = new UsuarioMock("admin");
        String login = usuarioTCU.getLogin();
        String password = usuarioTCU.getSenha();
        boolean enabled = usuarioTCU.getSeUsuarioAtivo();
        boolean accountNonExpired = usuarioTCU.getSeContaExpirada();
        boolean credentialsNonExpired = usuarioTCU.getSeSenhaExpirada();
        boolean accountNonLocked = usuarioTCU.getSeContaBloqueada();
        // Substituir a linha abaixo por uma chama ao serviço que recupera as funções computacionais liberdas para o usuario
        List<String> funcoesLiberadas = srvUsuarioMock.recuperaFuncoesComputacionaisLiberadasParaUsuario(login);
        funcoesLiberadas.toArray(new String[funcoesLiberadas.size()]);

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities = AuthorityUtils.createAuthorityList(funcoesLiberadas.toArray(new String[funcoesLiberadas.size()]));

        user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        return user;

    }

}