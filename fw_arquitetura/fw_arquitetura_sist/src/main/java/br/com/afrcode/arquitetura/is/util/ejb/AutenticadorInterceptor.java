package br.com.afrcode.arquitetura.is.util.ejb;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.afrcode.arquitetura.is.spring.config.util.EJBSpringApplicationContextUtils;
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

	@AroundInvoke
	public Object repassarCredenciais(InvocationContext ctx) throws Exception {
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			String username = EjbContextUtils.obterCredencial(ejbContext);
			repassarCredenciais(username);
		}
		return ctx.proceed();
	}

	private void repassarCredenciais(String username) {
		Environment environment = EJBSpringApplicationContextUtils
				.getBean(Environment.class);
		if (!Arrays.asList(environment.getActiveProfiles()).contains(
				Profiles.PROFILE_TU)) {
			repassarCredenciaisParaAplicacao(username);
		} // Para TUs as credenciais são criadas via ContextoSegurancaTU.
	}

	private void repassarCredenciaisParaAplicacao(String username) {
		// Obtém credenciais via UserDetailsService.
		UserDetailsServiceImpl userDetailsServiceImpl = EJBSpringApplicationContextUtils
				.getBean(UserDetailsServiceImpl.class);
		UserDetails user = userDetailsServiceImpl.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken usrPasswdToken = new UsernamePasswordAuthenticationToken(
				user, user.getPassword(), user.getAuthorities());

		// Repassando credenciais ao Spring Security.
		SecurityContextHolder.getContext().setAuthentication(usrPasswdToken);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Usuário obtido via EjbContext repassado ao Spring Security: "
					+ usrPasswdToken.getName());
		}
	}

}
