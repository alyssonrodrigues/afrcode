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

import afrcode.fwarquitetura.tu.spring.config.SpringTestConfig;

/**
 * Classe base para execu��o de testes unit�rios usando do Spring Context SEM contexto transacional.
 * 
 * Observa��o: S� deve ser usada para testes que n�o envolvam transa��es (sejam via JPA ou JTA).
 * 
 * @author alyssonfr
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringTestConfig.class})
@ActiveProfiles(PROFILE_TESTES)
public abstract class CasoTesteSemTransacao {

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
