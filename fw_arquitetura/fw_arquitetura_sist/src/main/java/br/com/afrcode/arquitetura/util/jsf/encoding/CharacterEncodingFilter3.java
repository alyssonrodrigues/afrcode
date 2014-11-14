package br.com.afrcode.arquitetura.util.jsf.encoding;

import javax.servlet.annotation.WebFilter;

import org.springframework.web.filter.CharacterEncodingFilter;

@WebFilter(servletNames = { "facesServlet" })
public class CharacterEncodingFilter3 extends CharacterEncodingFilter {

	private static final String UTF_8 = "UTF-8";

	public CharacterEncodingFilter3() {
		setForceEncoding(true);
		setEncoding(UTF_8);
	}

}
