package br.com.afrcode.arquitetura.apresentacao.managedbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.security.core.userdetails.User;

import br.com.afrcode.arquitetura.modelo.entidade.IEntidade;
import br.com.afrcode.arquitetura.util.contexto.IContextoSeguranca;
import br.com.afrcode.arquitetura.util.mensagem.IMensagem;

/**
 * Superclasse de JSF managed beans.
 * 
 * Pretende-se que esta seja a superclasse de todos os managed beans JSF.
 * 
 * @author moniquebm
 * 
 */
public abstract class ManagedBeanAbstrato implements IManagedBean, Serializable {
    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{contextoSeguranca}")
    private IContextoSeguranca contextoSeguranca;

    @Override
    public void adicionarMensagem(IMensagem mensagem) {
        Validate.notNull(mensagem, "Mensagem n�o deve ser nula!");
        mensagem.tratarMensagem();
    }

    @Override
    public void adicionarObjetoEmSessaoHttp(String chave, Object objetoEmSessao) {
        validarChaveObjetoEmSessao(chave);
        Validate.notNull(objetoEmSessao, "Objeto em sess�o n�o deve ser n�lo!");

        boolean isObjEntidade =
                objetoEmSessao.getClass().getAnnotation(Entity.class) != null
                        || IEntidade.class.isAssignableFrom(objetoEmSessao.getClass());
        if (isObjEntidade) {
            throw new IllegalArgumentException(
                    "N�o � permitida a inclus�o de Entidades em sess�o HTTP - problemas "
                            + "relacionados: detached object, stalled data, lazy initialization exceptions, resources leak, etc.");
        }

        HttpServletRequest request = getHttpServletRequest();
        request.getSession().setAttribute(chave, objetoEmSessao);
    }

    /**
     * M�todo de verifica��o de autoriza��o de acesso ao MBean.
     * 
     * MBeanS n�o s�o beans Spring, s�o beans JSF, e por isto � necess�rio
     * acionar o componente AccessDecisionManager do Spring programaticamente
     * para fazer uso do mecanismos de autoriza��o adequadamente.
     */
    @PostConstruct
    public void checarAutorizacao() {
        RolesAllowed rolesAllowed = getClass().getAnnotation(RolesAllowed.class);
        Validate.notNull(rolesAllowed, "Especifique a anota��o @RolesAllowed no MBean " + getClass().getName()
                + " informando ao menos uma fun��o computacional!");
        contextoSeguranca.checarAutorizacao(rolesAllowed);
    }

    public HttpServletRequest getHttpServletRequest() {
        HttpServletRequest httpServletRequest =
                (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return httpServletRequest;
    }

    public HttpServletResponse getHttpServletResponse() {
        HttpServletResponse httpServletResponse =
                (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        return httpServletResponse;
    }

    @Override
    public Object getObjetoEmSessao(String chave) {
        validarChaveObjetoEmSessao(chave);

        HttpServletRequest request = getHttpServletRequest();
        Object objetoEmSessao = request.getSession().getAttribute(chave);

        return objetoEmSessao;
    }

    @Override
    public void removerObjetoEmSessaoHttp(String chave) {
        validarChaveObjetoEmSessao(chave);

        HttpServletRequest request = getHttpServletRequest();
        request.getSession().removeAttribute(chave);
    }

    /**
     * M�todo de recupera��o do usu�rio autenticado para fins de consulta por
     * MBeans concretos.
     * 
     * @return
     */
    protected User getUsuarioAutenticado() {
        return contextoSeguranca.getUsuarioAutenticado();
    }

    private void validarChaveObjetoEmSessao(String chave) {
        Validate.isTrue(StringUtils.isNotBlank(chave), "Chave identificadora de objeto em sess�o n�o deve ser nula!");
    }

    /**
     * @param contextoSeguranca
     *            the contextoSeguranca to set
     */
    public void setContextoSeguranca(IContextoSeguranca contextoSeguranca) {
        this.contextoSeguranca = contextoSeguranca;
    }

    /**
     * @return the contextoSeguranca
     */
    protected IContextoSeguranca getContextoSeguranca() {
        return contextoSeguranca;
    }

}
