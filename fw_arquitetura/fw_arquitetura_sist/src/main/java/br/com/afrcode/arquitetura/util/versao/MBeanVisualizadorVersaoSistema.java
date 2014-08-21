package br.com.afrcode.arquitetura.util.versao;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.Manifest;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.context.annotation.Profile;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;
import br.com.afrcode.arquitetura.util.excecao.ExcecaoNaoPrevista;

@ManagedBean
@ViewScoped
@Profile(Profiles.PROFILE_APLICACAO)
public class MBeanVisualizadorVersaoSistema implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<Object, Object> props = new HashMap<Object, Object>();

    public Map<Object, Object> getProps() {
        return props;
    }

    @PostConstruct
    public void iniciar() {
        WebApplicationContext webCtx =
                FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance());
        ServletContext servletContext = webCtx.getServletContext();
        try {
            final String MANIFEST = "/META-INF/MANIFEST.MF";
            InputStream is = servletContext.getResourceAsStream(MANIFEST);
            Manifest manifest = new Manifest(is);
            for (Entry<Object, Object> entry : manifest.getMainAttributes().entrySet()) {
                Object prop = entry.getKey();
                Object valores = entry.getValue();
                props.put(prop, valores);
            }
        } catch (IOException e) {
            throw new ExcecaoNaoPrevista(e);
        }
    }

}
