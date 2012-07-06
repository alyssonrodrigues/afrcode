package localhost;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class ObjetoPersistente {
	private Long versao;

	@Version
	public Long getVersao() {
		return versao;
	}

	public void setVersao(Long versao) {
		this.versao = versao;
	}

	public abstract Long getId();

	public abstract void setId(Long id);
}