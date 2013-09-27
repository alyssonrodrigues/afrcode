package br.com.afrcode.apps.egos.dominio.dao;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.afrcode.apps.egos.dominio.OrdemServico;
import br.com.afrcode.apps.egos.spring.config.BeansSpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeansSpringConfig.class)
public class DaoOrdemServicoTest {
	
	@Autowired
	private DaoOrdemServico daoOrdemServico;
	
	@Test
	public void testarSalvarERecuperarOrdemServico() {
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setConcluida(false);
		ordemServico.setDataEntregaEmContrato(
				Calendar.getInstance());
		ordemServico.setDescricao("Descrição qualquer");
		ordemServico.setValor(BigDecimal.ONE);
		daoOrdemServico.salvar(ordemServico);
		OrdemServico ordemServicoRecuperada = daoOrdemServico.
				recuperarOrdemServico(ordemServico.getId());
		Assert.assertNotNull(ordemServicoRecuperada);
	}
}
