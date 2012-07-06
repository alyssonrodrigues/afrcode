package afrcode.fwarquitetura.test.util.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import afrcode.fwarquitetura.test.modelo.entidade.objetopersistente.dao.TesteDaoUmObjetoPersistente;
import afrcode.fwarquitetura.test.modelo.entidade.objetopersistente.povoador.TestePovoadorUmObjetoPersistente;
import afrcode.fwarquitetura.test.spring.TesteSpringTestContext;

/**
 * Classe utilit�ria para agrupar os testes de unidade por bateria de testes.
 * 
 * TODO: Refinar com a evolu��o dos testes unit�rios criados.
 * 
 * @author alyssonfr
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({TesteSpringTestContext.class, TesteDaoUmObjetoPersistente.class, TestePovoadorUmObjetoPersistente.class})
public class BateriaTestesFwArqRef {

}
