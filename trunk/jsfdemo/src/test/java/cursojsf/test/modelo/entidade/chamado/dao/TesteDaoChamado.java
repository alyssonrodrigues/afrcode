/**
 * 
 */
package cursojsf.test.modelo.entidade.chamado.dao;

import org.springframework.beans.factory.annotation.Autowired;

import cursojsf.modelo.entidade.chamado.Chamado;
import cursojsf.modelo.entidade.chamado.dao.DaoChamado;
import cursojsf.modelo.entidade.dao.DaoJpaAbstrato;
import cursojsf.test.modelo.entidade.TesteDaoObjetoPersistente;

/**
 * @author alysson
 *
 */
public class TesteDaoChamado extends TesteDaoObjetoPersistente<Chamado> {
	private DaoChamado daoChamado;

	@Autowired
	public void setDaoChamado(DaoChamado daoChamado) {
		this.daoChamado = daoChamado;
	}

	@Override
	protected DaoJpaAbstrato<Chamado> getDao() {
		return daoChamado;
	}

}
