/**
 * 
 */
package cursojsf.modelo.entidade.chamado;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.Max;
import org.hibernate.validator.NotNull;

import cursojsf.modelo.entidade.ObjetoPersistente;

/**
 * @author alysson
 *
 */
@Entity
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "SEQ_OCORRENCIA")
public class Ocorrencia extends ObjetoPersistente {
	private Long codigo;
	
	private Chamado chamado;
	
	private Collection<Responsavel> responsaveis;

	@NotNull
	@Max(Long.MAX_VALUE)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@NotNull
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@ForeignKey(name = "FK_OCORRENCIA_CHAMADO")
	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "AssocOcorrenciaResponsavel",
			joinColumns = {@JoinColumn(name = "RESPONSAVEL_ID")},
			inverseJoinColumns = {@JoinColumn(name = "OCORRENCIA_ID")})
  @ForeignKey(name = "FK_ASSOC_OCOR_RESP_OCOR",
  		inverseName = "FK_ASSOC_OCOR_RESP_RESP")
	public Collection<Responsavel> getResponsaveis() {
		if (responsaveis == null) {
			responsaveis = new ArrayList<Responsavel>();
		}
		return responsaveis;
	}

	public void setResponsaveis(Collection<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}
	
	@Override
	public void preencherComValoresDefault() {
		super.preencherComValoresDefault();
		Chamado umChamado = new Chamado();
		umChamado.preencherComValoresDefault();
		this.setChamado(umChamado);
		this.setCodigo(umChamado.getCodigo());
	}

}
