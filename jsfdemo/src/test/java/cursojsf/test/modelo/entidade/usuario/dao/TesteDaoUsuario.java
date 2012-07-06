/**
 * 
 */
package cursojsf.test.modelo.entidade.usuario.dao;

import org.springframework.beans.factory.annotation.Autowired;

import cursojsf.modelo.entidade.dao.DaoJpaAbstrato;
import cursojsf.modelo.entidade.usuario.Usuario;
import cursojsf.modelo.entidade.usuario.dao.DaoUsuario;
import cursojsf.test.modelo.entidade.TesteDaoObjetoPersistente;

/**
 * @author alysson
 *
 */
public class TesteDaoUsuario extends TesteDaoObjetoPersistente<Usuario> {
	private DaoUsuario daoUsuario;
	
	@Override
	protected DaoJpaAbstrato<Usuario> getDao() {
		return daoUsuario;
	}

	@Autowired
	public void setDaoUsuario(DaoUsuario daoUsuario) {
		this.daoUsuario = daoUsuario;
	}

}
