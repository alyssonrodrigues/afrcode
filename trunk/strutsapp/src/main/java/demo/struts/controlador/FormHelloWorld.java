package demo.struts.controlador;

import org.apache.struts.action.ActionForm;

public class FormHelloWorld extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
