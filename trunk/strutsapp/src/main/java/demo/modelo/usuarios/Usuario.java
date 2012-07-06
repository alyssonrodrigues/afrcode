package demo.modelo.usuarios;

import curso.modelo.entidade.EntidadeAbstrata;

public class Usuario extends EntidadeAbstrata {
	private String nome;

	private String email;

	private String login;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
