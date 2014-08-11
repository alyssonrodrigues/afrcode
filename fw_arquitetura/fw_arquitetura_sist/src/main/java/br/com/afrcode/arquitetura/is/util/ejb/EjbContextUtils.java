package br.com.afrcode.arquitetura.is.util.ejb;

import java.security.Principal;

import javax.ejb.EJBContext;

import org.apache.log4j.Logger;

public class EjbContextUtils {
    private static final String USUARIO_NAO_AUTENTICADO = "SEGURANCA";
    private static final Logger LOG = Logger.getLogger(EjbContextUtils.class);

    public static String obterCredencial(EJBContext ejbContext) {
        Principal callerPrincipal = ejbContext.getCallerPrincipal();
        String usuario = null;

        if (callerPrincipal != null) {
            String name = callerPrincipal.getName();
            if (name != null && !"anonymous".equalsIgnoreCase(name) && !"guest".equalsIgnoreCase(name)
                    && !"Unauthenticated".equalsIgnoreCase(name)) {
                usuario = name;
            }
        }

        if (usuario == null) {
            usuario = USUARIO_NAO_AUTENTICADO;
            LOG.warn("Usu�rio n�o autenticado, poss�vel acesso a recurso que n�o exige autentica��o, ex.: p�gina de login!");
        }

        return usuario;
    }

}
