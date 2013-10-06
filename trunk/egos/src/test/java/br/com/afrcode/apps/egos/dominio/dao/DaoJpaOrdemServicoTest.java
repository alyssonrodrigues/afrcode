package br.com.afrcode.apps.egos.dominio.dao;

import java.math.BigDecimal;
import java.util.Calendar;

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
import br.com.afrcode.apps.egos.spring.config.BeansSpringTestesConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeansSpringTestesConfig.class)
@ActiveProfiles("TESTES")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class DaoJpaOrdemServicoTest {
	
	@Autowired
	private DaoOrdemServico daoOrdemServico;

	private OrdemServico criarOS() {
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setConcluida(true);
		ordemServico.setDataEntregaEmContrato(
				Calendar.getInstance().getTime());
		ordemServico.setDescricao("1a OS");
		ordemServico.setValor(BigDecimal.ONE);
		return ordemServico;
	}

	@Test
	public void testarSalvarRecuperarEExcluirOrdemServico() {
		OrdemServico ordemServico = criarOS();
		daoOrdemServico.salvar(ordemServico);
		Long id = ordemServico.getId();
		
		OrdemServico ordemServicoEmBD = 
				daoOrdemServico.recuperarOrdemServico(id);
		Assert.assertNotNull("Uma OS deveria existir em BD!",
				ordemServicoEmBD);
		Assert.assertSame("O id da OS em BD deveria ser " 
				+ id + "!",
				id, ordemServicoEmBD.getId());
		
		daoOrdemServico.excluir(ordemServicoEmBD);
		Assert.assertNull("Após a exclusão não deveria "
				+ "existir OS de id " + id + "!", 
				daoOrdemServico.recuperarOrdemServico(id));
	}
}
