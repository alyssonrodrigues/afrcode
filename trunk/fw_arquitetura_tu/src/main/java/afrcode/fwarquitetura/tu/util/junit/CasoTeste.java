package afrcode.fwarquitetura.tu.util.junit;

import static afrcode.fwarquitetura.tu.spring.config.util.ProfilesTU.PROFILE_TESTES;

import org.junit.After;
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

/**
 * Classe base para execu��o de testes unit�rios usando do Spring Context.
 * 
 * Classes de teste unit�rio devem ter esta classe como superclasse, sempre!
 * 
 * Observa��es: um contexto transacional � provido tendo como comportamento default o
 * rollback ao final de cada m�todo de teste.
 * 
 * @author alyssonfr
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringTestConfig.class})
@ActiveProfiles(PROFILE_TESTES)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public abstract class CasoTeste {

    /**
     * Existindo EJBs 3 no classpath com Interceptors {@link SpringBeanAutowiringInterceptor} um novo contexto Spring ser�
     * iniciado. Este por sua vez ser� distinto e independete do contexto Spring inicial de testes. Portanto, faz necess�rio
     * a configura��o do profile Spring a usar. Isto � feito programaticamente por este m�todo.
     */
    @BeforeClass
    public static void iniciarSpringProfilesParaContextosEJB() {
        System.setProperty("spring.profiles.active", PROFILE_TESTES);
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
