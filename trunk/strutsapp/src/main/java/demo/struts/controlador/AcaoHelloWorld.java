package demo.struts.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AcaoHelloWorld extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FormHelloWorld formTela = (FormHelloWorld) form;
		// Neste exemplo, é necessário obter o nome do form bean?
		String nome = formTela.getNome();
		System.out.println(nome);

		return mapping.getInputForward();
	}

}
