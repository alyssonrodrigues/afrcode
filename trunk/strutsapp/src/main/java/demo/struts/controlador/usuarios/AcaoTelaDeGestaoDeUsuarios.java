package demo.struts.controlador.usuarios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import curso.modelo.util.ContextoStub;

import demo.modelo.usuarios.DaoUsuario;
import demo.modelo.usuarios.Usuario;

public class AcaoTelaDeGestaoDeUsuarios extends DispatchAction {

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return pesquisar(mapping, form, request, response);
	}

	public ActionForward excluir(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FormTelaGestaoDeUsuarios formTela =
			(FormTelaGestaoDeUsuarios) form;
		String id = formTela.getId();
		
		DaoUsuario daoUsuario = new DaoUsuario();
		Usuario usuario = daoUsuario.procurar(Long.valueOf(id));
		daoUsuario.excluir(usuario, new ContextoStub());
		
		return unspecified(mapping, form, request, response);
	}

	public ActionForward pesquisar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FormTelaGestaoDeUsuarios formTela =
			(FormTelaGestaoDeUsuarios) form;
		
		Map<String, Object> parametros =
			new HashMap<String, Object>();
		if (formTela.getLogin() != null &&
				!formTela.getLogin().trim().equals("")) {
			parametros.put("login", formTela.getLogin());
		}
		if (formTela.getNome() != null &&
				!formTela.getNome().trim().equals("")) {
			parametros.put("nome", formTela.getNome());
		}
		
		DaoUsuario daoUsuario = new DaoUsuario();
		List<Usuario> usuarios =
			daoUsuario.procurar(parametros);
		request.setAttribute("usuarios", usuarios);
		
		return mapping.getInputForward();
	}

}
