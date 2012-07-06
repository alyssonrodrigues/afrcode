package afrcode.fwarquitetura.test.modelo.entidade.objetopersistente.povoador;

import static afrcode.fwarquitetura.spring.config.util.Profiles.PROFILE_TESTES;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import afrcode.fwarquitetura.test.modelo.entidade.objetopersistente.UmObjetoPersistente;
import afrcode.fwarquitetura.test.modelo.entidade.objetopersistente.dao.DaoUmObjetoPersistente;
import afrcode.fwarquitetura.test.modelo.entidade.povoador.PovoadorAbstrato;

@Component
@Transactional
@Profile(PROFILE_TESTES)
public class PovoadorUmObjetoPersistente extends PovoadorAbstrato {
    static final int NUM_MAX_OBJS_CRIADOS = 10;

    @Autowired
    private DaoUmObjetoPersistente daoUmObjetoPersistente;

    public PovoadorUmObjetoPersistente() {
        super(PovoadorUmObjetoPersistente.class);
    }

    @Override
    public void povoar() {
        criarObjetosPersistentes();
    }

    private void criarObjetosPersistentes() {
        for (int i = 0; i < NUM_MAX_OBJS_CRIADOS; i++) {
            UmObjetoPersistente umObjetoPersistente = daoUmObjetoPersistente.instaciarObjetoPersistivelParaTestes();
            daoUmObjetoPersistente.salvar(umObjetoPersistente);
        }
    }

    public static void main(String[] args) {
        new PovoadorUmObjetoPersistente().executar();
    }

}
