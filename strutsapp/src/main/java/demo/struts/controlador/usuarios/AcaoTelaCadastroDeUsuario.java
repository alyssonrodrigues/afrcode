package demo.struts.controlador.usuarios;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import curso.modelo.util.ContextoStub;

import demo.modelo.usuarios.DaoUsuario;
import demo.modelo.usuarios.Usuario;

public class AcaoTelaCadastroDeUsuario extends DispatchAction {

	public ActionForward inserir(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.getInputForward();
	}

	public ActionForward alterar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FormTelaCadastroDeUsuario formTela =
			(FormTelaCadastroDeUsuario) form;
		String id = formTela.getId();
		
		DaoUsuario daoUsuario = new DaoUsuario();
		Usuario usuario = daoUsuario.procurar(Long.valueOf(id));
		
		formTela.setEmail(usuario.getEmail());
		formTela.setLogin(usuario.getLogin());
		formTela.setNome(usuario.getNome());
		
		return mapping.getInputForward();
	}

	public ActionForward salvar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FormTelaCadastroDeUsuario formTela =
			(FormTelaCadastroDeUsuario) form;
		Usuario usuario = null;
		DaoUsuario daoUsuario = new DaoUsuario();
		if (formTela.getId() != null &&
				!formTela.getId().trim().equals("")) {
			String id = formTela.getId();
			usuario = daoUsuario.procurar(Long.valueOf(id));
		}
		else {
			usuario = new Usuario();
		}
		
		usuario.setNome(formTela.getNome());
		usuario.setLogin(formTela.getLogin());
		usuario.setEmail(formTela.getEmail());
		
		daoUsuario.salvar(usuario, new ContextoStub());
		return mapping.findForward("fwTelaGestaoDeUsuarios");
	}

}
