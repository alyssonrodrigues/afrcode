package br.com.afrcode.arquitetura.spring.config.security;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.jaas.AuthorityGranter;

public class AuthorityGranterParaTU implements AuthorityGranter {
    public static final String ROLE_USER = "ROLE_USER";

    @Override
    public Set<String> grant(Principal principal) {
        Set<String> rtnSet = new HashSet<String>();

        if (principal.getName().equals(LoginModuleParaTU.PRINCIPAL)) {
            rtnSet.add(ROLE_USER);
        }

        return rtnSet;
    }

}
