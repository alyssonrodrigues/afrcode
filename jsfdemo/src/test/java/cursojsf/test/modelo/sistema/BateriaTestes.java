/**
 * 
 */
package cursojsf.test.modelo.sistema;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cursojsf.test.modelo.entidade.chamado.dao.TesteDaoChamado;
import cursojsf.test.modelo.entidade.chamado.dao.TesteDaoOcorrencia;
import cursojsf.test.modelo.entidade.chamado.dao.TesteDaoResponsavel;
import cursojsf.test.modelo.entidade.dao.TesteDaoEntidadeStub;
import cursojsf.test.modelo.entidade.usuario.dao.TesteDaoPapel;
import cursojsf.test.modelo.entidade.usuario.dao.TesteDaoUsuario;


/**
 * @author alysson
 *
 */
@RunWith(Suite.class)
@SuiteClasses({TesteDaoEntidadeStub.class, TesteDaoUsuario.class,
	TesteDaoPapel.class, TesteDaoChamado.class,
	TesteDaoOcorrencia.class, TesteDaoResponsavel.class})
public class BateriaTestes {

}
