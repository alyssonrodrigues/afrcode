package afrcode.projetoweb.test.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import afrcode.projetoweb.test.modelo.entidade.contrato.dao.TesteDaoContrato;
import afrcode.projetoweb.test.modelo.entidade.ordemservico.dao.TesteDaoOrdemServico;
import afrcode.projetoweb.test.modelo.entidade.ordemservico.povoador.TestePovoadorOrdemServico;

@RunWith(Suite.class)
@SuiteClasses({TesteDaoContrato.class, TesteDaoOrdemServico.class, TestePovoadorOrdemServico.class})
public class BateriaTestesProjetoWeb {

}
