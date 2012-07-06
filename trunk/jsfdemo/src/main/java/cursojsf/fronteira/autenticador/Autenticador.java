/**
 * 
 */
package cursojsf.fronteira.autenticador;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import cursojsf.modelo.entidade.usuario.Papel;
import cursojsf.modelo.entidade.usuario.Usuario;
import cursojsf.modelo.entidade.usuario.dao.DaoUsuario;

/**
 * @author alysson
 *
 */
@Name("autenticador")
public class Autenticador {
	@Logger 
	private Log log;
	
	@In
	private Identity identity;
	
	@In
	private Credentials credentials;
	
	@In(value = "#{daoUsuario}", required = true)
	private DaoUsuario daoUsuario;
	
	public boolean autenticar() {
		log.info("Autenticando {0} ...", credentials.getUsername());
		Usuario usuario = daoUsuario.procurar("login", credentials.getUsername());
		if (usuario != null &&
				usuario.getSenha().equals(credentials.getPassword())) {
			for (Papel papel : usuario.getPapeis()) {
				identity.addRole(papel.getCodigo());
			}
			return true;
		}
		// Usuários especiais ...
		else if ("administrador".equals(credentials.getUsername()) &&
				"senhaadmin".equals(credentials.getPassword())) {
			identity.addRole("admin");
			return true;
		} else if ("convidado".equals(credentials.getUsername()) &&
				"senhaconvidado".equals(credentials.getPassword())) {
			identity.addRole("guest");
			return true;
		}
		return false;
	}

}
