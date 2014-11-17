package br.com.afrcode.iauditor.apresentacao.democont;

import java.math.BigDecimal;

import org.easymock.EasyMock;
import org.easymock.IMockBuilder;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
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
		mBean.setDtMinAExibir(new LocalDate(2010, 3, 31));
		mBean.setDtMaxAExibir(new LocalDate(2014, 3, 31));
		mBean.setMaxStdDeviation(BigDecimal.valueOf(2));
		mBean.iniciar();
		Assert.assertNotNull("beansViewerContas nulo!",
				mBean.getBeansViewerContas());
	}

}
