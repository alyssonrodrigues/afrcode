package br.com.afrcode.arquitetura.modelo.entidade;

import java.io.Serializable;

/**
 * Interface de definição de uma Entidade, objeto persistível em banco de dados.
 * 
 * Esta interface prima pela criação de Entidades RICAS com métodos além de
 * get/set de atributos. Ou seja, entidades devem implementar suas respectivas
 * regras de negócio.
 * 
 * Requisito mínimo: ter um ID do tipo TIPOID (parametrizado).
 * 
 * 
 * @param <T>
 *            Tipo do ID (Long, Integer, String, etc.) que deve ser
 *            Comparable<TIPOID>
 */
public interface IEntidade<T extends Comparable<T>> extends Serializable {

	/**
	 * Método de obtenção da propriedade id.
	 * 
	 * @return id
	 */
	T getId();

	/**
	 * Método de atribuição da propriedade id.
	 * 
	 * @param id
	 *            id
	 */
	void setId(T id);

	/**
	 * Método para atribuição de valores persistíveis à entidade, tornando-a
	 * persistível sem erros. Deve ser acionado automaticamente pelo método
	 * IDao#instanciarObjetoPersistivel() de implementações de IDao.
	 */
	void preencherComValoresPersistiveis();

	/**
	 * Método a patir do qual devem ser acionadas rotinas (outros métodos
	 * internos a entidade) de validação necessárias ao salvar a entidade.
	 * 
	 * Os métodos acionados a partir deste método podem fazer uso do recurso
	 * Method Validation do Hibernate Validator.
	 * 
	 * Deve ser acionado automaticamente pelo método IDao#salvar(Entidade) de
	 * implementações de IDao.
	 * 
	 */
	void validarSalvamento();

	/**
	 * Método a partir do qual devem ser acionadas rotinas (outros métodos
	 * internos a entidade) de validação necessárias ao excluir a entidade.
	 * 
	 * Os métodos acionados a partir deste método podem fazer uso do recurso
	 * Method Validation do Hibernate Validator.
	 * 
	 * Deve ser acionado automaticamente pelo método IDao#excluir(Entidade) de
	 * implementações de IDao.
	 * 
	 */
	void validarExclusao();

}
