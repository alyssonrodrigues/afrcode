package br.com.afrcode.iauditor.apresentacao.democont;

import java.io.Serializable;
import java.util.List;

import org.primefaces.model.chart.LineChartModel;

import br.com.afrcode.iauditor.dominio.elasticsearch.ExtendedStats;

public class BeanViewerConta implements Serializable {

	private static final long serialVersionUID = 1L;

	private String label;

	private LineChartModel chartModel;

	private ExtendedStats extendedStats;

	private List<BeanViewerConta> beansViewerSubcontas;

	public BeanViewerConta(String label, LineChartModel chartModel,
			ExtendedStats extendedStats) {
		super();
		this.label = label;
		this.chartModel = chartModel;
		this.extendedStats = extendedStats;
	}

	public List<BeanViewerConta> getBeansViewerSubcontas() {
		return beansViewerSubcontas;
	}

	public LineChartModel getChartModel() {
		return chartModel;
	}

	public ExtendedStats getExtendedStats() {
		return extendedStats;
	}

	public String getLabel() {
		return label;
	}

	public void setBeansViewerSubcontas(
			List<BeanViewerConta> beansViewerSubcontas) {
		this.beansViewerSubcontas = beansViewerSubcontas;
	}

}
