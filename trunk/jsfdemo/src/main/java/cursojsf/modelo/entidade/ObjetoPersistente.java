/**
 * 
 */
package cursojsf.modelo.entidade;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import curso.modelo.entidade.EntidadeAbstrata;

/**
 * @author alysson
 *
 */
@MappedSuperclass
public abstract class ObjetoPersistente extends EntidadeAbstrata {
	private Long versao;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE")
	@Override
	public Long getId() {
		return super.getId();
	}
	
	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Version
	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
	}
}
