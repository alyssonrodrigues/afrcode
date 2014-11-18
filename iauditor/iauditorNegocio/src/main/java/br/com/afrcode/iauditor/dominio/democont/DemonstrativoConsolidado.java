package br.com.afrcode.iauditor.dominio.democont;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

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
		if (contas == null) {
			contas = new ArrayList<Conta>();
		}
		return contas;
	}

	public String getEntidade() {
		return entidade;
	}

	public Date getPeriodo() {
		return periodo;
	}

	public List<Conta> getSubcontas(String labelConta) {
		List<Conta> r = new ArrayList<Conta>();
		for (Conta conta : getContas()) {
			if (StringUtils.equals(labelConta, conta.getLabel())) {
				r.addAll(conta.getSubcontas());
				break;
			}
		}
		return r;
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
