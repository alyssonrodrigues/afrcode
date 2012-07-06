/**
 * 
 */
package cursojsf.modelo.tarefas;

import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import curso.modelo.util.ContextoStub;
import cursojsf.modelo.entidade.chamado.Chamado;
import cursojsf.modelo.entidade.chamado.dao.DaoChamado;

/**
 * @author alysson
 *
 */
@Component
@Transactional
public class GeradorChamados extends TimerTask {
	private static final Logger LOG = 
		Logger.getLogger(GeradorChamados.class);

	private DaoChamado daoChamado;

	@Override
	public void run() {
		criarChamados();		
	}

	private void criarChamados() {
		Chamado chamadoA = daoChamado.instanciarObjetoPadrao();
		daoChamado.salvar(chamadoA, new ContextoStub());
		Chamado chamadoB = daoChamado.instanciarObjetoPadrao();
		chamadoB.setUrgente(true);
		daoChamado.salvar(chamadoB, new ContextoStub());
		LOG.info("GeradorChamados.criarChamados: 2 chamados criados.");
	}

	@Required
	public void setDaoChamado(DaoChamado daoChamado) {
		this.daoChamado = daoChamado;
	}

}
