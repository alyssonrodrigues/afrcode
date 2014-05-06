package br.com.afrcode.arquitetura.util.excecao;

import org.apache.commons.lang.exception.ExceptionUtils;

public class ExcecaoNaoPrevista extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String id;

	public ExcecaoNaoPrevista(String msg) {
		super(msg);
	}

	public ExcecaoNaoPrevista(Throwable te) {
		super(te);
	}

	public ExcecaoNaoPrevista(String id, Throwable te) {
		super(id, te);
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getStackTraceComoString() {
		return ExceptionUtils.getFullStackTrace(this);
	}

	public String getStackTraceComoStringParaWeb() {
		String str = getStackTraceComoString();
		str = str.replaceAll("\t", "<br/>");
		return str;
	}

}
