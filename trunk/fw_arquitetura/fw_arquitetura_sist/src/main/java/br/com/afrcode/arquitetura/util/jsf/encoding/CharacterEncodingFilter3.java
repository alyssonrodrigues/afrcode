package br.com.afrcode.arquitetura.util.jsf.encoding;

import javax.servlet.annotation.WebFilter;

import org.springframework.web.filter.CharacterEncodingFilter;

@WebFilter(servletNames = { "facesServlet" })
public class CharacterEncodingFilter3 extends CharacterEncodingFilter {

    private static final String ISO_8859_1 = "ISO-8859-1";

    public CharacterEncodingFilter3() {
        setForceEncoding(true);
        setEncoding(ISO_8859_1);
    }

}
