package demo.struts.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class AcaoTelaDeCalculos extends DispatchAction {
	
	private void preencherParamRequisicao(HttpServletRequest req,
			ActionForm form)
	{
		FormTelaDeCalculos formTela =
			(FormTelaDeCalculos) form;
		req.setAttribute("form", formTela);
	}

	public ActionForward somar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FormTelaDeCalculos formTela =
			(FormTelaDeCalculos) form;
		
		String valorA = formTela.getValorA();
		String valorB = formTela.getValorB();
		
		int resultado = formTela.somar(valorA, valorB);
		
		formTela.setResultado(Integer.toString(resultado));
		preencherParamRequisicao(request, form);
		
		return mapping.getInputForward();
	}
	
	public ActionForward subtrair(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FormTelaDeCalculos formTela =
			(FormTelaDeCalculos) form;
		
		String valorA = formTela.getValorA();
		String valorB = formTela.getValorB();
		
		int resultado = formTela.subtrair(valorA, valorB);
		
		formTela.setResultado(Integer.toString(resultado));
		preencherParamRequisicao(request, form);
		
		return mapping.getInputForward();
	}
	
	public ActionForward multiplicar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FormTelaDeCalculos formTela =
			(FormTelaDeCalculos) form;
		
		String valorA = formTela.getValorA();
		String valorB = formTela.getValorB();
		
		int resultado = formTela.multiplicar(valorA, valorB);
		
		formTela.setResultado(Integer.toString(resultado));
		preencherParamRequisicao(request, form);
		
		return mapping.getInputForward();
	}
	
	public ActionForward dividir(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FormTelaDeCalculos formTela =
			(FormTelaDeCalculos) form;
		
		String valorA = formTela.getValorA();
		String valorB = formTela.getValorB();
		
		int resultado = formTela.dividir(valorA, valorB);
		
		formTela.setResultado(Integer.toString(resultado));
		preencherParamRequisicao(request, form);
		
		return mapping.getInputForward();
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.getInputForward();
	}
	
	

}
