package br.com.afrcode.iauditor.apresentacao.democont;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.joda.time.LocalDate;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.afrcode.arquitetura.apresentacao.managedbean.AbstractManagedBean;
import br.com.afrcode.iauditor.dominio.democont.Conta;
import br.com.afrcode.iauditor.dominio.democont.DemonstrativoConsolidado;
import br.com.afrcode.iauditor.dominio.democont.dao.DaoDemonstrativoConsolidado;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLAction.PhaseId;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ViewScoped
@RolesAllowed({ "ROLE_USER" })
@URLMappings(mappings = @URLMapping(id = "viewer.democonsol.init", pattern = "/democonsol/viewer/init/", viewId = "/web/page/democonsol/viewer/telaViewerDemoConsol.xhtml", outbound = false))
public class MBeanViewerDemoConsol extends AbstractManagedBean {

	private static final String PERIODO_PATTERN = DemonstrativoConsolidado.PERIODO_PATTERN;

	private static final long serialVersionUID = 1L;

	private BarChartModel contasBarChartModel;

	@ManagedProperty("#{daoDemonstrativoConsolidado}")
	private DaoDemonstrativoConsolidado daoDemonstrativoConsolidado;

	private void configurarContasBarChartModel(String entidade,
			LocalDate dtMin, LocalDate dtMax) {
		StringBuilder sb = new StringBuilder("Evolução das Contas do ");
		sb.append(entidade).append(" de ").append(dtMin.getYear())
				.append(" a ").append(dtMax.getYear());
		contasBarChartModel.setTitle(sb.toString());
		final String legendPosition = "ne";
		contasBarChartModel.setLegendPosition(legendPosition);
		contasBarChartModel.setLegendCols(4);
		Axis xAxis = contasBarChartModel.getAxis(AxisType.X);
		final String labelXAxis = "Período";
		xAxis.setLabel(labelXAxis);
		Axis yAxis = contasBarChartModel.getAxis(AxisType.Y);
		final String labelYAxis = "Valores";
		yAxis.setLabel(labelYAxis);
		yAxis.setMin(0);
		// TODO rever
		yAxis.setMax(35000000);
	}

	public BarChartModel getContasBarChartModel() {
		return contasBarChartModel;
	}

	@URLAction(mappingId = "viewer.democonsol.init", phaseId = PhaseId.RESTORE_VIEW, onPostback = true)
	public void iniciarContasBarChartModel() {
		final String entidade = "BB";
		final LocalDate dtMin = new LocalDate(2010, 3, 31);
		final LocalDate dtMax = new LocalDate(2014, 3, 31);
		List<DemonstrativoConsolidado> demosConsol = recuperarDemonstrativosConsolidados(
				entidade, dtMin, dtMax);
		iniciarContasBarChartModel(demosConsol);
		configurarContasBarChartModel(entidade, dtMin, dtMax);
	}

	private void iniciarContasBarChartModel(
			List<DemonstrativoConsolidado> demosConsol) {
		contasBarChartModel = new BarChartModel();
		Map<String, ChartSeries> chartsSeries = new HashMap<String, ChartSeries>();
		for (DemonstrativoConsolidado demoConsol : demosConsol) {
			for (Conta conta : demoConsol.getContas()) {
				String label = conta.getLabel();
				ChartSeries chartSeries = chartsSeries.get(label);
				if (chartSeries == null) {
					chartSeries = new ChartSeries(label);
					chartsSeries.put(label, chartSeries);
					contasBarChartModel.addSeries(chartSeries);
				}
				LocalDate dt = LocalDate
						.fromDateFields(demoConsol.getPeriodo());
				String dtStr = dt.toString(PERIODO_PATTERN);
				chartSeries.set(dtStr, conta.getValor());
			}
		}
	}

	private List<DemonstrativoConsolidado> recuperarDemonstrativosConsolidados(
			String entidade, LocalDate dtMin, LocalDate dtMax) {
		List<DemonstrativoConsolidado> demosConsol = daoDemonstrativoConsolidado
				.recuperarDemonstrativosConsolidados(entidade, dtMin, dtMax);
		return demosConsol;
	}

	public void setDaoDemonstrativoConsolidado(
			DaoDemonstrativoConsolidado daoDemonstrativoConsolidado) {
		this.daoDemonstrativoConsolidado = daoDemonstrativoConsolidado;
	}

}
