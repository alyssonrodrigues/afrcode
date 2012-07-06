/**
 * 
 */
package cursojsf.test.modelo.entidade.usuario.povoador;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import curso.modelo.util.ContextoStub;
import cursojsf.modelo.entidade.usuario.Papel;
import cursojsf.modelo.entidade.usuario.Usuario;
import cursojsf.modelo.entidade.usuario.dao.DaoUsuario;
import cursojsf.test.modelo.entidade.povoador.Povoador;

/**
 * @author alysson
 *
 */
@Component
@Transactional
public class PovoadorUsuario extends Povoador {
	private DaoUsuario daoUsuario;
	
	private PovoadorPapel povoadorPapel;
	
	public PovoadorUsuario() {
		super(PovoadorUsuario.class);
	}

	@Override
	public void povoar() {
		getPovoadorPapel().povoar();
		criarUsuariosAleatorios();
		criarUsuariosEspeciais();
	}

	private void criarUsuariosEspeciais() {
		Usuario admin = getDaoUsuario().instanciarObjetoPadrao();
		admin.setLogin("administrador");
		admin.setSenha("senhaadmin");
		Papel papelAdmin = getPovoadorPapel().getDaoPapel().procurar(
				"codigo", "admin");
		Validate.notNull(papelAdmin);
		admin.getPapeis().add(papelAdmin);
		papelAdmin.getUsuarios().add(admin);
		getDaoUsuario().salvar(admin, new ContextoStub());
		
		Usuario guest = getDaoUsuario().instanciarObjetoPadrao();
		guest.setLogin("convidado");
		guest.setSenha("senhaconvidado");
		Papel papelGuest = getPovoadorPapel().getDaoPapel().procurar(
				"codigo", "guest");
		Validate.notNull(papelGuest);
		guest.getPapeis().add(papelGuest);
		papelGuest.getUsuarios().add(guest);
		getDaoUsuario().salvar(guest, new ContextoStub());
	}

	private void criarUsuariosAleatorios() {
		for (int i = 0; i < 30; i++) {
			Usuario usuario = getDaoUsuario().instanciarObjetoPadrao();
			usuario.setNome("Usuário [" + i + "]");
			usuario.setLogin("usuario" + i);
			getDaoUsuario().salvar(usuario, new ContextoStub());
		}
	}
	
	public static void main(String[] args) {
		new PovoadorUsuario().executar();
	}

	@Autowired
	public void setDaoUsuario(DaoUsuario daoUsuario) {
		this.daoUsuario = daoUsuario;
	}

	protected DaoUsuario getDaoUsuario() {
		return daoUsuario;
	}

	@Autowired
	public void setPovoadorPapel(PovoadorPapel povoadorPapel) {
		this.povoadorPapel = povoadorPapel;
	}

	protected PovoadorPapel getPovoadorPapel() {
		return povoadorPapel;
	}
	
}
