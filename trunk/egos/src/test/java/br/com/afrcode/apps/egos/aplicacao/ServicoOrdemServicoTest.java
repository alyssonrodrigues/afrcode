package br.com.afrcode.apps.egos.aplicacao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.afrcode.apps.egos.dominio.OrdemServico;
import br.com.afrcode.apps.egos.dominio.dao.DaoOrdemServico;
import br.com.afrcode.apps.egos.spring.config.BeansSpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeansSpringConfig.class)
public class ServicoOrdemServicoTest {
	
	@Autowired
	private ServicoOrdemServico servicoOrdemServico;
	
	@Autowired
	private DaoOrdemServico daoOrdemServico;
	
	@Test
	public void testarRecuperarOrdensServicoEmAtraso() {
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setConcluida(true);
		Calendar dataEntregaPrevistaEmContrato = Calendar.getInstance();
		ordemServico.setDataEntregaEmContrato(
				dataEntregaPrevistaEmContrato);
		ordemServico.setDescricao("Descrição qualquer");
		ordemServico.setValor(BigDecimal.ONE);
		daoOrdemServico.salvar(ordemServico);
		
		dataEntregaPrevistaEmContrato.add(Calendar.DAY_OF_MONTH, -1);
		OrdemServico ordemServicoNaoConcluida = new OrdemServico();
		ordemServicoNaoConcluida.setConcluida(false);
		ordemServicoNaoConcluida.setDataEntregaEmContrato(
				dataEntregaPrevistaEmContrato);
		ordemServicoNaoConcluida.setDescricao("Outra descrição qualquer");
		ordemServicoNaoConcluida.setValor(BigDecimal.ONE);
		daoOrdemServico.salvar(ordemServicoNaoConcluida);
		
		Collection<OrdemServico> ordens = servicoOrdemServico.
				recuperarOrdensServicoEmAtraso(Calendar.getInstance());
		Assert.assertEquals(1, ordens.size());
		OrdemServico ordemRecuperada = ordens.iterator().next();
		Assert.assertEquals(ordemServicoNaoConcluida.getId(), 
				ordemRecuperada.getId());
	}

}
