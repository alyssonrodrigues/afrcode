package afrcode.projetoweb.test.modelo.entidade.ordemservico.povoador;

import static afrcode.projetoweb.test.modelo.entidade.ordemservico.povoador.PovoadorOrdemServico.NUM_MAX_OBJS_CRIADOS;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import afrcode.fwarquitetura.tu.util.junit.CasoTesteEmMemoria;
import afrcode.projetoweb.sgos.modelo.entidade.ordemservico.OrdemServico;
import afrcode.projetoweb.sgos.modelo.entidade.ordemservico.dao.DaoOrdemServico;

public class TestePovoadorOrdemServico extends CasoTesteEmMemoria {
    @Autowired
    private PovoadorOrdemServico povoadorOrdemServico;

    @Autowired
    private DaoOrdemServico daoOrdemServico;

    @Test
    public void testarPovoadorOrdemServico() {
        povoadorOrdemServico.povoar();
        Collection<OrdemServico> objs = daoOrdemServico.recuperarTodos();
        assertEquals("Deveria ter sido criadas " + NUM_MAX_OBJS_CRIADOS + " ordens de serviço!", NUM_MAX_OBJS_CRIADOS,
                objs.size());
    }

}
