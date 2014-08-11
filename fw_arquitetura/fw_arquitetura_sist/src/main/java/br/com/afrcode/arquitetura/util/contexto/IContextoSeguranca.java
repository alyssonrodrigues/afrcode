package br.com.afrcode.arquitetura.util.contexto;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.core.userdetails.User;

/**
 * Interface que provê acesso às informações de autenticação e autorização do
 * usuário atual.
 * 
 * 
 */
public interface IContextoSeguranca {

    /**
     * Recupera o usuário autenticado na sessão.
     * 
     * @return O usuário autenticado na sessão.
     */
    User getUsuarioAutenticado();

    /**
     * Verifica se o usuário é anônimo ou não.
     * 
     * @return <code>true</code> caso o usuário seja anônimo, <code>false</code>
     *         caso contrário.
     */
    boolean seUsuarioAnonimo();

    /**
     * Faz a autorização do usuário atual, com base em um conjunto de papéis
     * ("roles") permitidos.
     * 
     * @param rolesAllowed
     *            O conjunto de papéis permitidos.
     */
    void checarAutorizacao(RolesAllowed rolesAllowed);

}
