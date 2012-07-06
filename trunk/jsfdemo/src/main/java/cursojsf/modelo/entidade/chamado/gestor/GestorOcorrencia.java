/**
 * 
 */
package cursojsf.modelo.entidade.chamado.gestor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.modelo.util.ContextoStub;
import cursojsf.modelo.entidade.chamado.Chamado;
import cursojsf.modelo.entidade.chamado.Ocorrencia;
import cursojsf.modelo.entidade.chamado.Responsavel;
import cursojsf.modelo.entidade.chamado.dao.DaoChamado;
import cursojsf.modelo.entidade.chamado.dao.DaoOcorrencia;

/**
 * @author alysson
 *
 */
@Service
public class GestorOcorrencia {
	private DaoOcorrencia daoOcorrencia;
	
	private DaoChamado daoChamado;
	
	public Ocorrencia criarESalvarOcorrencia(Chamado chamado, 
			List<Responsavel> responsaveis) {
		Ocorrencia ocorrencia = chamado.isEmAtendimento() ? 
				chamado.getOcorrencia() : daoOcorrencia.criarOcorrencia(chamado);
		ocorrencia.setResponsaveis(responsaveis);
		daoOcorrencia.salvar(ocorrencia, new ContextoStub());
		chamado.setEmAtendimento(true);
		daoChamado.salvar(chamado, new ContextoStub());
		return ocorrencia;
	}

	@Autowired(required = true)
	public void setDaoOcorrencia(DaoOcorrencia daoOcorrencia) {
		this.daoOcorrencia = daoOcorrencia;
	}

	@Autowired(required = true)
	public void setDaoChamado(DaoChamado daoChamado) {
		this.daoChamado = daoChamado;
	}

}
