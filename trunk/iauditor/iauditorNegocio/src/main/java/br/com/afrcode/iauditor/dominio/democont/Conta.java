package br.com.afrcode.iauditor.dominio.democont;

import java.io.Serializable;
import java.math.BigDecimal;

public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String label;
	
	private BigDecimal valor;
	
	private Conta subConta;
	
	public Conta() {

	}

	public String getLabel() {
		return label;
	}

	public Conta getSubConta() {
		return subConta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setSubConta(Conta subConta) {
		this.subConta = subConta;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
