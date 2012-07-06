package afrcode.fwarquitetura.test.spring;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.junit.Test;

import afrcode.fwarquitetura.test.spring.config.jpa.EntityManagerFactoryTUConfig;
import afrcode.fwarquitetura.tu.util.junit.CasoTesteEmMemoria;

/**
 * Teste unit�rio de startup do contexto Spring espec�fico para o ambiente de
 * desenvolvimento.
 * 
 * Objetivo: validar o startup do contexto Spring atrav�s de DI para o
 * EntityManagerFactory definido em {@link EntityManagerFactoryTUConfig}.
 * 
 * Observa��es: faz uso de SGBD em mem�ria, o HSQLDB.
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
