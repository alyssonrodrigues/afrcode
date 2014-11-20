package br.com.afrcode.iauditor.apresentacao.democont;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

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
@URLMappings(mappings = @URLMapping(id = "viewer.democonsol.init", pattern = "/democonsol/viewer/init/#{mBeanViewerDemoConsol.entidadeAExibir}/#{mBeanViewerDemoConsol.maxStdDeviationPercent}/#{mBeanViewerDemoConsol.anoMin}/#{mBeanViewerDemoConsol.anoMax}/", viewId = "/web/page/democonsol/viewer/telaViewerDemoConsol.xhtml", outbound = false))
public class MBeanViewerDemoConsol extends AbstractManagedBean {

	private static final long serialVersionUID = 1L;

	private static final String PERIODO_PATTERN = DemonstrativoConsolidado.PERIODO_PATTERN;

	private static final String COD_MSG_CONTA_COM_DESVIO_PADRAO_ACIMA_LIMITE = "conta.desvioPadraoAcimaLimite";

	@ManagedProperty("#{daoDemonstrativoConsolidado}")
	private DaoDemonstrativoConsolidado daoDemonstrativoConsolidado;

	private List<DemonstrativoConsolidado> demonstrativosConsol;

	private List<BeanViewerConta<? extends ChartModel>> beansViewerContas;

	private BeanViewerConta<? extends ChartModel> beanViewerContaADetalhar;

	private List<BeanViewerConta<? extends ChartModel>> beansViewerSubcontas;

	private BeanViewerConta<? extends ChartModel> beanViewerSubcontaADetalhar;

	private List<BeanViewerConta<? extends ChartModel>> beansViewerSubSubcontas;

	private boolean telaEmExibicaoSubcontas;

	private boolean telaEmExibicaoSubSubcontas;

	private String entidadeAExibir;
	// maxStdDeviation a partir de avg
	private BigDecimal maxStdDeviationPercent;

	private Integer anoMin;
	private Integer anoMax;
	private LocalDate dtMinAExibir;
	private LocalDate dtMaxAExibir;

	private void alertarMaxStdDeviationViolation(
			BeanViewerConta<? extends ChartModel> beanViewerConta) {
		ExtendedStats extendedStats = beanViewerConta.getExtendedStats();
		String labelConta = beanViewerConta.getLabel();
		BigDecimal avg = extendedStats.getAvg().abs();
		BigDecimal limiteStdDeviation = avg.multiply(maxStdDeviationPercent);
		BigDecimal stdDeviation = extendedStats.getStdDeviation().abs();
		if (stdDeviation.compareTo(limiteStdDeviation) > 0) {
			MensagemAlertaNegocio msg = new MensagemAlertaNegocio();
			msg.setCodMensagem(COD_MSG_CONTA_COM_DESVIO_PADRAO_ACIMA_LIMITE);
			String maxStdDeviationPercentStr = maxStdDeviationPercent
					.multiply(BigDecimal.valueOf(100)).toPlainString()
					.concat("%");
			String stdDeviationPercentStr = stdDeviation
					.divide(avg, 4, RoundingMode.HALF_EVEN)
					.multiply(BigDecimal.valueOf(100)).toPlainString()
					.concat("%");
			msg.setArgs(new Object[] { labelConta, stdDeviationPercentStr,
					maxStdDeviationPercentStr });
			beanViewerConta.setMensagem(msg);
			adicionarMensagem(msg);
		}
	}

	public void cancelarDetalharSubcontas() {
		setTelaEmExibicaoSubcontas(false);
		beanViewerContaADetalhar = null;
		beansViewerSubcontas = null;
	}

	public void cancelarDetalharSubSubcontas() {
		setTelaEmExibicaoSubSubcontas(false);
		beanViewerSubcontaADetalhar = null;
		beansViewerSubSubcontas = null;
	}

	private void configurarChartModel(
			BeanViewerConta<? extends CartesianChartModel> beanViewerConta) {
		CartesianChartModel chartModel = beanViewerConta.getChartModel();
		String title = beanViewerConta.getLabel();
		chartModel.setTitle(title);
		final String legendPosition = "ne";
		chartModel.setLegendPosition(legendPosition);
		chartModel.setAnimate(true);
		final String labelXAxis = "Período";
		Axis xAxis = chartModel.getAxis(AxisType.X);
		if (chartModel instanceof LineChartModel) {
			xAxis = new CategoryAxis();
			chartModel.getAxes().put(AxisType.X, xAxis);
		}
		xAxis.setLabel(labelXAxis);
		Axis yAxis = chartModel.getAxis(AxisType.Y);
		final String labelYAxis = "Valores em milhares de Reais";
		yAxis.setLabel(labelYAxis);
	}

	public void detalharSubcontas(
			BeanViewerConta<? extends ChartModel> beanViewerContaADetalhar) {
		setTelaEmExibicaoSubcontas(true);
		this.beanViewerContaADetalhar = beanViewerContaADetalhar;
		beansViewerSubcontas = new ArrayList<BeanViewerConta<? extends ChartModel>>();
		Map<Long, ChartSeries> subcontasChartSeries = iniciarSubcontasChartSeries();
		iniciarBeansViewerSubcontas(subcontasChartSeries);
	}

	public void detalharSubSubcontas(
			BeanViewerConta<? extends ChartModel> beanViewerSubcontaADetalhar) {
		setTelaEmExibicaoSubSubcontas(true);
		this.beanViewerSubcontaADetalhar = beanViewerSubcontaADetalhar;
		beansViewerSubSubcontas = new ArrayList<BeanViewerConta<? extends ChartModel>>();
		Map<Long, ChartSeries> subSubcontasChartSeries = iniciarSubSubcontasChartSeries();
		iniciarBeansViewerSubSubcontas(subSubcontasChartSeries);
	}

	public Integer getAnoMax() {
		return anoMax;
	}

	public Integer getAnoMin() {
		return anoMin;
	}

	public List<BeanViewerConta<? extends ChartModel>> getBeansViewerContas() {
		return beansViewerContas;
	}

	public List<BeanViewerConta<? extends ChartModel>> getBeansViewerSubcontas() {
		return beansViewerSubcontas;
	}

	public List<BeanViewerConta<? extends ChartModel>> getBeansViewerSubSubcontas() {
		return beansViewerSubSubcontas;
	}

	public BeanViewerConta<? extends ChartModel> getBeanViewerContaADetalhar() {
		return beanViewerContaADetalhar;
	}

	public BeanViewerConta<? extends ChartModel> getBeanViewerSubcontaADetalhar() {
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

	public BigDecimal getMaxStdDeviationPercent() {
		return maxStdDeviationPercent;
	}

	@URLAction(mappingId = "viewer.democonsol.init", phaseId = PhaseId.RESTORE_VIEW, onPostback = true)
	public void iniciar() {
		if (beansViewerContas == null) {
			dtMinAExibir = new LocalDate(anoMin, 1, 1);
			dtMaxAExibir = new LocalDate(anoMax, 12, 31);
			demonstrativosConsol = recuperarDemonstrativosConsolidados();
			beansViewerContas = new ArrayList<BeanViewerConta<? extends ChartModel>>();
			Map<Long, ChartSeries> contasChartSeries = iniciarContasChartSeries();
			iniciarBeansViewerContas(contasChartSeries);
		}
	}

	private void iniciarBeansViewerContas(
			Map<Long, ChartSeries> contasChartSeries) {
		if (contasChartSeries.isEmpty()) {
			return;
		}
		BeanViewerConta<LineChartModel> beanViewerContasConsol = new BeanViewerConta<LineChartModel>();
		beanViewerContasConsol.setConsolidado(true);
		beanViewerContasConsol
				.setLabel("Evolução de contas de Demonstrações Consolidadas "
						+ "do Resultado do(a) " + entidadeAExibir);
		LineChartModel contasConsolChartModel = new LineChartModel();
		beanViewerContasConsol.setChartModel(contasConsolChartModel);
		beansViewerContas.add(beanViewerContasConsol);

		for (Entry<Long, ChartSeries> entry : contasChartSeries.entrySet()) {
			ChartSeries chartSeries = entry.getValue();
			contasConsolChartModel.addSeries(chartSeries);

			LineChartModel contaChartModel = new LineChartModel();
			contaChartModel.addSeries(chartSeries);

			String labelConta = chartSeries.getLabel();
			ExtendedStats extendedStats = recuperarContaExtendedStats(labelConta);
			BeanViewerConta<LineChartModel> beanViewerConta = new BeanViewerConta<LineChartModel>(
					labelConta, contaChartModel, extendedStats);
			configurarChartModel(beanViewerConta);
			alertarMaxStdDeviationViolation(beanViewerConta);

			beansViewerContas.add(beanViewerConta);
		}
		configurarChartModel(beanViewerContasConsol);
	}

	private void iniciarBeansViewerSubcontas(
			Map<Long, ChartSeries> subcontasChartSeries) {
		if (subcontasChartSeries.isEmpty()) {
			return;
		}
		BeanViewerConta<LineChartModel> beanViewerSubcontasConsol = new BeanViewerConta<LineChartModel>();
		beanViewerSubcontasConsol.setConsolidado(true);
		beanViewerSubcontasConsol.setLabel("Evolução de subcontas de "
				+ beanViewerContaADetalhar.getLabel());
		LineChartModel subcontasConsolChartModel = new LineChartModel();
		beanViewerSubcontasConsol.setChartModel(subcontasConsolChartModel);
		beansViewerSubcontas.add(beanViewerSubcontasConsol);

		for (Entry<Long, ChartSeries> entry : subcontasChartSeries.entrySet()) {
			ChartSeries chartSeries = entry.getValue();
			subcontasConsolChartModel.addSeries(chartSeries);

			LineChartModel subcontaChartModel = new LineChartModel();
			subcontaChartModel.addSeries(chartSeries);

			String labelSubconta = chartSeries.getLabel();
			String labelConta = beanViewerContaADetalhar.getLabel();
			ExtendedStats extendedStats = recuperarSubcontaExtendedStats(
					labelConta, labelSubconta);
			BeanViewerConta<LineChartModel> beanViewerSubconta = new BeanViewerConta<LineChartModel>(
					labelSubconta, subcontaChartModel, extendedStats);
			configurarChartModel(beanViewerSubconta);
			alertarMaxStdDeviationViolation(beanViewerSubconta);

			beansViewerSubcontas.add(beanViewerSubconta);
		}
		beanViewerContaADetalhar.setBeansViewerSubcontas(beansViewerSubcontas);
		configurarChartModel(beanViewerSubcontasConsol);
	}

	private void iniciarBeansViewerSubSubcontas(
			Map<Long, ChartSeries> subSubcontasChartSeries) {
		if (subSubcontasChartSeries.isEmpty()) {
			return;
		}
		BeanViewerConta<LineChartModel> beanViewerSubSubcontasConsol = new BeanViewerConta<LineChartModel>();
		beanViewerSubSubcontasConsol.setConsolidado(true);
		beanViewerSubSubcontasConsol.setLabel("Evolução de subcontas de "
				+ beanViewerSubcontaADetalhar.getLabel());
		LineChartModel subSubcontasConsolChartModel = new LineChartModel();
		beanViewerSubSubcontasConsol
				.setChartModel(subSubcontasConsolChartModel);
		beansViewerSubSubcontas.add(beanViewerSubSubcontasConsol);

		for (Entry<Long, ChartSeries> entry : subSubcontasChartSeries
				.entrySet()) {
			ChartSeries chartSeries = entry.getValue();
			subSubcontasConsolChartModel.addSeries(chartSeries);

			LineChartModel subSubcontaChartModel = new LineChartModel();
			subSubcontaChartModel.addSeries(chartSeries);

			String labelSubSubconta = chartSeries.getLabel();
			String labelConta = beanViewerContaADetalhar.getLabel();
			String labelSubconta = beanViewerSubcontaADetalhar.getLabel();
			ExtendedStats extendedStats = recuperarSubSubcontaExtendedStats(
					labelConta, labelSubconta, labelSubSubconta);
			BeanViewerConta<LineChartModel> beanViewerSubSubconta = new BeanViewerConta<LineChartModel>(
					labelSubSubconta, subSubcontaChartModel, extendedStats);
			configurarChartModel(beanViewerSubSubconta);
			alertarMaxStdDeviationViolation(beanViewerSubSubconta);

			beansViewerSubSubcontas.add(beanViewerSubSubconta);
		}
		beanViewerSubcontaADetalhar
				.setBeansViewerSubcontas(beansViewerSubSubcontas);
		configurarChartModel(beanViewerSubSubcontasConsol);
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
			iniciarSubcontasComposicaoChartSeries(subcontas, demoConsol);
			for (Conta subconta : subcontas) {
				preencherContaChartSeries(subcontasChartSeries, periodo,
						subconta);
			}
		}
		return subcontasChartSeries;
	}

	private void iniciarSubcontasComposicaoChartSeries(List<Conta> subcontas,
			DemonstrativoConsolidado demoConsol) {
		if (subcontas.isEmpty()) {
			return;
		}
		String labelContaADetalhar = beanViewerContaADetalhar.getLabel();
		BeanViewerConta<PieChartModel> beanViewerSubcontasComposicao = new BeanViewerConta<PieChartModel>();
		LocalDate periodo = LocalDate.fromDateFields(demoConsol.getPeriodo());
		beanViewerSubcontasComposicao.setLabel("Composição % de "
				+ labelContaADetalhar + " em "
				+ periodo.toString(PERIODO_PATTERN));
		beanViewerSubcontasComposicao.setConsolidado(true);
		PieChartModel subcontasComposicaoChartModel = new PieChartModel();
		subcontasComposicaoChartModel.setTitle(beanViewerSubcontasComposicao
				.getLabel());
		subcontasComposicaoChartModel.setLegendPosition("e");
		subcontasComposicaoChartModel.setShowDataLabels(true);
		beanViewerSubcontasComposicao
				.setChartModel(subcontasComposicaoChartModel);
		for (Conta subconta : subcontas) {
			subcontasComposicaoChartModel.set(subconta.getLabel(), subconta
					.getValor().abs());
		}
		beansViewerSubcontas.add(beanViewerSubcontasComposicao);
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
			iniciarSubSubcontasComposicaoChartSeries(subSubcontas, demoConsol);
			for (Conta subSubconta : subSubcontas) {
				preencherContaChartSeries(subSubcontasChartSeries, periodo,
						subSubconta);
			}
		}
		return subSubcontasChartSeries;
	}

	private void iniciarSubSubcontasComposicaoChartSeries(
			List<Conta> subSubcontas, DemonstrativoConsolidado demoConsol) {
		if (subSubcontas.isEmpty()) {
			return;
		}
		String labelSubcontaADetalhar = beanViewerSubcontaADetalhar.getLabel();
		BeanViewerConta<PieChartModel> beanViewerSubSubcontasComposicao = new BeanViewerConta<PieChartModel>();
		LocalDate periodo = LocalDate.fromDateFields(demoConsol.getPeriodo());
		beanViewerSubSubcontasComposicao.setLabel("Composição % de "
				+ labelSubcontaADetalhar + " em "
				+ periodo.toString(PERIODO_PATTERN));
		beanViewerSubSubcontasComposicao.setConsolidado(true);
		PieChartModel subSubcontasComposicaoChartModel = new PieChartModel();
		subSubcontasComposicaoChartModel
				.setTitle(beanViewerSubSubcontasComposicao.getLabel());
		subSubcontasComposicaoChartModel.setLegendPosition("e");
		subSubcontasComposicaoChartModel.setShowDataLabels(true);
		beanViewerSubSubcontasComposicao
				.setChartModel(subSubcontasComposicaoChartModel);
		for (Conta subSubconta : subSubcontas) {
			subSubcontasComposicaoChartModel.set(subSubconta.getLabel(),
					subSubconta.getValor().abs());
		}
		beansViewerSubSubcontas.add(beanViewerSubSubcontasComposicao);
	}

	public boolean isTelaEmExibicaoSubcontas() {
		return telaEmExibicaoSubcontas;
	}

	public boolean isTelaEmExibicaoSubSubcontas() {
		return telaEmExibicaoSubSubcontas;
	}

	private void preencherContaChartSeries(
			Map<Long, ChartSeries> contasChartSeries, Date periodo, Conta conta) {
		Long id = conta.getId();
		String label = conta.getLabel();
		ChartSeries contaChartSeries = contasChartSeries.get(id);
		if (contaChartSeries == null) {
			contaChartSeries = new ChartSeries(label);
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

	public void setAnoMax(Integer anoMax) {
		this.anoMax = anoMax;
	}

	public void setAnoMin(Integer anoMin) {
		this.anoMin = anoMin;
	}

	public void setDaoDemonstrativoConsolidado(
			DaoDemonstrativoConsolidado daoDemonstrativoConsolidado) {
		this.daoDemonstrativoConsolidado = daoDemonstrativoConsolidado;
	}

	public void setEntidadeAExibir(String entidadeAExibir) {
		this.entidadeAExibir = entidadeAExibir;
	}

	public void setMaxStdDeviationPercent(BigDecimal maxStdDeviationPercent) {
		this.maxStdDeviationPercent = maxStdDeviationPercent;
	}

	public void setTelaEmExibicaoSubcontas(boolean telaEmExibicaoSubcontas) {
		this.telaEmExibicaoSubcontas = telaEmExibicaoSubcontas;
	}

	public void setTelaEmExibicaoSubSubcontas(boolean telaEmExibicaoSubSubcontas) {
		this.telaEmExibicaoSubSubcontas = telaEmExibicaoSubSubcontas;
	}

}
