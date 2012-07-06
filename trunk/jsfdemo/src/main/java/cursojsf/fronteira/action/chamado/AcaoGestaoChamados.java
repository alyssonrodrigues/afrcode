/**
 * 
 */
package cursojsf.fronteira.action.chamado;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;

import cursojsf.fronteira.action.Acao;
import cursojsf.modelo.entidade.chamado.Chamado;
import cursojsf.modelo.entidade.chamado.Responsavel;
import cursojsf.modelo.entidade.chamado.dao.DaoChamado;
import cursojsf.modelo.entidade.chamado.dao.DaoResponsavel;
import cursojsf.modelo.entidade.chamado.gestor.GestorOcorrencia;

/**
 * @author alysson
 * 
 */
@Name("acaoGestaoChamados")
@Scope(ScopeType.CONVERSATION)
public class AcaoGestaoChamados extends Acao {
	@In(value = "#{daoChamado}", required = true)
	private DaoChamado daoChamado;

	@In(value = "#{daoResponsavel}", required = true)
	private DaoResponsavel daoResponsavel;

	@In(value = "#{gestorOcorrencia}", required = true)
	private GestorOcorrencia gestorOcorrencia;

	private Long idAAtender;

	private Chamado chamadoAAtender;
	
	private List<Responsavel> responsaveisAAssociar;

	@Begin(join = true)
	public void atender() {
		chamadoAAtender = daoChamado.procurar(idAAtender);
	}

	@End
	public void salvar() {
		gestorOcorrencia.criarESalvarOcorrencia(chamadoAAtender, 
				getResponsaveisAAssociar());
		chamadoAAtender = null;
		idAAtender = null;
		responsaveisAAssociar = null;
	}

	@End
	public void cancelar() {
		chamadoAAtender = null;
		idAAtender = null;
		responsaveisAAssociar = null;
	}

	@DataModel
	public Collection<Chamado> getChamados() {
		return daoChamado.recuperarTodos();
	}

	public List<Responsavel> getResponsaveis() {
		List<Responsavel> responsaveis = new ArrayList<Responsavel>(
				daoResponsavel.recuperarTodos());
		return responsaveis;
	}
	
	public List<Responsavel> getResponsaveisAAssociar() {
		if ((responsaveisAAssociar == null || responsaveisAAssociar.isEmpty()) && 
				chamadoAAtender != null && chamadoAAtender.getOcorrencia() != null) {
			responsaveisAAssociar = new ArrayList<Responsavel>( 
				chamadoAAtender.getOcorrencia().getResponsaveis());
		}
		return responsaveisAAssociar;
	}
	
	public void setResponsaveisAAssociar(List<Responsavel> responsaveisAAssociar) {
		this.responsaveisAAssociar = responsaveisAAssociar;
	}

	public Long getIdAAtender() {
		return idAAtender;
	}

	public void setIdAAtender(Long idAAtender) {
		this.idAAtender = idAAtender;
	}
}
