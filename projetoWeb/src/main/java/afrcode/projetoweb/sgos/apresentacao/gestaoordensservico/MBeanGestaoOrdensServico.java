package afrcode.projetoweb.sgos.apresentacao.gestaoordensservico;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import afrcode.fwarquitetura.apresentacao.managedbean.ManagedBeanAbstrato;
import afrcode.fwarquitetura.util.mensagem.alerta.MensagemAlertaNegocio;
import afrcode.fwarquitetura.util.mensagem.info.MensagemInfoNegocio;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLAction.PhaseId;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

/**
 * <b>ATENÇÃO</b>
 * 
 * CLASSE PARA TESTE DE IMPLEMENTAÇÃO DE CRUD.
 * 
 * NÃO HÁ COMPROMISSO DE ADERÊNCIA TOTAL AS DEFINIÇÕES PRÉVIAS DA ARQUITETURA POR SE TRATAR DE UM TESTE DE ADEQUAÇÃO E USO!
 * 
 * TODO: Não há contexto transacional neste componente! O contexto transacional inicia-se na {@link CtlGestaoOrdensServico}.
 * Se usassemos o Seam teriamos o contexto transacional já neste componente implicitamente. Por não ser possível usar é necessário
 * implementar caso necessário.
 * 
 * @author alyssonfr
 * 
 */
@ManagedBean
@SessionScoped
@URLMappings(mappings = {
        @URLMapping(id = "gestaoordensservico.telaGestaoOrdensServico", pattern = "/gestaoordensservico/telaGestaoOrdensServico/",
                viewId = "/Web/pages/gestaoordensservico/telaGestaoOrdensServico.xhtml"),
        @URLMapping(id = "gestaoordensservico.telaEdicaoOrdemServico.edicao",
                pattern = "/gestaoordensservico/telaEdicaoOrdemServico/#{mBeanGestaoOrdensServico.idEmEdicao}/",
                viewId = "/Web/pages/gestaoordensservico/telaEdicaoOrdemServico.xhtml"),
        @URLMapping(id = "gestaoordensservico.telaEdicaoOrdemServico.insercao",
                pattern = "/gestaoordensservico/telaEdicaoOrdemServico/",
                viewId = "/Web/pages/gestaoordensservico/telaEdicaoOrdemServico.xhtml")})
public class MBeanGestaoOrdensServico extends ManagedBeanAbstrato {

    private static final String TELA_EDICAO_ORDEM_SERVICO_EDICAO_VIEW_ID =
            "pretty:gestaoordensservico.telaEdicaoOrdemServico.edicao";

    private static final String TELA_EDICAO_ORDEM_SERVICO_INSERCAO_VIEW_ID =
            "pretty:gestaoordensservico.telaEdicaoOrdemServico.insercao";

    private static final String TELA_GESTAO_ORDENS_SERVICO_VIEW_ID =
            "pretty:gestaoordensservico.telaGestaoOrdensServico";

    private static final Logger LOG = Logger.getLogger(MBeanGestaoOrdensServico.class);

    @ManagedProperty(value = "#{ctlGestaoOrdensServico}")
    private CtlGestaoOrdensServico ctlGestaoOrdensServico;

    private Collection<BeanOrdemServico> beansOrdensServicoAListar;

    private BeanOrdemServico beanOrdemServicoEmEdicao;

    private Long idEmEdicao;

    public MBeanGestaoOrdensServico() {
        LOG.info("Criando MBeanGestaoOrdensServico...");
    }

    /**
     * Método acionado pela view {@link #TELA_GESTAO_ORDENS_SERVICO_VIEW_ID} para listagem de OSs cadastradas.
     */
    @URLAction(mappingId = "gestaoordensservico.telaGestaoOrdensServico", phaseId = PhaseId.RENDER_RESPONSE)
    public void listar() {
        LOG.info("Recuperando listagem de ordens de serviço...");
        beansOrdensServicoAListar = new ArrayList<BeanOrdemServico>();
        beansOrdensServicoAListar.addAll(ctlGestaoOrdensServico.recuperarTodos());
    }

    /**
     * Método acionado pela view {@link #TELA_EDICAO_ORDEM_SERVICO_EDICAO_VIEW_ID} durante o
     * PRIMEIRO acesso (RESTORE_VIEW e postback false) a esta view.
     */
    @URLAction(mappingId = "gestaoordensservico.telaEdicaoOrdemServico.edicao", phaseId = PhaseId.RESTORE_VIEW,
            onPostback = false)
    public void recuperarBeanOrdemServicoEmEdicao() {
        LOG.info("Recuperando ordem de serviço em edição...");
        MensagemInfoNegocio msg = new MensagemInfoNegocio();
        msg.setCodMensagem("telaEdicaoOrdemServico.edicao");
        msg.setMensagem("Informação: você está prestes a alterar uma Ordem de Serviço e será responsável por esta alteração!");
        adicionarMensagem(msg);
        if (idEmEdicao == null && beanOrdemServicoEmEdicao == null) {
            beanOrdemServicoEmEdicao = new BeanOrdemServico();
        } else if (idEmEdicao != null && beanOrdemServicoEmEdicao == null) {
            beanOrdemServicoEmEdicao = ctlGestaoOrdensServico.recuperarOrdemServicoEmEdicao(idEmEdicao);
        } else if (idEmEdicao != null && beanOrdemServicoEmEdicao != null && !idEmEdicao.equals(beanOrdemServicoEmEdicao.getId())) {
            // Se há idEmEdicao e este é diferente do id do beanOrdemServicoEmEdicao temos um acesso direto via URL Rest-ful
            // e por isto é necessário atualizar o beanOrdemServicoEmEdicao.
            beanOrdemServicoEmEdicao = ctlGestaoOrdensServico.recuperarOrdemServicoEmEdicao(idEmEdicao);
        }
    }

    /**
     * Método acionado pela view {@link #TELA_EDICAO_ORDEM_SERVICO_INSERCAO_VIEW_ID} durante o
     * PRIMEIRO acesso (RESTORE_VIEW e postback false) a esta view.
     */
    @URLAction(mappingId = "gestaoordensservico.telaEdicaoOrdemServico.insercao", phaseId = PhaseId.RESTORE_VIEW,
            onPostback = false)
    public void recuperarBeanOrdemServicoEmInsercao() {
        LOG.info("Criando bean ordem de serviço p/ inserção...");
        MensagemAlertaNegocio msg = new MensagemAlertaNegocio();
        msg.setCodMensagem("telaEdicaoOrdemServico.insercao");
        msg.setMensagem("Alerta: você está prestes a criar uma Ordem de Serviço e será responsável pela mesma!");
        adicionarMensagem(msg);
        if (idEmEdicao == null && beanOrdemServicoEmEdicao == null) {
            beanOrdemServicoEmEdicao = new BeanOrdemServico();
        }
    }

    public String alterar() {
        return TELA_EDICAO_ORDEM_SERVICO_EDICAO_VIEW_ID;
    }

    public String inserir() {
        idEmEdicao = null;
        return TELA_EDICAO_ORDEM_SERVICO_INSERCAO_VIEW_ID;
    }

    public String cancelar() {
        beanOrdemServicoEmEdicao = null;
        idEmEdicao = null;
        return TELA_GESTAO_ORDENS_SERVICO_VIEW_ID;
    }

    public String salvar() {
        if (idEmEdicao != null) {
            ctlGestaoOrdensServico.alterarOrdemServico(beanOrdemServicoEmEdicao, idEmEdicao);
        }
        else {
            ctlGestaoOrdensServico.criarOrdemServico(beanOrdemServicoEmEdicao);
        }
        beanOrdemServicoEmEdicao = null;
        idEmEdicao = null;

        return TELA_GESTAO_ORDENS_SERVICO_VIEW_ID;
    }

    public void excluir() {
        ctlGestaoOrdensServico.excluirOrdemServico(idEmEdicao);
        beanOrdemServicoEmEdicao = null;
        idEmEdicao = null;
    }

    /**
     * @return the ordensServicoAListar
     */
    public Collection<BeanOrdemServico> getBeansOrdensServicoAListar() {
        return beansOrdensServicoAListar;
    }

    /**
     * @param ordensServicoAListar the ordensServicoAListar to set
     */
    public void setBeansOrdensServicoAListar(Collection<BeanOrdemServico> ordensServicoAListar) {
        this.beansOrdensServicoAListar = ordensServicoAListar;
    }

    /**
     * @return the ordemServicoAGerir
     */
    public BeanOrdemServico getBeanOrdemServicoEmEdicao() {
        return beanOrdemServicoEmEdicao;
    }

    /**
     * @param beanOrdemServicoEmEdicao the beanOrdemServicoEmEdicao to set
     */
    public void setBeanOrdemServicoEmEdicao(BeanOrdemServico beanOrdemServicoEmEdicao) {
        this.beanOrdemServicoEmEdicao = beanOrdemServicoEmEdicao;
    }

    /**
     * @return the idAGerir
     */
    public Long getIdEmEdicao() {
        return idEmEdicao;
    }

    /**
     * @param idAGerir the idAGerir to set
     */
    public void setIdEmEdicao(Long idAGerir) {
        this.idEmEdicao = idAGerir;
    }

    /**
     * @return the ctlGestaoOrdensServico
     */
    public CtlGestaoOrdensServico getCtlGestaoOrdensServico() {
        return ctlGestaoOrdensServico;
    }

    /**
     * @param ctlGestaoOrdensServico the ctlGestaoOrdensServico to set
     */
    public void setCtlGestaoOrdensServico(CtlGestaoOrdensServico ctlGestaoOrdensServico) {
        this.ctlGestaoOrdensServico = ctlGestaoOrdensServico;
    }

}
