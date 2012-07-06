/**
 * 
 */
package cursojsf.modelo.entidade.usuario;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import cursojsf.modelo.entidade.ObjetoPersistente;

/**
 * @author alysson
 *
 */
@Entity
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "SEQ_PAPEL")
public class Papel extends ObjetoPersistente {
	private String descricao;
	
	private String codigo;
	
	private Collection<Usuario> usuarios;
	
	public Papel() {
		
	}
	
	public Papel(String descricao, String codigo) {
		this.descricao = descricao;
		this.codigo = codigo;
	}

	@NotNull
	@Length(max = 64)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@NotNull
	@Length(max = 16)
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "papeis",
			cascade = CascadeType.PERSIST)
	@JoinTable(name = "AssocUsuarioPapel", 
			joinColumns = {@JoinColumn(name = "PAPEL_ID")},
			inverseJoinColumns = {@JoinColumn(name = "USUARIO_ID")})
	public Collection<Usuario> getUsuarios() {
		if (usuarios == null) {
			usuarios = new ArrayList<Usuario>();
		}
		return usuarios;
	}

	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@Override
	public void preencherComValoresDefault() {
		super.preencherComValoresDefault();
		this.setDescricao("Um papel qualquer");
		this.setCodigo("papel");
	}

}
