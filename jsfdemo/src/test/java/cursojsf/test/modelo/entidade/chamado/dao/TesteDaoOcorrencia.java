/**
 * 
 */
package cursojsf.test.modelo.entidade.chamado.dao;

import org.springframework.beans.factory.annotation.Autowired;

import cursojsf.modelo.entidade.chamado.Ocorrencia;
import cursojsf.modelo.entidade.chamado.dao.DaoOcorrencia;
import cursojsf.modelo.entidade.dao.DaoJpaAbstrato;
import cursojsf.test.modelo.entidade.TesteDaoObjetoPersistente;

/**
 * @author alysson
 *
 */
public class TesteDaoOcorrencia extends TesteDaoObjetoPersistente<Ocorrencia> {
	private DaoOcorrencia daoOcorrencia;

	@Override
	protected DaoJpaAbstrato<Ocorrencia> getDao() {
		return daoOcorrencia;
	}

	@Autowired
	public void setDaoOcorrencia(DaoOcorrencia daoOcorrencia) {
		this.daoOcorrencia = daoOcorrencia;
	}

}
