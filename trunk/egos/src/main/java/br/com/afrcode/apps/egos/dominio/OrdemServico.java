package br.com.afrcode.apps.egos.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

public class OrdemServico implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;
	private BigDecimal valor;
	private Calendar dataEntregaEmContrato;
	private Boolean concluida;
	
	public OrdemServico() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Calendar getDataEntregaEmContrato() {
		return dataEntregaEmContrato;
	}

	public void setDataEntregaEmContrato(Calendar dataEntregaEmContrato) {
		this.dataEntregaEmContrato = dataEntregaEmContrato;
	}

	public Boolean getConcluida() {
		return concluida;
	}

	public void setConcluida(Boolean concluida) {
		this.concluida = concluida;
	}
}
