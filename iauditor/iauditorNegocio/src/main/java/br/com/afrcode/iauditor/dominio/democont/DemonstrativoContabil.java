package br.com.afrcode.iauditor.dominio.democont;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DemonstrativoContabil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String entidade;
	
	private Date periodo;
	
	private List<Conta> contas;
	
	public DemonstrativoContabil() {

	}

	public List<Conta> getContas() {
		return contas;
	}

	public String getEntidade() {
		return entidade;
	}

	public Date getPeriodo() {
		return periodo;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public void setPeriodo(Date periodo) {
		this.periodo = periodo;
	}

}
