package br.com.afrcode.arquitetura.teste.unitario.util.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import br.com.afrcode.arquitetura.teste.unitario.spring.config.SpringTestConfig;
import br.com.afrcode.arquitetura.teste.unitario.spring.config.util.ProfilesTU;

/**
 * Classe base para execu��o de testes unit�rios usando do Spring Context SEM
 * contexto transacional e de persist�ncia.
 * 
 * Observa��o: S� deve ser usada para testes que n�o envolvam transa��es (sejam
 * via JPA ou JTA).
 * 
 * Subclasses de AbstractCasoTesteSemJpaEJta ter�o como Profile Spring ativo
 * (ActiveProfiles) o PROFILE_TU, onde apenas classes Configuration e/ou classes
 * Component, e subtipos de Component, com Profile PROFILE_TU ser�o avaliadas em
 * classpath pelo Spring.
 * 
 * Classes de teste unit�rio de servi�os remotos <b>devem</b> usar esta classe
 * como superclasse, sempre! Servi�os remotos s�o fachadas de acesso a servi�os
 * j� existens e por isto far�o uso dos servi�os de persist�ncia locais aos
 * servi�os locais e <b>n�o far�o uso dos servi�os de persist�ncia por si
 * s�.</b>.
 * 
 * 
 */
@RunWith(SpringJUnit4ClassRunnerExtended.class)
@ContextConfiguration(classes = { SpringTestConfig.class })
@ActiveProfiles(ProfilesTU.PROFILE_TU)
public abstract class AbstractCasoTesteSemJpaEJta {

    /**
     * Existindo EJBs 3 no classpath com Interceptors
     * SpringBeanAutowiringInterceptor um novo contexto Spring ser� iniciado.
     * Este por sua vez ser� distinto e independete do contexto Spring inicial
     * de testes. Portanto, faz necess�rio a configura��o do profile Spring a
     * usar. Isto � feito programaticamente por este m�todo.
     */
    @BeforeClass
    public static void iniciarSpringProfilesParaContextosEJB() {
        System.setProperty("spring.profiles.active", ProfilesTU.PROFILE_TU);
        // Para evitar que o OpenEJB reconfigure o log4j conflitando com threads
        // j� em execu��o (Spring Threads, por exemplo).
        System.setProperty("openejb.logger.external", "true");
    }

    /**
     * Metodo de pre-configuracao para um metodo de TU. Deve ser sobrescrito
     * quando necessario.
     */
    @Before
    public void antesDeExecutarMetodoTU() {
        // Pr�-condi��es de execu��o de m�todos de TU.
    }

    /**
     * Metodo de configuracao pre-execucao de um metodo de TU.
     */
    @After
    public void aposExecutarMetodoTU() {
        // P�s-condi��es de execu��o de m�todos de TU.
    }

}
