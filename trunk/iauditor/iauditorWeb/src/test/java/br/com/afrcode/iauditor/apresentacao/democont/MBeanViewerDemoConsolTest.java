package br.com.afrcode.iauditor.apresentacao.democont;

import java.math.BigDecimal;

import org.easymock.EasyMock;
import org.easymock.IMockBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.primefaces.model.chart.ChartModel;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;
import br.com.afrcode.iauditor.dominio.democont.dao.DaoDemonstrativoConsolidado;

public class MBeanViewerDemoConsolTest extends AbstractCasoTesteEmMemoria {
	@Autowired
	private DaoDemonstrativoConsolidado daoDemonstrativoConsolidado;

	private MBeanViewerDemoConsol configurarMBean() {
		IMockBuilder<MBeanViewerDemoConsol> mBeanMockBuilder = EasyMock
				.createMockBuilder(MBeanViewerDemoConsol.class);
		mBeanMockBuilder = mBeanMockBuilder
				.addMockedMethod("adicionarMensagem");
		MBeanViewerDemoConsol mBeanViewerDemoConsol = mBeanMockBuilder
				.createMock();
		mBeanViewerDemoConsol
				.setDaoDemonstrativoConsolidado(daoDemonstrativoConsolidado);
		return mBeanViewerDemoConsol;
	}

	@Test
	public void iniciar() {
		MBeanViewerDemoConsol mBean = configurarMBean();
		mBean.setEntidadeAExibir("BB");
		mBean.setAnoMin(2010);
		mBean.setAnoMax(2014);
		mBean.setMaxStdDeviationPercent(BigDecimal.valueOf(2));
		mBean.iniciar();
		Assert.assertNotNull("beansViewerContas nulo!",
				mBean.getBeansViewerContas());
	}

	@Test
	public void detalharSubcontas() {
		MBeanViewerDemoConsol mBean = configurarMBean();
		mBean.setEntidadeAExibir("BB");
		mBean.setAnoMin(2010);
		mBean.setAnoMax(2014);
		mBean.setMaxStdDeviationPercent(BigDecimal.valueOf(2));
		mBean.iniciar();

		mBean.detalharSubcontas(mBean.getBeansViewerContas().get(0));
		Assert.assertNotNull("beanViewerContaADetalhar nulo!",
				mBean.getBeanViewerContaADetalhar());
		Assert.assertNotNull("beansViewerSubcontas nulo!",
				mBean.getBeansViewerSubcontas());
	}

	@Test
	public void detalharSubSubcontas() {
		MBeanViewerDemoConsol mBean = configurarMBean();
		mBean.setEntidadeAExibir("CEF");
		mBean.setAnoMin(2010);
		mBean.setAnoMax(2014);
		mBean.setMaxStdDeviationPercent(BigDecimal.valueOf(2));
		mBean.iniciar();

		BeanViewerConta<? extends ChartModel> beanViewerContaADetalhar = mBean
				.getBeansViewerContas().get(4);
		mBean.detalharSubcontas(beanViewerContaADetalhar);
		BeanViewerConta<? extends ChartModel> beanViewerSubcontaADetalhar = mBean
				.getBeansViewerSubcontas().get(6);
		mBean.detalharSubSubcontas(beanViewerSubcontaADetalhar);
		Assert.assertNotNull("beanViewerSubcontaADetalhar nulo!",
				mBean.getBeanViewerSubcontaADetalhar());
		Assert.assertNotNull("beansViewerSubSubcontas nulo!",
				mBean.getBeansViewerSubSubcontas());
	}
}
