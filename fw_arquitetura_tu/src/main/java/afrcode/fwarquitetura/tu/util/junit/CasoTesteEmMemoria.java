package afrcode.fwarquitetura.tu.util.junit;

import static afrcode.fwarquitetura.tu.spring.config.util.ProfilesTU.PROFILE_TESTES;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import afrcode.fwarquitetura.tu.spring.config.SpringTestConfig;
import afrcode.fwarquitetura.tu.util.hsqldb.HSQLDBUtil;

/**
 * Classe base para execução de testes unitários usando do Spring Context.
 * 
 * Classes de teste unitário devem ter esta classe como superclasse, sempre!
 * 
 * Observações: um contexto transacional é provido tendo como comportamento default o
 * rollback ao final de cada método de teste.
 * 
 * @author alyssonfr
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringTestConfig.class})
@ActiveProfiles(PROFILE_TESTES)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public abstract class CasoTesteEmMemoria {

    /**
     * Existindo EJBs 3 no classpath com Interceptors {@link SpringBeanAutowiringInterceptor} um novo contexto Spring será
     * iniciado. Este por sua vez será distinto e independete do contexto Spring inicial de testes. Portanto, faz necessário
     * a configuração do profile Spring a usar. Isto é feito programaticamente por este método.
     */
    @BeforeClass
    public static void iniciarSpringProfilesParaContextosEJB() {
        System.setProperty("spring.profiles.active", PROFILE_TESTES);
    }

    @BeforeClass
    public static void iniciarHSQLDB() {
        HSQLDBUtil.getInstancia().iniciarHSQLDB();
    }

    @AfterClass
    public static void pararHSQLDB() {
        HSQLDBUtil.getInstancia().pararHSQLDB();
    }

    /**
     * Metodo de pre-configuracao para um metodo de TU. Deve ser sobrescrito
     * quando necessario.
     */
    @Before
    public void antesDeExecutarMetodoTU() {

    }

    /**
     * Metodo de configuracao pre-execucao de um metodo de TU.
     */
    @After
    public void aposExecutarMetodoTU() {

    }

}
