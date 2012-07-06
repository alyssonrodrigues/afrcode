/**
 * 
 */
package cursojsf.test.modelo.entidade.dao;

import org.springframework.beans.factory.annotation.Autowired;

import cursojsf.modelo.entidade.EntidadeStub;
import cursojsf.modelo.entidade.dao.DaoEntidadeStub;
import cursojsf.modelo.entidade.dao.DaoJpaAbstrato;
import cursojsf.test.modelo.entidade.TesteDaoObjetoPersistente;

/**
 * @author alysson
 *
 */
public class TesteDaoEntidadeStub 
	extends TesteDaoObjetoPersistente<EntidadeStub> {

	private DaoEntidadeStub daoEntidadeStub;

	@Autowired
	public void setDaoEntidadeStub(DaoEntidadeStub daoEntidadeStub) {
		this.daoEntidadeStub = daoEntidadeStub;
	}
	
	@Override
	protected DaoJpaAbstrato<EntidadeStub> getDao() {
		return daoEntidadeStub;
	}

}
