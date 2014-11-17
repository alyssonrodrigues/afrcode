package br.com.afrcode.iauditor.apresentacao.democont;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;
import br.com.afrcode.iauditor.dominio.democont.dao.DaoDemonstrativoConsolidado;

public class MBeanViewerDemoConsolTest extends AbstractCasoTesteEmMemoria {
	@Autowired
	private DaoDemonstrativoConsolidado daoDemonstrativoConsolidado;

	private MBeanViewerDemoConsol configurarMBean() {
		MBeanViewerDemoConsol mBeanViewerDemoConsol = new MBeanViewerDemoConsol();
		mBeanViewerDemoConsol
				.setDaoDemonstrativoConsolidado(daoDemonstrativoConsolidado);
		return mBeanViewerDemoConsol;
	}

	@Test
	public void iniciarContasChartModel() {
		MBeanViewerDemoConsol mBean = configurarMBean();
		mBean.iniciarContasChartModel();
		Assert.assertNotNull("contasBarChartModel nulo!",
				mBean.getContasChartModel());
	}

}
