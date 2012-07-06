/**
 * 
 */
package curso.modelo.entidade;

import java.io.Serializable;

import curso.modelo.util.Contexto;

/**
 * @author alysson
 *
 */
public abstract class EntidadeAbstrata implements Entidade<Long>, Serializable {
	private Long id;

	/* (non-Javadoc)
	 * @see curso.modelo.entidade.Entidade#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see curso.modelo.entidade.Entidade#setId(java.lang.Object)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		}
		EntidadeAbstrata ent = (EntidadeAbstrata) obj;
		if (getId() != null) {
			return getId().equals(ent.getId());
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		if (getId() != null) {
			return getId().hashCode();
		} else {
			return super.hashCode();
		}
	}
	
	@Override
	public void validarSalvamento(Contexto contexo) {
		
	}
	
	@Override
	public void validarExclusao(Contexto contexto) {
		
	}
	
	@Override
	public void preencherComValoresDefault() {
	}
	
}
