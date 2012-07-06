/**
 * 
 */
package cursojsf.modelo.entidade.usuario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import cursojsf.modelo.entidade.ObjetoPersistente;

/**
 * @author alysson
 *
 */
@Entity
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "SEQ_USUARIO")
public class Usuario extends ObjetoPersistente {
	private String nome;
	
	private String email;
	
	private String login;
	
	private Date dataNascimento;
	
	private String telefone;
	
	private EnumSexo sexo;
	
	private String senha;
	
	private Collection<Papel> papeis;

	@NotNull
	@Length(max = 256)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Length(max = 256)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@NotNull
	@Length(max = 15)
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@NotNull
	public EnumSexo getSexo() {
		return sexo;
	}

	public void setSexo(EnumSexo sexo) {
		this.sexo = sexo;
	}

	@NotNull
	@Length(min = 6, max = 16)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotNull
	@Length(min = 6, max = 16)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "AssocUsuarioPapel", 
			joinColumns = {@JoinColumn(name = "USUARIO_ID")},
			inverseJoinColumns = {@JoinColumn(name = "PAPEL_ID")})
	@ForeignKey(name = "FK_ASSOC_USUARIO_PAPEL_USUARIO",
			inverseName = "FK_ASSOC_USUARIO_PAPEL_PAPEL")
	public Collection<Papel> getPapeis() {
		if (papeis == null) {
			papeis = new ArrayList<Papel>();
		}
		return papeis;
	}

	public void setPapeis(Collection<Papel> papeis) {
		this.papeis = papeis;
	}
	
	@Override
	public void preencherComValoresDefault() {
		super.preencherComValoresDefault();
		this.setDataNascimento(Calendar.getInstance().getTime());
		this.setEmail("email@email.com");
		this.setNome("Usuario anonimo");
		this.setSexo(EnumSexo.MASCULINO);
		this.setTelefone("55-55-5555-5555");
		this.setLogin("usuario");
		this.setSenha("123456");
	}
}
