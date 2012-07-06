/**
 * 
 */
package cursojsf.test.modelo.entidade.chamado.dao;

import org.springframework.beans.factory.annotation.Autowired;

import cursojsf.modelo.entidade.chamado.Responsavel;
import cursojsf.modelo.entidade.chamado.dao.DaoResponsavel;
import cursojsf.modelo.entidade.dao.DaoJpaAbstrato;
import cursojsf.test.modelo.entidade.TesteDaoObjetoPersistente;

/**
 * @author alysson
 *
 */
public class TesteDaoResponsavel extends TesteDaoObjetoPersistente<Responsavel> {
	private DaoResponsavel daoResponsavel;

	@Autowired
	public void setDaoResponsavel(DaoResponsavel daoResponsavel) {
		this.daoResponsavel = daoResponsavel;
	}

	@Override
	protected DaoJpaAbstrato<Responsavel> getDao() {
		return daoResponsavel;
	}

}
