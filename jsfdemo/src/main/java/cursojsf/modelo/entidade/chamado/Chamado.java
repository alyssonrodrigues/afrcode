/**
 * 
 */
package cursojsf.modelo.entidade.chamado;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.Length;
import org.hibernate.validator.Max;
import org.hibernate.validator.NotNull;

import cursojsf.modelo.entidade.ObjetoPersistente;

/**
 * @author alysson
 * 
 */
@Entity
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "SEQ_CHAMADO")
public class Chamado extends ObjetoPersistente {
	private Long codigo;

	private Calendar dataHora;

	private Boolean urgente;

	private String descricao;
	
	private Ocorrencia ocorrencia;
	
	private Boolean emAtendimento;

	@NotNull
	@Max(value = Long.MAX_VALUE)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getDataHora() {
		return dataHora;
	}

	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}

	public Boolean getUrgente() {
		return urgente;
	}

	@Transient
	public boolean isUrgente() {
		return urgente != null ? urgente : false;
	}

	public void setUrgente(Boolean urgente) {
		this.urgente = urgente;
	}

	@NotNull
	@Length(max = 500)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, 
			mappedBy = "chamado")
	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}
	
	@Transient
	public boolean isEmAtendimento() {
		return emAtendimento != null ? emAtendimento : false;
	}

	@NotNull
	public Boolean getEmAtendimento() {
		return emAtendimento;
	}

	public void setEmAtendimento(Boolean emAtendimento) {
		this.emAtendimento = emAtendimento;
	}
	
	@Override
	public void preencherComValoresDefault() {
		super.preencherComValoresDefault();
		this.setCodigo(new Long(this.hashCode()));
		this.setDataHora(Calendar.getInstance());
		this.setUrgente(false);
		this.setDescricao("Uma descricao qualquer!");
		this.setEmAtendimento(false);
	}

}
