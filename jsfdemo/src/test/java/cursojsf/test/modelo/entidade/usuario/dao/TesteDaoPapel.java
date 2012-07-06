/**
 * 
 */
package cursojsf.test.modelo.entidade.usuario.dao;

import org.springframework.beans.factory.annotation.Autowired;

import cursojsf.modelo.entidade.dao.DaoJpaAbstrato;
import cursojsf.modelo.entidade.usuario.Papel;
import cursojsf.modelo.entidade.usuario.dao.DaoPapel;
import cursojsf.test.modelo.entidade.TesteDaoObjetoPersistente;

/**
 * @author alysson
 *
 */
public class TesteDaoPapel extends TesteDaoObjetoPersistente<Papel> {
	private DaoPapel daoPapel;
	
	@Override
	protected DaoJpaAbstrato<Papel> getDao() {
		return daoPapel;
	}

	@Autowired
	public void setDaoPapel(DaoPapel daoPapel) {
		this.daoPapel = daoPapel;
	}

}
