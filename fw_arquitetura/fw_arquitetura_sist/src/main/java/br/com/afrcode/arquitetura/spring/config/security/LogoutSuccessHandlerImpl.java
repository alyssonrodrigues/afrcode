package br.com.afrcode.arquitetura.spring.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * Classe utilitário para tratamento de Logout de usuários para o Spring
 * Security.
 * 
 * 
 */
public class LogoutSuccessHandlerImpl extends SimpleUrlLogoutSuccessHandler implements InitializingBean {

    @Override
    public void
            onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                    throws IOException, ServletException {
        super.onLogoutSuccess(request, response, authentication);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

}
