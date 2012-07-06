package afrcode.fwarquitetura.spring.config.security;

public class UsuarioMock {

    private String login;
    private String senha;
    private Boolean seUsuarioAtivo;
    private Boolean seContaExpirada;
    private Boolean seSenhaExpirada;
    private Boolean seContaBloqueada;

    public UsuarioMock(String login) {
        super();
        this.login = login;
        this.senha = "VmLSNoga/KEUfw941jswjB4e2VY=";
        this.seUsuarioAtivo = true;
        this.seContaExpirada = true;
        this.seSenhaExpirada = true;
        this.seContaBloqueada = true;

    }

    public Boolean getSeUsuarioAtivo() {
        return seUsuarioAtivo;
    }

    public void setSeUsuarioAtivo(Boolean seUsuarioAtivo) {
        this.seUsuarioAtivo = seUsuarioAtivo;
    }

    public Boolean getSeContaExpirada() {
        return seContaExpirada;
    }

    public void setSeContaExpirada(Boolean seContaExpirada) {
        this.seContaExpirada = seContaExpirada;
    }

    public Boolean getSeSenhaExpirada() {
        return seSenhaExpirada;
    }

    public void setSeSenhaExpirada(Boolean seSenhaExpirada) {
        this.seSenhaExpirada = seSenhaExpirada;
    }

    public Boolean getSeContaBloqueada() {
        return seContaBloqueada;
    }

    public void setSeContaBloqueada(Boolean seContaBloqueada) {
        this.seContaBloqueada = seContaBloqueada;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
