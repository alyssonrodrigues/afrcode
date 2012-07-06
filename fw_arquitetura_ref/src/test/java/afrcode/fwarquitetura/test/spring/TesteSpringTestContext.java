package afrcode.fwarquitetura.test.spring;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.junit.Test;

import afrcode.fwarquitetura.test.spring.config.jpa.EntityManagerFactoryTUConfig;
import afrcode.fwarquitetura.tu.util.junit.CasoTesteEmMemoria;

/**
 * Teste unitário de startup do contexto Spring específico para o ambiente de
 * desenvolvimento.
 * 
 * Objetivo: validar o startup do contexto Spring através de DI para o
 * EntityManagerFactory definido em {@link EntityManagerFactoryTUConfig}.
 * 
 * Observações: faz uso de SGBD em memória, o HSQLDB.
 * 
 * @author alyssonfr
 * 
 */
public class TesteSpringTestContext extends CasoTesteEmMemoria {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void testarSpringTestContext() {
        assertNotNull("Um EntityManagerFactory deveria ter sido obtido com sucesso!",
                entityManagerFactory);
    }

}
