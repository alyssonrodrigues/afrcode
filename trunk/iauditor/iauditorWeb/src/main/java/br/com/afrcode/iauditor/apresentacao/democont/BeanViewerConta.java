package br.com.afrcode.iauditor.apresentacao.democont;

import java.io.Serializable;
import java.util.List;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.LineChartModel;

import br.com.afrcode.arquitetura.util.mensagem.IMensagem;
import br.com.afrcode.iauditor.dominio.elasticsearch.ExtendedStats;

public class BeanViewerConta<T extends ChartModel> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String label;

	private T chartModel;

	private ExtendedStats extendedStats;

	private List<BeanViewerConta<? extends ChartModel>> beansViewerSubcontas;

	private IMensagem mensagem;

	private boolean consolidado;

	public BeanViewerConta() {

	}

	public BeanViewerConta(String label, T chartModel,
			ExtendedStats extendedStats) {
		super();
		this.label = label;
		this.chartModel = chartModel;
		this.extendedStats = extendedStats;
	}

	public List<BeanViewerConta<? extends ChartModel>> getBeansViewerSubcontas() {
		return beansViewerSubcontas;
	}

	public T getChartModel() {
		return chartModel;
	}

	public String getChartType() {
		String chartType = null;
		if (chartModel instanceof LineChartModel) {
			chartType = "line";
		} else if (chartModel instanceof BarChartModel) {
			chartType = "bar";
		} else {
			throw new IllegalArgumentException("chartType não previsto!");
		}
		return chartType;
	}

	public ExtendedStats getExtendedStats() {
		return extendedStats;
	}

	public String getLabel() {
		return label;
	}

	public IMensagem getMensagem() {
		return mensagem;
	}

	public boolean isConsolidado() {
		return consolidado;
	}

	public void setBeansViewerSubcontas(
			List<BeanViewerConta<? extends ChartModel>> beansViewerSubcontas) {
		this.beansViewerSubcontas = beansViewerSubcontas;
	}

	public void setChartModel(T chartModel) {
		this.chartModel = chartModel;
	}

	public void setConsolidado(boolean consolidado) {
		this.consolidado = consolidado;
	}

	public void setExtendedStats(ExtendedStats extendedStats) {
		this.extendedStats = extendedStats;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setMensagem(IMensagem mensagem) {
		this.mensagem = mensagem;
	}

}
