package br.com.afrcode.iauditor.dominio.elasticsearch;

import java.io.Serializable;
import java.math.BigDecimal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

public class ExtendedStats implements Serializable {

	private static final long serialVersionUID = 1L;

	private int count;
	private BigDecimal min;
	private BigDecimal max;
	private BigDecimal avg;
	private BigDecimal sum;
	@SerializedName("sum_of_squares")
	private BigDecimal sumOfSquares;
	private BigDecimal variance;
	@SerializedName("std_deviation")
	private BigDecimal stdDeviation;

	public static ExtendedStats fromJson(JsonElement jsonElement) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(jsonElement, ExtendedStats.class);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	public BigDecimal getAvg() {
		return avg;
	}

	public void setAvg(BigDecimal avg) {
		this.avg = avg;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public BigDecimal getSumOfSquares() {
		return sumOfSquares;
	}

	public void setSumOfSquares(BigDecimal sumOfSquares) {
		this.sumOfSquares = sumOfSquares;
	}

	public BigDecimal getVariance() {
		return variance;
	}

	public void setVariance(BigDecimal variance) {
		this.variance = variance;
	}

	public BigDecimal getStdDeviation() {
		return stdDeviation;
	}

	public void setStdDeviation(BigDecimal stdDeviation) {
		this.stdDeviation = stdDeviation;
	}

}
