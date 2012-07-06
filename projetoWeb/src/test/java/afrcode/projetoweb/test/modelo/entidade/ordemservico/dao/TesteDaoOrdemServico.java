package afrcode.projetoweb.test.modelo.entidade.ordemservico.dao;

import org.springframework.beans.factory.annotation.Autowired;

import afrcode.fwarquitetura.modelo.entidade.dao.DaoJpaAbstrato;
import afrcode.fwarquitetura.test.util.dao.TesteDaoObjetoPersistenteAbstrato;
import afrcode.projetoweb.sgos.modelo.entidade.ordemservico.OrdemServico;
import afrcode.projetoweb.sgos.modelo.entidade.ordemservico.dao.DaoOrdemServico;

public class TesteDaoOrdemServico extends TesteDaoObjetoPersistenteAbstrato<Long, OrdemServico> {
    @Autowired
    private DaoOrdemServico daoOrdemServico;

    @Override
    protected DaoJpaAbstrato<Long, OrdemServico> getDao() {
        return daoOrdemServico;
    }

}
