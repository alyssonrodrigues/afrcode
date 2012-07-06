package afrcode.projetoweb.test.modelo.entidade.ordemservico.povoador;

import static afrcode.fwarquitetura.tu.spring.config.util.ProfilesTU.PROFILE_TESTES;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import afrcode.fwarquitetura.test.modelo.entidade.povoador.PovoadorAbstrato;
import afrcode.projetoweb.sgos.modelo.entidade.ordemservico.OrdemServico;
import afrcode.projetoweb.sgos.modelo.entidade.ordemservico.dao.DaoOrdemServico;

@Component
@Transactional
@Profile(PROFILE_TESTES)
public class PovoadorOrdemServico extends PovoadorAbstrato {
    static final int NUM_MAX_OBJS_CRIADOS = 10;

    @Autowired
    private DaoOrdemServico daoOrdemServico;

    public PovoadorOrdemServico() {
        super(PovoadorOrdemServico.class);
    }

    @Override
    public void povoar() {
        criarOrdensServico();
    }

    private void criarOrdensServico() {
        for (int i = 0; i < NUM_MAX_OBJS_CRIADOS; i++) {
            OrdemServico ordemServico = daoOrdemServico.instaciarObjetoPersistivelParaTestes();
            daoOrdemServico.salvar(ordemServico);
        }
    }

    public static void main(String[] args) {
        new PovoadorOrdemServico().executar();
    }

}
