package afrcode.fwarquitetura.tu.spring;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import afrcode.fwarquitetura.tu.util.junit.CasoTesteSemTransacao;

/**
 * Classe de testes simples sem contexto transacional envolvido. Em uso para validar o bootstrapping do Spring, ou seja, carga
 * de classes @Configuration e diretivas associadas.
 * 
 * @author alyssonfr
 * 
 */
public class TesteSpringTUContext extends CasoTesteSemTransacao {

    @Test
    public void testarSpringTUContext() {
        try {
            assertTrue("Spring Bootstrap OKay!", true);
        } catch (Throwable e) {
            fail("Erro ao iniciar contexto Spring!");
        }
    }

}
