package br.com.afrcode.arquitetura.spring.config.security;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.jaas.AuthorityGranter;

public class AuthorityGranterImpl implements AuthorityGranter, InitializingBean {
    private static final Logger LOG = Logger.getLogger(AuthorityGranterImpl.class);
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Set<String> grant(Principal principal) {
        Set<String> roles = Collections.singleton("ROLE_USER");
        if (LOG.isDebugEnabled()) {
            LOG.debug("Principal: " + principal + " Roles: " + roles);
        }
        return roles;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Validate.notNull(userDetailsService, "userDetailsService não informado!");
    }

    /**
     * @param userDetailsService
     *            the userDetailsService to set
     */
    public void setUserDetailsService(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

}
