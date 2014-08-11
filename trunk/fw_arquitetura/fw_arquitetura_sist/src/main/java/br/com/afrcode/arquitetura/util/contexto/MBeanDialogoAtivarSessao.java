package br.com.afrcode.arquitetura.util.contexto;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Profile;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

@ManagedBean
@SessionScoped
@Profile({ Profiles.PROFILE_APLICACAO })
public class MBeanDialogoAtivarSessao implements Serializable {

    private static final int TEMPO_EXIBICAO_PADRAO = 60;

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(MBeanDialogoAtivarSessao.class);

    private static final String MSG_ERRO_AO_REDIRECIONAR = "N�o foi poss�vel redirecionar para a p�gina de login, "
            + "pois j� houve envio de reposta HTML ao usu�rio!";

    private int tempoExibicao = TEMPO_EXIBICAO_PADRAO;

    private String urlLogoutRelativa = "/web/efetuarLogout";

    @ManagedProperty("#{contextoSeguranca}")
    private IContextoSeguranca contextoSeguranca;

    public boolean isAcessoAnonimo() {
        boolean seUsuarioAnonimo = contextoSeguranca.seUsuarioAnonimo();
        return seUsuarioAnonimo;
    }

    /**
     * Retorna o tempo durante o qual a caixa de di�logo dever� permanecer
     * aberta.
     * 
     * @return O tempo durante o qual a caixa de di�logo dever� permanecer
     *         aberta.
     */
    public int getTempoExibicao() {
        return tempoExibicao;
    }

    /**
     * Retorna o tempo m�ximo, em segundos, para sess�es inativas.
     * 
     * @return O tempo m�ximo, em segundos, para sess�es inativas.
     */
    public int getTimeoutSessao() {
        HttpServletRequest httpServletRequest =
                (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sessao = httpServletRequest.getSession();
        return sessao.getMaxInactiveInterval();
    }

    public void ativarSessao(ActionEvent e) {
        // N�o faz nada - serve apenas para gerar uma requisi��o.
    }

    public void efetuarLogout(ActionEvent e) throws IOException {
        HttpServletResponse response =
                (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        if (response.isCommitted()) {
            LOG.error(MSG_ERRO_AO_REDIRECIONAR);
        } else {
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.getExternalContext().redirect(getLogoutUrl());
        }
    }

    public String getLogoutUrl() {
        String contextPath = getRequest().getContextPath();
        return contextPath + urlLogoutRelativa;
    }

    private HttpServletRequest getRequest() {
        HttpServletRequest httpServletRequest =
                (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return httpServletRequest;
    }

    public IContextoSeguranca getContextoSeguranca() {
        return contextoSeguranca;
    }

    public void setContextoSeguranca(IContextoSeguranca contextoSeguranca) {
        this.contextoSeguranca = contextoSeguranca;
    }

}
