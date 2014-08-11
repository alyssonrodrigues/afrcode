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
        Validate.notNull(mensagem, "Mensagem não deve ser nula!");
        mensagem.tratarMensagem();
    }

    @Override
    public void adicionarObjetoEmSessaoHttp(String chave, Object objetoEmSessao) {
        validarChaveObjetoEmSessao(chave);
        Validate.notNull(objetoEmSessao, "Objeto em sessão não deve ser núlo!");

        boolean isObjEntidade =
                objetoEmSessao.getClass().getAnnotation(Entity.class) != null
                        || IEntidade.class.isAssignableFrom(objetoEmSessao.getClass());
        if (isObjEntidade) {
            throw new IllegalArgumentException(
                    "Não é permitida a inclusão de Entidades em sessão HTTP - problemas "
                            + "relacionados: detached object, stalled data, lazy initialization exceptions, resources leak, etc.");
        }

        HttpServletRequest request = getHttpServletRequest();
        request.getSession().setAttribute(chave, objetoEmSessao);
    }

    /**
     * Método de verificação de autorização de acesso ao MBean.
     * 
     * MBeanS não são beans Spring, são beans JSF, e por isto é necessário
     * acionar o componente AccessDecisionManager do Spring programaticamente
     * para fazer uso do mecanismos de autorização adequadamente.
     */
    @PostConstruct
    public void checarAutorizacao() {
        RolesAllowed rolesAllowed = getClass().getAnnotation(RolesAllowed.class);
        Validate.notNull(rolesAllowed, "Especifique a anotação @RolesAllowed no MBean " + getClass().getName()
                + " informando ao menos uma função computacional!");
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
     * Método de recuperação do usuário autenticado para fins de consulta por
     * MBeans concretos.
     * 
     * @return
     */
    protected User getUsuarioAutenticado() {
        return contextoSeguranca.getUsuarioAutenticado();
    }

    private void validarChaveObjetoEmSessao(String chave) {
        Validate.isTrue(StringUtils.isNotBlank(chave), "Chave identificadora de objeto em sessão não deve ser nula!");
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
