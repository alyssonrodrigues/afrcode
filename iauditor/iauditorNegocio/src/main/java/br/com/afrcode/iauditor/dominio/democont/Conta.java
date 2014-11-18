package br.com.afrcode.iauditor.dominio.democont;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String label;

	private BigDecimal valor;

	private List<Conta> subcontas;

	public Conta() {

	}

	public static Conta fromJson(JsonElement jsonElement) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(jsonElement, Conta.class);
	}

	public Long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public List<Conta> getSubcontas() {
		if (subcontas == null) {
			subcontas = new ArrayList<Conta>();
		}
		return subcontas;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setSubcontas(List<Conta> subcontas) {
		this.subcontas = subcontas;
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
