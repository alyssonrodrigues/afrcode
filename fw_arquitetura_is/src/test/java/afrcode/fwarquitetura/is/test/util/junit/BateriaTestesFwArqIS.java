package afrcode.fwarquitetura.is.test.util.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.TesteUmObjetoEmMemoriaBeanRemote;
import afrcode.fwarquitetura.is.test.modelo.rmi.objetoemmemoria.client.TesteUmObjetoEmMemoriaRmiClient;
import afrcode.fwarquitetura.is.test.spring.jms.TesteJMS;

/**
 * Classe utilitária para agrupar os testes de unidade por bateria de testes.
 * 
 * TODO: Refinar com a evolução dos testes unitários criados.
 * 
 * @author alyssonfr
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({TesteJMS.class, TesteUmObjetoEmMemoriaRmiClient.class, TesteUmObjetoEmMemoriaBeanRemote.class})
public class BateriaTestesFwArqIS {

}
