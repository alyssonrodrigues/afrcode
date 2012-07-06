package afrcode.fwarquitetura.tu.util.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import afrcode.fwarquitetura.tu.spring.TesteSpringTUContext;

/**
 * Classe utilit�ria para agrupar os testes de unidade por bateria de testes.
 * 
 * TODO: Refinar com a evolu��o dos testes unit�rios criados.
 * 
 * @author alyssonfr
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({TesteSpringTUContext.class})
public class BateriaTestesFwArqTU {

}
