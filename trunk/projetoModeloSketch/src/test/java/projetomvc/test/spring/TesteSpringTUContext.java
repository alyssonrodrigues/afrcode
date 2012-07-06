package projetomvc.test.spring;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import projetomvc.test.util.CasoTeste;
import projetomvc.test.util.hsqldb.HSQLDBUtil;

public class TesteSpringTUContext extends CasoTeste {
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

    @BeforeClass
    public static void iniciarHSQLDB() {
        HSQLDBUtil.getInstancia().iniciarHSQLDB();
    }

    @AfterClass
    public static void pararHSQLDB() {
        HSQLDBUtil.getInstancia().pararHSQLDB();
    }

	@Test
	public void testarSpringTUContext() {
		assertNotNull("Um EntityManagerFactory deveria ter sido obtido com sucesso!", 
				entityManagerFactory);
	}

}
