/**
 * 
 */
package cursojsf.test.modelo.entidade.chamado.povoador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import curso.modelo.util.ContextoStub;
import cursojsf.modelo.entidade.chamado.Responsavel;
import cursojsf.modelo.entidade.chamado.dao.DaoResponsavel;
import cursojsf.test.modelo.entidade.povoador.Povoador;

/**
 * @author alysson
 *
 */
@Component
@Transactional
public class PovoadorResponsavel extends Povoador {
	private DaoResponsavel daoResponsavel;
	
	public PovoadorResponsavel() {
		super(PovoadorResponsavel.class);
	}

	@Override
	public void povoar() {
		for (int i = 1; i <= 100; i++) {
			Responsavel resp = daoResponsavel.instanciarObjetoPadrao();
			resp.setCodigo(new Long(i));
			resp.setDescricao("Viatura[" + i + "]");
			daoResponsavel.salvar(resp, new ContextoStub());
		}
	}

	@Autowired
	public void setDaoResponsavel(DaoResponsavel daoResponsavel) {
		this.daoResponsavel = daoResponsavel;
	}
	
	public static void main(String[] args) {
		new PovoadorResponsavel().executar();
	}

}
