/**
 * 
 */
package cursojsf.fronteira.action.usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.commons.lang.Validate;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Admin;
import org.richfaces.event.DragEvent;
import org.richfaces.event.DropEvent;

import curso.modelo.util.ContextoStub;
import cursojsf.fronteira.action.Acao;
import cursojsf.modelo.entidade.usuario.EnumSexo;
import cursojsf.modelo.entidade.usuario.Papel;
import cursojsf.modelo.entidade.usuario.Usuario;
import cursojsf.modelo.entidade.usuario.dao.DaoPapel;
import cursojsf.modelo.entidade.usuario.dao.DaoUsuario;

/**
 * @author alysson
 *
 */
@Name("acaoGestaoUsuarios")
@Scope(ScopeType.CONVERSATION)
public class AcaoGestaoUsuarios extends Acao {
	// Contém os filtros de pesquisa ...
	@Out
	private Usuario usuarioAPesquisar;

	// Usuários para listagem ...
	@DataModel 
	private Collection<Usuario> usuarios;
	
	@In(value = "#{daoUsuario}", required = true) 
	private DaoUsuario daoUsuario;
	
	@In(value = "#{daoPapel}", required = true)
	private DaoPapel daoPapel;
	
	// Id do usuário a gerir, seja para exclusão, seja para alteração.
	// Atribuído ao acionar os comandos de exclusão ou alteração.
	private Long idAGerir;
	
	// Listagem dos papeis disponíveis para associação ao usuário gerido.
	private Collection<Papel> papeisCadastrados;
	
	// Contém os dados do usuário gerido durante a edição.
	@Out(required = false)
	private Usuario usuarioAGerir;
	
	/**
	 * Método responsável pela iniciação deste backingbean.
	 */
	@Create
	public void init() {
		usuarioAPesquisar = new Usuario();
		pesquisar();
	}
	
	/**
	 * Método responsável por pesquisar usando os filtros de pesquisa informados.
	 */
	public void pesquisar() {
		usuarios = daoUsuario.procurarPorExemplo(usuarioAPesquisar);
	}
	
	public void exibirTodos() {
		usuarioAPesquisar = new Usuario();
		usuarios = daoUsuario.recuperarTodos();
	}
	
	@Begin @Admin
	public String alterar() {
		recuperarUsuarioAGerir();
		return "gerir";
	}

	@Admin
	public void excluir() {
		recuperarUsuarioAGerir();
		daoUsuario.excluir(usuarioAGerir, new ContextoStub());
		usuarioAGerir = null;
		init();
	}
	
	@Begin @Admin
	public String inserir() {
		usuarioAGerir = new Usuario();
		return "gerir";
	}
	
	@End @Admin
	public String salvar() {
		usuarioAGerir.setPapeis(getPapeisUsuario());
		daoUsuario.salvar(usuarioAGerir, new ContextoStub());
		usuarioAGerir = null;
		init();
		return "concluir";
	}
	
	@End
	public String cancelar() {
		usuarioAGerir = null;
		usuarioAPesquisar = new Usuario();
		return "concluir";
	}

	private void recuperarUsuarioAGerir() {
		Validate.notNull(idAGerir, "Id a gerir n�o atribuido!");
		usuarioAGerir = daoUsuario.procurar(idAGerir);
	}
	
	// Opções para combo opcoesSexo ...
	public List<EnumSexo> getOpcoesSexo() {
		return Arrays.asList(EnumSexo.values());
	}
	
	@DataModel
	public Collection<Papel> getPapeisCadastrados() {
		if (papeisCadastrados == null) {
			papeisCadastrados = daoPapel.recuperarTodos();
		}
		papeisCadastrados.removeAll(getPapeisUsuario());
		return papeisCadastrados;
	}
	
	@DataModel
	public Collection<Papel> getPapeisUsuario() {
		return usuarioAGerir != null ? 
				usuarioAGerir.getPapeis() :	new ArrayList<Papel>();
	}
	
	// Método acionado por um evento drop para a área de papeis a atribuir.
	public void associarPapelAoUsuarioAGerir(DropEvent dropEvent) {
		Papel papel = (Papel) dropEvent.getDragValue();
		usuarioAGerir.getPapeis().add(papel);
	}
	
	// Método acionado por um evento drag para a área de papeis a atribuir.
	public void desassociarPapelAoUsuarioAGerir(DragEvent dragEvent) {
		Papel papel = (Papel) dragEvent.getDragValue();
		usuarioAGerir.getPapeis().remove(papel);
	}
	
	// Método acionado por um evento drag para a área de papeis disponíveis.
	public void atualizarPapeisCadastrados(DragEvent dragEvent) {
		Papel papel = (Papel) dragEvent.getDragValue();
		papeisCadastrados.remove(papel);
	}
	
	// Método acionado por um evento drop para a área de papeis disponíveis.
	public void atualizarPapeisCadastrados(DropEvent dropEvent) {
		Papel papel = (Papel) dropEvent.getDragValue();
		papeisCadastrados.add(papel);
	}

	public Long getIdAGerir() {
		return idAGerir;
	}

	public void setIdAGerir(Long idAGerir) {
		this.idAGerir = idAGerir;
	}

	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
