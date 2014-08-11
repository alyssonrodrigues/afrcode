package br.com.afrcode.arquitetura.util.contexto;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.core.userdetails.User;

/**
 * Interface que prov� acesso �s informa��es de autentica��o e autoriza��o do
 * usu�rio atual.
 * 
 * 
 */
public interface IContextoSeguranca {

    /**
     * Recupera o usu�rio autenticado na sess�o.
     * 
     * @return O usu�rio autenticado na sess�o.
     */
    User getUsuarioAutenticado();

    /**
     * Verifica se o usu�rio � an�nimo ou n�o.
     * 
     * @return <code>true</code> caso o usu�rio seja an�nimo, <code>false</code>
     *         caso contr�rio.
     */
    boolean seUsuarioAnonimo();

    /**
     * Faz a autoriza��o do usu�rio atual, com base em um conjunto de pap�is
     * ("roles") permitidos.
     * 
     * @param rolesAllowed
     *            O conjunto de pap�is permitidos.
     */
    void checarAutorizacao(RolesAllowed rolesAllowed);

}
