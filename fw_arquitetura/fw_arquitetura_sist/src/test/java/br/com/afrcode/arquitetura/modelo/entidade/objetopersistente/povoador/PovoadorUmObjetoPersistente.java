package br.com.afrcode.arquitetura.modelo.entidade.objetopersistente.povoador;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.afrcode.arquitetura.modelo.entidade.objetopersistente.UmObjetoPersistente;
import br.com.afrcode.arquitetura.modelo.entidade.objetopersistente.dao.DaoUmObjetoPersistente;
import br.com.afrcode.arquitetura.modelo.entidade.povoador.PovoadorAbstrato;
import br.com.afrcode.arquitetura.spring.anotacoes.Povoador;

@Povoador
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
            UmObjetoPersistente umObjetoPersistente = daoUmObjetoPersistente.instanciarObjetoPersistivel();
            daoUmObjetoPersistente.salvar(umObjetoPersistente);
        }
    }

    public static void main(String[] args) {
        new PovoadorUmObjetoPersistente().executar();
    }

}
