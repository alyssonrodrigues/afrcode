package demo.struts.controlador;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class FormTelaDeCalculos extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String valorA;

	private String valorB;

	private String resultado;

	private String metodo;

	public String getValorA() {
		return valorA;
	}

	public void setValorA(String valorA) {
		this.valorA = valorA;
	}

	public String getValorB() {
		return valorB;
	}

	public void setValorB(String valorB) {
		this.valorB = valorB;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public int somar(String valorX, String valorY) {
		int a = converterStringParaInt(valorX);
		int b = converterStringParaInt(valorY);
		return a + b;
	}

	private int converterStringParaInt(String valor) {
		int r = 0;
		if (valor != null && !valor.trim().equals("")) {
			r = Integer.parseInt(valor);
		}
		return r;
	}

	public int subtrair(String valorX, String valorY) {
		int a = converterStringParaInt(valorX);
		int b = converterStringParaInt(valorY);
		return a - b;
	}

	public int dividir(String valorX, String valorY) {
		int a = converterStringParaInt(valorX);
		int b = converterStringParaInt(valorY);
		return a / b;
	}

	public int multiplicar(String valorX, String valorY) {
		int a = converterStringParaInt(valorX);
		int b = converterStringParaInt(valorY);
		return a * b;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		valorA = "0";
		valorB = "0";
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors erros = new ActionErrors();
		// Verificação de tipos numéricos ...
		boolean valorAOk = true;
		boolean valorBOk = true;
		try {
			Integer.parseInt(valorA);
		} catch (NumberFormatException e) {
			ActionMessage erro = 
				new ActionMessage("erros.tipoNaoInteiro");
			erros.add("valorA", erro);
			valorAOk = false;
		}
		try {
			Integer.parseInt(valorB);
		} catch (NumberFormatException e) {
			ActionMessage erro = 
				new ActionMessage("erros.tipoNaoInteiro");
			erros.add("valorB", erro);
			valorBOk = false;
		}
		if (!valorAOk || !valorBOk) {
			return erros;
		}

		if (metodo != null && metodo.equals("dividir")) {
			int b = Integer.parseInt(valorB);
			if (b == 0) {
				ActionMessage erro = 
					new ActionMessage("erros.divisaoPorZero");
				erros.add("valorB", erro);
			}
		}
		return erros;
	}

}
