package br.com.afrcode.iauditor.dominio.democont;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class DemonstrativoConsolidado implements Serializable {

	public static final String PERIODO_PATTERN = "dd/MM/yyyy";

	private static final long serialVersionUID = 1L;

	private String entidade;

	private Date periodo;

	private List<Conta> contas;

	public DemonstrativoConsolidado() {

	}

	public static DemonstrativoConsolidado fromJson(JsonElement jsonElement) {
		Gson gson = new GsonBuilder().setPrettyPrinting()
				.setDateFormat(PERIODO_PATTERN).create();
		return gson.fromJson(jsonElement, DemonstrativoConsolidado.class);
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

	public String toJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting()
				.setDateFormat(PERIODO_PATTERN).create();
		return gson.toJson(this, DemonstrativoConsolidado.class);
	}

	@Override
	public String toString() {
		return toJson();
	}

}
