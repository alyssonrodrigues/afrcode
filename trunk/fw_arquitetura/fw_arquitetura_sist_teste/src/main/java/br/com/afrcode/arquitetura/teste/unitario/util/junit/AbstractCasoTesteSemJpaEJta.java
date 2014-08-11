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
 * Classe base para execução de testes unitários usando do Spring Context SEM
 * contexto transacional e de persistência.
 * 
 * Observação: Só deve ser usada para testes que não envolvam transações (sejam
 * via JPA ou JTA).
 * 
 * Subclasses de AbstractCasoTesteSemJpaEJta terão como Profile Spring ativo
 * (ActiveProfiles) o PROFILE_TU, onde apenas classes Configuration e/ou classes
 * Component, e subtipos de Component, com Profile PROFILE_TU serão avaliadas em
 * classpath pelo Spring.
 * 
 * Classes de teste unitário de serviços remotos <b>devem</b> usar esta classe
 * como superclasse, sempre! Serviços remotos são fachadas de acesso a serviços
 * já existens e por isto farão uso dos serviços de persistência locais aos
 * serviços locais e <b>não farão uso dos serviços de persistência por si
 * só.</b>.
 * 
 * 
 */
@RunWith(SpringJUnit4ClassRunnerExtended.class)
@ContextConfiguration(classes = { SpringTestConfig.class })
@ActiveProfiles(ProfilesTU.PROFILE_TU)
public abstract class AbstractCasoTesteSemJpaEJta {

    /**
     * Existindo EJBs 3 no classpath com Interceptors
     * SpringBeanAutowiringInterceptor um novo contexto Spring será iniciado.
     * Este por sua vez será distinto e independete do contexto Spring inicial
     * de testes. Portanto, faz necessário a configuração do profile Spring a
     * usar. Isto é feito programaticamente por este método.
     */
    @BeforeClass
    public static void iniciarSpringProfilesParaContextosEJB() {
        System.setProperty("spring.profiles.active", ProfilesTU.PROFILE_TU);
        // Para evitar que o OpenEJB reconfigure o log4j conflitando com threads
        // já em execução (Spring Threads, por exemplo).
        System.setProperty("openejb.logger.external", "true");
    }

    /**
     * Metodo de pre-configuracao para um metodo de TU. Deve ser sobrescrito
     * quando necessario.
     */
    @Before
    public void antesDeExecutarMetodoTU() {
        // Pré-condições de execução de métodos de TU.
    }

    /**
     * Metodo de configuracao pre-execucao de um metodo de TU.
     */
    @After
    public void aposExecutarMetodoTU() {
        // Pós-condições de execução de métodos de TU.
    }

}
