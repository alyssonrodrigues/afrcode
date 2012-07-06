/**
 * 
 */
package cursojsf.modelo.entidade.usuario.dao;

import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.springframework.stereotype.Repository;

import cursojsf.modelo.entidade.dao.DaoJpaAbstrato;
import cursojsf.modelo.entidade.usuario.Usuario;

/**
 * @author alysson
 *
 */
@BypassInterceptors
@Repository
public class DaoUsuario extends DaoJpaAbstrato<Usuario> {

}
