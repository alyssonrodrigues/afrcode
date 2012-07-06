/**
 * 
 */
package cursojsf.modelo.entidade.chamado;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.Length;
import org.hibernate.validator.Max;
import org.hibernate.validator.NotNull;

import cursojsf.modelo.entidade.ObjetoPersistente;

/**
 * @author alysson
 *
 */
@Entity
@SequenceGenerator(name ="SEQ_STORE", sequenceName = "SEQ_RESPONSAVEL")
public class Responsavel extends ObjetoPersistente {
	private Long codigo;
	
	private String descricao;
	
	@NotNull
	@Max(Long.MAX_VALUE)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@NotNull
	@Length(max = 16)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public void preencherComValoresDefault() {
		super.preencherComValoresDefault();
		this.setCodigo(new Long(this.hashCode()));
		this.setDescricao("Uma descricao.");
	}

}
