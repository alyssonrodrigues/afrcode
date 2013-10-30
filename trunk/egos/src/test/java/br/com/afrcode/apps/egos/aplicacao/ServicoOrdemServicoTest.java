package br.com.afrcode.apps.egos.aplicacao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	@Rule
	public ExpectedException expectedException = 
			ExpectedException.none();
	
	@Autowired
	private ServicoOrdemServico servicoOrdemServico;
	
	@Autowired
	private DaoOrdemServico daoOrdemServico;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private void autenticarUsuario(String... roles) {
		Authentication authentication = new TestingAuthenticationToken(
				"USER_TU", "PASSWD", roles);
		authentication = authenticationManager.authenticate(
				authentication);
		SecurityContextHolder.getContext().setAuthentication(
				authentication);
	}
	
	@Test
	public void testarAcessoNegadoAoServicoOrdemServico() {
		autenticarUsuario("ROLE_USER");
		
		expectedException.expect(AccessDeniedException.class);
		servicoOrdemServico.recuperarOrdensServicoEmAtraso(
				Calendar.getInstance());
		Assert.fail("Deveria ter ocorrido AccessDeniedException!");
	}

	@Test
	public void testarRecuperarOrdensServicoEmAtraso() {
		autenticarUsuario("ROLE_MANAGER");
		
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setConcluida(true);
		Date dataEntregaPrevistaEmContrato = 
				Calendar.getInstance().getTime();
		ordemServico.setDataEntregaEmContrato(
				dataEntregaPrevistaEmContrato);
		ordemServico.setDescricao("Descrição qualquer");
		ordemServico.setValor(BigDecimal.ONE);
		daoOrdemServico.salvar(ordemServico);
		
		dataEntregaPrevistaEmContrato = DateUtils.addDays(
				dataEntregaPrevistaEmContrato, -1);
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
