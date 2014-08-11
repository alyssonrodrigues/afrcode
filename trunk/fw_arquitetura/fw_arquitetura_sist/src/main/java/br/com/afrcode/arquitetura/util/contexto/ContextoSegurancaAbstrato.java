package br.com.afrcode.arquitetura.util.contexto;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.annotation.Jsr250SecurityConfig;
import org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Classe base de implementação de IContextoSeguranca para uso em
 * PROFILE_APLICACAO e PROFILE_TU.
 * 
 * 
 */
public abstract class ContextoSegurancaAbstrato implements IContextoSeguranca {

    @Autowired
    private MethodSecurityInterceptor methodSecurityInterceptor;

    public ContextoSegurancaAbstrato() {
        super();
    }

    @Override
    public boolean seUsuarioAnonimo() {
        Authentication authentication = getAuthentication();
        AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    @Override
    public void checarAutorizacao(RolesAllowed rolesAllowed) {
        Collection<ConfigAttribute> securityConfAttributes = new ArrayList<ConfigAttribute>();
        for (String funcaoComputacional : rolesAllowed.value()) {
            securityConfAttributes.add(new Jsr250SecurityConfig(funcaoComputacional));
        }
        AccessDecisionManager accessDecisionManager = methodSecurityInterceptor.getAccessDecisionManager();
        Authentication authentication = getAuthentication();
        accessDecisionManager.decide(authentication, null, securityConfAttributes);
    }

    protected Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}