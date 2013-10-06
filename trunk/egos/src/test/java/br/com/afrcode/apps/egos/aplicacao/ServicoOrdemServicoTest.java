package br.com.afrcode.apps.egos.aplicacao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.afrcode.apps.egos.dominio.OrdemServico;
import br.com.afrcode.apps.egos.dominio.dao.DaoOrdemServico;
import br.com.afrcode.apps.egos.spring.config.BeansSpringTestesConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeansSpringTestesConfig.class)
@ActiveProfiles("TESTES")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ServicoOrdemServicoTest {
	
	@Autowired
	private ServicoOrdemServico servicoOrdemServico;
	
	@Autowired
	private DaoOrdemServico daoOrdemServico;
	
	@Test
	public void testarRecuperarOrdensServicoEmAtraso() {
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setConcluida(true);
		Date dataEntregaPrevistaEmContrato = 
				Calendar.getInstance().getTime();
		ordemServico.setDataEntregaEmContrato(
				dataEntregaPrevistaEmContrato);
		ordemServico.setDescricao("Descrição qualquer");
		ordemServico.setValor(BigDecimal.ONE);
		daoOrdemServico.salvar(ordemServico);
		
		DateUtils.addDays(dataEntregaPrevistaEmContrato, -1);
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
