package br.com.afrcode.iauditor.dominio.democont;

import java.io.Serializable;
import java.math.BigDecimal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	private String label;

	private BigDecimal valor;

	private Conta subConta;

	public Conta() {

	}

	public static Conta fromJson(JsonElement jsonElement) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(jsonElement, Conta.class);
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

	public String toJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this, Conta.class);
	}

	@Override
	public String toString() {
		return toJson();
	}

}
