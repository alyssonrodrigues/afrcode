package br.com.afrcode.arquitetura.modelo.entidade;

import java.io.Serializable;

/**
 * Interface de defini��o de uma Entidade, objeto persist�vel em banco de dados.
 * 
 * Esta interface prima pela cria��o de Entidades RICAS com m�todos al�m de get/set de atributos. Ou seja, entidades devem
 * implementar suas respectivas regras de neg�cio.
 * 
 * Requisito m�nimo: ter um ID do tipo TIPOID (parametrizado).
 * 
 * 
 * @param <T> Tipo do ID (Long, Integer, String, etc.) que deve ser Comparable<TIPOID>
 */
public interface IEntidade<T extends Comparable<T>> extends Serializable {

    /**
     * M�todo de obten��o da propriedade id.
     * 
     * @return id
     */
    T getId();

    /**
     * M�todo de atribui��o da propriedade id.
     * 
     * @param id id
     */
    void setId(T id);

    /**
     * M�todo para atribui��o de valores persist�veis � entidade, tornando-a persist�vel sem erros.
     * Deve ser acionado automaticamente pelo m�todo IDao#instanciarObjetoPersistivel() de implementa��es de IDao.
     */
    void preencherComValoresPersistiveis();

    /**
     * M�todo a patir do qual devem ser acionadas rotinas (outros m�todos internos a entidade) de valida��o necess�rias
     * ao salvar a entidade.
     * 
     * Os m�todos acionados a partir deste m�todo podem fazer uso do recurso Method Validation do Hibernate Validator.
     * 
     * Deve ser acionado automaticamente pelo m�todo IDao#salvar(Entidade) de implementa��es de IDao.
     * 
     */
    void validarSalvamento();

    /**
     * M�todo a partir do qual devem ser acionadas rotinas (outros m�todos internos a entidade) de valida��o necess�rias
     * ao excluir a entidade.
     * 
     * Os m�todos acionados a partir deste m�todo podem fazer uso do recurso Method Validation do Hibernate Validator.
     * 
     * Deve ser acionado automaticamente pelo m�todo IDao#excluir(Entidade) de implementa��es de IDao.
     * 
     */
    void validarExclusao();

}
