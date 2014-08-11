package br.com.afrcode.arquitetura.spring.config.security;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.DefaultJaasAuthenticationProvider;
import org.springframework.security.core.Authentication;

import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;

public class SpringSecurityContextTest extends AbstractCasoTesteEmMemoria {

    @Autowired
    private DefaultJaasAuthenticationProvider defaultJaasAuthenticationProvider;

    private UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(LoginModuleParaTU.USER,
            LoginModuleParaTU.PASSWD);

    @Test
    public void testarLoginJAASComSucesso() {
        Authentication auth = defaultJaasAuthenticationProvider.authenticate(token);
        Assert.assertEquals("O usuário deveria ter sido autenticado com sucesso!", true, auth.isAuthenticated());
        Assert.assertEquals("O usuário autenticado deveria ter o principal: " + token.getPrincipal(),
                token.getPrincipal(), auth.getPrincipal());
        Assert.assertEquals("O usuário autenticado deveria ter a credencial: " + AuthorityGranterParaTU.ROLE_USER,
                token.getCredentials(), auth.getCredentials());
        Assert.assertEquals("O usuário autenticado deveria ter a autorização:" + AuthorityGranterParaTU.ROLE_USER,
                false, auth.getAuthorities().isEmpty());
    }

}
