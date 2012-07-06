package afrcode.projetoweb.sgos.apresentacao.gestaoordensservico;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import afrcode.fwarquitetura.spring.anotacoes.Controlador;
import afrcode.projetoweb.sgos.modelo.entidade.ordemservico.OrdemServico;
import afrcode.projetoweb.sgos.modelo.entidade.ordemservico.dao.DaoOrdemServico;

@Controlador
@Secured("ROLE_USER")
public class CtlGestaoOrdensServico {

    @Autowired
    private DaoOrdemServico daoOrdemServico;

    public void criarOrdemServico(BeanOrdemServico beanOrdemServicoEmEdicao) {
        // TODO: um contrato padrão está sendo criado pelo DAO. Reimplementar com seleção de contrato para OS.
        OrdemServico ordemServicoEmEdicao = daoOrdemServico.instaciarObjetoPersistivelParaTestes();
        ordemServicoEmEdicao.setNumero(beanOrdemServicoEmEdicao.getNumero());
        ordemServicoEmEdicao.setDescricao(beanOrdemServicoEmEdicao.getDescricao());
        daoOrdemServico.salvar(ordemServicoEmEdicao);
    }

    public void alterarOrdemServico(BeanOrdemServico beanOrdemServicoEmEdicao, Long idEmEdicao) {
        Validate.notNull(idEmEdicao, "Deveria ter sido informado um id da Ordem de Serviço em edição!");
        OrdemServico ordemServicoEmEdicao = daoOrdemServico.procurarPorId(idEmEdicao);
        ordemServicoEmEdicao.setNumero(beanOrdemServicoEmEdicao.getNumero());
        ordemServicoEmEdicao.setDescricao(beanOrdemServicoEmEdicao.getDescricao());
        daoOrdemServico.salvar(ordemServicoEmEdicao);
    }

    public Collection<BeanOrdemServico> recuperarTodos() {
        Collection<OrdemServico> ordensServico = daoOrdemServico.recuperarTodos();
        Collection<BeanOrdemServico> beansOrdensServicoAListar = new ArrayList<BeanOrdemServico>();
        // TODO: Código necessário por não termos extended persistence contexts! :( Rever em função de uso de BKBs e/ou DTOS!
        for (OrdemServico ordem : ordensServico) {
            // Swap de dados ... :(
            BeanOrdemServico beanOrdemServico = new BeanOrdemServico();
            beanOrdemServico.setNumero(ordem.getNumero());
            beanOrdemServico.setDescricao(ordem.getDescricao());
            beanOrdemServico.setId(ordem.getId());
            beansOrdensServicoAListar.add(beanOrdemServico);
        }
        return beansOrdensServicoAListar;
    }

    public BeanOrdemServico recuperarOrdemServicoEmEdicao(Long idEmEdicao) {
        Validate.notNull(idEmEdicao, "Deveria ter sido informado um id da Ordem de Serviço em edição!");
        // TODO: Código necessário por não termos extended persistence contexts! :( Rever em função de uso de BKBs e/ou DTOS!
        BeanOrdemServico beanOrdemServicoEmEdicao = new BeanOrdemServico();
        OrdemServico ordem = daoOrdemServico.procurarPorId(idEmEdicao);
        // Swap de dados ... :(
        beanOrdemServicoEmEdicao.setId(ordem.getId());
        beanOrdemServicoEmEdicao.setNumero(ordem.getNumero());
        beanOrdemServicoEmEdicao.setDescricao(ordem.getDescricao());
        return beanOrdemServicoEmEdicao;
    }

    public void excluirOrdemServico(Long idAExcluir) {
        OrdemServico ordemServico = daoOrdemServico.procurarPorId(idAExcluir);
        daoOrdemServico.excluir(ordemServico);
    }

}
