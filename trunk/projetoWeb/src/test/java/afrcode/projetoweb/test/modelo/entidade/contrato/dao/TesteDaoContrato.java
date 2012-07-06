package afrcode.projetoweb.test.modelo.entidade.contrato.dao;

import org.springframework.beans.factory.annotation.Autowired;

import afrcode.fwarquitetura.modelo.entidade.dao.DaoJpaAbstrato;
import afrcode.fwarquitetura.test.util.dao.TesteDaoObjetoPersistenteAbstrato;
import afrcode.projetoweb.sgos.modelo.entidade.contrato.Contrato;
import afrcode.projetoweb.sgos.modelo.entidade.contrato.dao.DaoContrato;

public class TesteDaoContrato extends TesteDaoObjetoPersistenteAbstrato<Long, Contrato> {
    @Autowired
    private DaoContrato daoContrato;

    @Override
    protected DaoJpaAbstrato<Long, Contrato> getDao() {
        return daoContrato;
    }

}
