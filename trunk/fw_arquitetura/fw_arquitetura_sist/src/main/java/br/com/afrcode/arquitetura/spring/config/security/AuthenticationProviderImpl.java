package br.com.afrcode.arquitetura.spring.config.security;

import java.util.Calendar;

import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.jaas.DefaultJaasAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Provedor de autenticação para o Spring responsável por encadear autenticação
 * via DAO pattern e via JAAS.
 * 
 * A autenticação via JAAS é posterior a via DAO pattern e só será executada em
 * caso de sucesso desta.
 * 
 * 
 */
public class AuthenticationProviderImpl implements AuthenticationProvider, InitializingBean {
    private static final Logger LOG = Logger.getLogger(AuthenticationProviderImpl.class);

    private DaoAuthenticationProvider daoAuthenticationProvider;

    private DefaultJaasAuthenticationProvider defaultJaasAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication auth = null;
        try {
            auth = daoAuthenticationProvider.authenticate(authentication);
            auth = defaultJaasAuthenticationProvider.authenticate(auth);
            registrarLogin(auth);
            return auth;
        } catch (AuthenticationException ae) {
            LOG.warn(ae);
            throw ae;
        }
    }

    private void registrarLogin(Authentication auth) {
        if (auth == null) {
            return;
        }
        if (auth.isAuthenticated()) {
            Calendar dataHoraLogin = Calendar.getInstance();
            if (UsernamePasswordAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
                // Armazenando dataHoraLogin no objeto container de autenticação
                // para uso porterior - no processo de logout, por
                // exemplo.
                UsernamePasswordAuthenticationToken userPassWdToken = (UsernamePasswordAuthenticationToken) auth;
                userPassWdToken.setDetails(dataHoraLogin);
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    /**
     * @param daoAuthenticationProvider
     *            the daoAuthenticationProvider to set
     */
    public void setDaoAuthenticationProvider(DaoAuthenticationProvider daoAuthenticationProvider) {
        this.daoAuthenticationProvider = daoAuthenticationProvider;
    }

    /**
     * @param defaultJaasAuthenticationProvider
     *            the defaultJaasAuthenticationProvider to set
     */
    public void
            setDefaultJaasAuthenticationProvider(DefaultJaasAuthenticationProvider defaultJaasAuthenticationProvider) {
        this.defaultJaasAuthenticationProvider = defaultJaasAuthenticationProvider;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Validate.notNull(daoAuthenticationProvider, "daoAuthenticationProvider não informado!");
        Validate.notNull(defaultJaasAuthenticationProvider, "defaultJaasAuthenticationProvider não informado!");
    }

}
