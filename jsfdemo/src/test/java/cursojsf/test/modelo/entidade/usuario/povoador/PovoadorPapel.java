/**
 * 
 */
package cursojsf.test.modelo.entidade.usuario.povoador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import curso.modelo.util.ContextoStub;
import cursojsf.modelo.entidade.usuario.Papel;
import cursojsf.modelo.entidade.usuario.dao.DaoPapel;
import cursojsf.test.modelo.entidade.povoador.Povoador;

/**
 * @author alysson
 *
 */
@Component
@Transactional
public class PovoadorPapel extends Povoador {
	private DaoPapel daoPapel;
	
	public PovoadorPapel() {
		super(PovoadorPapel.class);
	}

	@Override
	public void povoar() {
		Papel papelAdmin = daoPapel.instanciarObjetoPadrao();
		papelAdmin.setDescricao("Administrador de sistema");
		papelAdmin.setCodigo("admin");
		daoPapel.salvar(papelAdmin, new ContextoStub());
		Papel papelGuest = daoPapel.instanciarObjetoPadrao();
		papelGuest.setDescricao("Usuário convidado");
		papelGuest.setCodigo("guest");
		daoPapel.salvar(papelGuest, new ContextoStub());
	}
	
	public static void main(String[] args) {
		new PovoadorPapel().executar();
	}

	@Autowired
	public void setDaoPapel(DaoPapel daoPapel) {
		this.daoPapel = daoPapel;
	}

	protected DaoPapel getDaoPapel() {
		return daoPapel;
	}

}
