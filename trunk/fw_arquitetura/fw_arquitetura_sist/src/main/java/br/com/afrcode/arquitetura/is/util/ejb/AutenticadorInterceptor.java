package br.com.afrcode.arquitetura.is.util.ejb;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.afrcode.arquitetura.is.spring.config.util.EJBSpringApplicationContextUtils;
import br.com.afrcode.arquitetura.spring.config.security.AuthenticationProviderImpl;
import br.com.afrcode.arquitetura.spring.config.security.UserDetailsServiceImpl;
import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Interceptor responsável por repassar ao Spring Security credenciais de
 * usuário presentes no EjbContext.
 */
public class AutenticadorInterceptor {
	private static final Logger LOG = Logger
			.getLogger(AutenticadorInterceptor.class);

	@Resource
	private EJBContext ejbContext;

	@Resource(name = "java:app/AppName")
	private String appName;

	private AuthenticationProviderImpl getAuthenticationProviderImpl() {
		AuthenticationProviderImpl authenticationProviderImpl =
				EJBSpringApplicationContextUtils
						.getBean(AuthenticationProviderImpl.class);
		return authenticationProviderImpl;
	}

	@AroundInvoke
	public Object repassarCredenciais(InvocationContext ctx) throws Exception {
		String username = EjbContextUtils.obterCredencial(ejbContext);
		repassarCredenciais(username);
		return ctx.proceed();
	}

	private void repassarCredenciais(String username) {
		Environment environment =
				EJBSpringApplicationContextUtils.getBean(Environment.class);
		if (!Arrays.asList(environment.getActiveProfiles()).contains(
				Profiles.PROFILE_TU)) {
			repassarCredenciaisParaAplicacao(username);
		} // Para TUs as credenciais são criadas via ContextoSegurancaTU.
	}

	private void repassarCredenciaisParaAplicacao(String username) {
		// Obtém credenciais via UserDetailsService.
		UserDetailsServiceImpl userDetailsServiceImpl =
				EJBSpringApplicationContextUtils
						.getBean(UserDetailsServiceImpl.class);
		UserDetails user = userDetailsServiceImpl.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken usrPasswdToken =
				new UsernamePasswordAuthenticationToken(user.getUsername(),
						user.getPassword(), user.getAuthorities());

		// Repassando credenciais ao Spring Security.
		AuthenticationProviderImpl authenticationProviderImpl =
				getAuthenticationProviderImpl();
		Authentication authentication =
				authenticationProviderImpl.authenticate(usrPasswdToken);
		Validate.notNull(
				authentication,
				"Houve falha no repasse via EjbContext de credenciais de usuário ao Spring Security!");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Usuário obtido via EjbContext repassado ao Spring Security: "
					+ authentication.getName());
		}
	}

}
