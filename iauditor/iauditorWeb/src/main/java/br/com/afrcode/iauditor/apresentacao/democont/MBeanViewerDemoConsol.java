package br.com.afrcode.iauditor.apresentacao.democont;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.joda.time.LocalDate;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.afrcode.arquitetura.apresentacao.managedbean.AbstractManagedBean;
import br.com.afrcode.arquitetura.util.mensagem.alerta.MensagemAlertaNegocio;
import br.com.afrcode.iauditor.dominio.democont.Conta;
import br.com.afrcode.iauditor.dominio.democont.DemonstrativoConsolidado;
import br.com.afrcode.iauditor.dominio.democont.dao.DaoDemonstrativoConsolidado;
import br.com.afrcode.iauditor.dominio.elasticsearch.ExtendedStats;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLAction.PhaseId;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ViewScoped
@RolesAllowed({ "ROLE_USER" })
@URLMappings(mappings = @URLMapping(id = "viewer.democonsol.init", pattern = "/democonsol/viewer/init/#{mBeanViewerDemoConsol.entidadeAExibir}/#{mBeanViewerDemoConsol.maxStdDeviation}/", viewId = "/web/page/democonsol/viewer/telaViewerDemoConsol.xhtml", outbound = false))
public class MBeanViewerDemoConsol extends AbstractManagedBean {

	private static final long serialVersionUID = 1L;

	private static final String PERIODO_PATTERN = DemonstrativoConsolidado.PERIODO_PATTERN;

	private static final String COD_MSG_CONTA_COM_DESVIO_PADRAO_ACIMA_LIMITE = "conta.desvioPadraoAcimaLimite";

	@ManagedProperty("#{daoDemonstrativoConsolidado}")
	private DaoDemonstrativoConsolidado daoDemonstrativoConsolidado;

	private List<DemonstrativoConsolidado> demonstrativosConsol;

	private List<BeanViewerConta> beansViewerContas;

	private BeanViewerConta beanViewerContaADetalhar;

	private List<BeanViewerConta> beansViewerSubcontas;

	private BeanViewerConta beanViewerSubcontaADetalhar;

	private List<BeanViewerConta> beansViewerSubSubcontas;

	private String entidadeAExibir;
	// maxStdDeviation a partir de avg
	private BigDecimal maxStdDeviation;

	// TODO tornar atributos abaixo em parâmetros de entrada
	private LocalDate dtMinAExibir = new LocalDate(2010, 3, 31);
	private LocalDate dtMaxAExibir = new LocalDate(2014, 3, 31);

	private void alertarMaxStdDeviationViolation(String labelConta,
			ExtendedStats extendedStats) {
		BigDecimal limiteStdDeviation = extendedStats.getAvg().abs()
				.multiply(maxStdDeviation);
		BigDecimal stdDeviation = extendedStats.getStdDeviation().abs();
		if (stdDeviation.compareTo(limiteStdDeviation) > 0) {
			MensagemAlertaNegocio msg = new MensagemAlertaNegocio();
			msg.setCodMensagem(COD_MSG_CONTA_COM_DESVIO_PADRAO_ACIMA_LIMITE);
			String maxStdDeviationStr = maxStdDeviation
					.multiply(BigDecimal.valueOf(100)).toPlainString()
					.concat("%");
			msg.setArgs(new Object[] { labelConta, maxStdDeviationStr });
			adicionarMensagem(msg);
		}
	}

	public void cancelarDetalharSubcontas() {
		beanViewerContaADetalhar = null;
		beansViewerSubcontas = null;
	}

	public void cancelarDetalharSubSubcontas() {
		beanViewerSubcontaADetalhar = null;
		beansViewerSubSubcontas = null;
	}

	private void configurarChartModel(String title, LineChartModel chartModel) {
		chartModel.setTitle(title);
		chartModel.setAnimate(true);
		final String legendPosition = "ne";
		chartModel.setLegendPosition(legendPosition);
		final String labelXAxis = "Período";
		Axis xAxis = new CategoryAxis(labelXAxis);
		chartModel.getAxes().put(AxisType.X, xAxis);
		Axis yAxis = chartModel.getAxis(AxisType.Y);
		final String labelYAxis = "Valores em milhares de Reais";
		yAxis.setLabel(labelYAxis);
	}

	public void detalharSubcontas(BeanViewerConta beanViewerContaADetalhar) {
		this.beanViewerContaADetalhar = beanViewerContaADetalhar;
		Map<Long, ChartSeries> subcontasChartSeries = iniciarSubcontasChartSeries();
		iniciarBeansViewerSubcontas(subcontasChartSeries);
	}

	public void detalharSubSubcontas(BeanViewerConta beanViewerSubcontaADetalhar) {
		this.beanViewerSubcontaADetalhar = beanViewerSubcontaADetalhar;
		Map<Long, ChartSeries> subSubcontasChartSeries = iniciarSubSubcontasChartSeries();
		iniciarBeansViewerSubSubcontas(subSubcontasChartSeries);
	}

	public List<BeanViewerConta> getBeansViewerContas() {
		return beansViewerContas;
	}

	public List<BeanViewerConta> getBeansViewerSubcontas() {
		return beansViewerSubcontas;
	}

	public List<BeanViewerConta> getBeansViewerSubSubcontas() {
		return beansViewerSubSubcontas;
	}

	public BeanViewerConta getBeanViewerContaADetalhar() {
		return beanViewerContaADetalhar;
	}

	public BeanViewerConta getBeanViewerSubcontaADetalhar() {
		return beanViewerSubcontaADetalhar;
	}

	public List<DemonstrativoConsolidado> getDemonstrativosConsol() {
		return demonstrativosConsol;
	}

	public LocalDate getDtMaxAExibir() {
		return dtMaxAExibir;
	}

	public LocalDate getDtMinAExibir() {
		return dtMinAExibir;
	}

	public String getEntidadeAExibir() {
		return entidadeAExibir;
	}

	public BigDecimal getMaxStdDeviation() {
		return maxStdDeviation;
	}

	@URLAction(mappingId = "viewer.democonsol.init", phaseId = PhaseId.RESTORE_VIEW, onPostback = true)
	public void iniciar() {
		demonstrativosConsol = recuperarDemonstrativosConsolidados();
		Map<Long, ChartSeries> contasChartSeries = iniciarContasChartSeries();
		iniciarBeansViewerContas(contasChartSeries);
	}

	private void iniciarBeansViewerContas(
			Map<Long, ChartSeries> contasChartSeries) {
		beansViewerContas = new ArrayList<BeanViewerConta>();
		for (Entry<Long, ChartSeries> entry : contasChartSeries.entrySet()) {
			ChartSeries chartSeries = entry.getValue();
			String labelConta = chartSeries.getLabel();

			LineChartModel contaChartModel = new LineChartModel();
			contaChartModel.addSeries(chartSeries);
			configurarChartModel(labelConta, contaChartModel);

			ExtendedStats extendedStats = recuperarContaExtendedStats(labelConta);
			alertarMaxStdDeviationViolation(labelConta, extendedStats);
			BeanViewerConta beanViewerConta = new BeanViewerConta(labelConta,
					contaChartModel, extendedStats);
			beansViewerContas.add(beanViewerConta);
		}
	}

	private void iniciarBeansViewerSubcontas(
			Map<Long, ChartSeries> subcontasChartSeries) {
		beansViewerSubcontas = new ArrayList<BeanViewerConta>();

		for (Entry<Long, ChartSeries> entry : subcontasChartSeries.entrySet()) {
			ChartSeries chartSeries = entry.getValue();
			String labelSubconta = chartSeries.getLabel();

			LineChartModel subcontaChartModel = new LineChartModel();
			subcontaChartModel.addSeries(chartSeries);
			configurarChartModel(labelSubconta, subcontaChartModel);

			String labelConta = beanViewerContaADetalhar.getLabel();
			ExtendedStats extendedStats = recuperarSubcontaExtendedStats(
					labelConta, labelSubconta);
			alertarMaxStdDeviationViolation(labelSubconta, extendedStats);
			BeanViewerConta beanViewerSubconta = new BeanViewerConta(
					labelSubconta, subcontaChartModel, extendedStats);
			beansViewerSubcontas.add(beanViewerSubconta);
		}
		beanViewerContaADetalhar.setBeansViewerSubcontas(beansViewerSubcontas);
	}

	private void iniciarBeansViewerSubSubcontas(
			Map<Long, ChartSeries> subSubcontasChartSeries) {
		beansViewerSubSubcontas = new ArrayList<BeanViewerConta>();

		for (Entry<Long, ChartSeries> entry : subSubcontasChartSeries
				.entrySet()) {
			ChartSeries chartSeries = entry.getValue();
			String labelSubSubconta = chartSeries.getLabel();

			LineChartModel subSubcontaChartModel = new LineChartModel();
			subSubcontaChartModel.addSeries(chartSeries);
			configurarChartModel(labelSubSubconta, subSubcontaChartModel);

			String labelConta = beanViewerContaADetalhar.getLabel();
			String labelSubconta = beanViewerSubcontaADetalhar.getLabel();
			ExtendedStats extendedStats = recuperarSubSubcontaExtendedStats(
					labelConta, labelSubconta, labelSubSubconta);
			alertarMaxStdDeviationViolation(labelSubSubconta, extendedStats);
			BeanViewerConta beanViewerSubSubconta = new BeanViewerConta(
					labelSubSubconta, subSubcontaChartModel, extendedStats);
			beansViewerSubSubcontas.add(beanViewerSubSubconta);
		}
		beanViewerSubcontaADetalhar
				.setBeansViewerSubcontas(beansViewerSubSubcontas);
	}

	private Map<Long, ChartSeries> iniciarContasChartSeries() {
		Map<Long, ChartSeries> contasChartSeries = new TreeMap<Long, ChartSeries>();
		for (DemonstrativoConsolidado demoConsol : demonstrativosConsol) {
			Date periodo = demoConsol.getPeriodo();
			for (Conta conta : demoConsol.getContas()) {
				preencherContaChartSeries(contasChartSeries, periodo, conta);
			}
		}
		return contasChartSeries;
	}

	private Map<Long, ChartSeries> iniciarSubcontasChartSeries() {
		Map<Long, ChartSeries> subcontasChartSeries = new TreeMap<Long, ChartSeries>();
		String labelContaADetalhar = beanViewerContaADetalhar.getLabel();
		for (DemonstrativoConsolidado demoConsol : demonstrativosConsol) {
			Date periodo = demoConsol.getPeriodo();
			List<Conta> subcontas = demoConsol
					.getSubcontas(labelContaADetalhar);
			for (Conta subconta : subcontas) {
				preencherContaChartSeries(subcontasChartSeries, periodo,
						subconta);
			}
		}
		return subcontasChartSeries;
	}

	private Map<Long, ChartSeries> iniciarSubSubcontasChartSeries() {
		Map<Long, ChartSeries> subSubcontasChartSeries = new TreeMap<Long, ChartSeries>();
		String labelContaADetalhar = beanViewerContaADetalhar.getLabel();
		String labelSubcontaADetalhar = beanViewerSubcontaADetalhar.getLabel();
		for (DemonstrativoConsolidado demoConsol : demonstrativosConsol) {
			Date periodo = demoConsol.getPeriodo();
			Conta contaADetalhar = demoConsol.getConta(labelContaADetalhar);
			Conta subcontaADetalhar = contaADetalhar
					.getSubconta(labelSubcontaADetalhar);
			List<Conta> subSubcontas = subcontaADetalhar.getSubcontas();
			for (Conta subSubconta : subSubcontas) {
				preencherContaChartSeries(subSubcontasChartSeries, periodo,
						subSubconta);
			}
		}
		return subSubcontasChartSeries;
	}

	private void preencherContaChartSeries(
			Map<Long, ChartSeries> contasChartSeries, Date periodo, Conta conta) {
		Long id = conta.getId();
		String label = conta.getLabel();
		ChartSeries contaChartSeries = contasChartSeries.get(id);
		if (contaChartSeries == null) {
			contaChartSeries = new LineChartSeries(label);
			contasChartSeries.put(id, contaChartSeries);
		}
		LocalDate dt = LocalDate.fromDateFields(periodo);
		String dtStr = dt.toString(PERIODO_PATTERN);
		contaChartSeries.set(dtStr, conta.getValor());
	}

	private ExtendedStats recuperarContaExtendedStats(String labelConta) {
		final String field = "valor";
		ExtendedStats extendedStats = daoDemonstrativoConsolidado
				.getContaExtendedStats(entidadeAExibir, dtMinAExibir,
						dtMaxAExibir, labelConta, field);
		return extendedStats;
	}

	private List<DemonstrativoConsolidado> recuperarDemonstrativosConsolidados() {
		List<DemonstrativoConsolidado> demosConsol = daoDemonstrativoConsolidado
				.recuperarDemonstrativosConsolidados(entidadeAExibir,
						dtMinAExibir, dtMaxAExibir);
		return demosConsol;
	}

	private ExtendedStats recuperarSubcontaExtendedStats(String labelConta,
			String labelSubconta) {
		final String field = "valor";
		ExtendedStats extendedStats = daoDemonstrativoConsolidado
				.getSubcontaExtendedStats(entidadeAExibir, dtMinAExibir,
						dtMaxAExibir, labelConta, labelSubconta, field);
		return extendedStats;
	}

	private ExtendedStats recuperarSubSubcontaExtendedStats(String labelConta,
			String labelSubconta, String labelSubSubconta) {
		final String field = "valor";
		ExtendedStats extendedStats = daoDemonstrativoConsolidado
				.getSubSubcontaExtendedStats(entidadeAExibir, dtMinAExibir,
						dtMaxAExibir, labelConta, labelSubconta,
						labelSubSubconta, field);
		return extendedStats;
	}

	public void setDaoDemonstrativoConsolidado(
			DaoDemonstrativoConsolidado daoDemonstrativoConsolidado) {
		this.daoDemonstrativoConsolidado = daoDemonstrativoConsolidado;
	}

	public void setDtMaxAExibir(LocalDate dtMaxAExibir) {
		this.dtMaxAExibir = dtMaxAExibir;
	}

	public void setDtMinAExibir(LocalDate dtMinAExibir) {
		this.dtMinAExibir = dtMinAExibir;
	}

	public void setEntidadeAExibir(String entidadeAExibir) {
		this.entidadeAExibir = entidadeAExibir;
	}

	public void setMaxStdDeviation(BigDecimal maxStdDeviation) {
		this.maxStdDeviation = maxStdDeviation;
	}

}
