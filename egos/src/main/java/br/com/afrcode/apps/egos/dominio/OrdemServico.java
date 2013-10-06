package br.com.afrcode.apps.egos.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "ORDEM_SERVICO")
public class OrdemServico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "DESCRICAO")
	@NotBlank
	@Length(max = 100)
	private String descricao;
	
	@Column(name = "VALOR")
	@NotNull
	private BigDecimal valor;
	
	@Column(name = "DATAENTREGAEMCONTRATO")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dataEntregaEmContrato;
	
	@Column(name = "CONCLUIDA")
	@NotNull
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

	public Date getDataEntregaEmContrato() {
		return dataEntregaEmContrato;
	}

	public void setDataEntregaEmContrato(Date dataEntregaEmContrato) {
		this.dataEntregaEmContrato = dataEntregaEmContrato;
	}

	public Boolean getConcluida() {
		return concluida;
	}

	public void setConcluida(Boolean concluida) {
		this.concluida = concluida;
	}
}
