package br.com.afrcode.arquitetura.modelo.entidade;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Superclasse dos objetos persistentes MappedSuperclass. Definição da
 * estratégia de controle de lock otimista através do campo versao.
 * 
 * 
 * @param <TIPOID>
 *            Tipo do ID (Long, Integer, String, etc.)
 * 
 */
@MappedSuperclass
public abstract class ObjetoPersistenteAbstrato extends EntidadeAbstrata<Long> {

	public static final String JODA_DATE_TIME_TYPE = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime";

	public static final String JODA_DATE_TYPE = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate";

	public static final String JODA_TIME_TYPE = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime";

	public static final String JODA_YEAR_MONTH_TYPE = "org.jadira.usertype.dateandtime.joda.PersistentYearMonthAsString";

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COD")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_GENERATOR")
	private Long id;

	@Version
	@Column(name = "NUM_VERSAO_REGISTRO")
	private Long versao;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the versao
	 */
	public Long getVersao() {
		return versao;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param versao
	 *            the versao to set
	 */
	public void setVersao(Long versao) {
		this.versao = versao;
	}

	/**
	 * Ver IEntidade#validarExclusao().
	 */
	@Override
	public void validarExclusao() {

	}

	/**
	 * Ver IEntidade#validarSalvamento().
	 */
	@Override
	public void validarSalvamento() {

	}

}
