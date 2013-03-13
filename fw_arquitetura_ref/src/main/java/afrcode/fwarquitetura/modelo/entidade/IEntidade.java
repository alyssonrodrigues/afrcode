package afrcode.fwarquitetura.modelo.entidade;

import java.io.Serializable;

import afrcode.fwarquitetura.modelo.entidade.dao.IDao;
import afrcode.fwarquitetura.util.excecao.ExcecaoNegocio;

/**
 * Interface de definição de uma Entidade, objeto persistível em banco de dados.
 * 
 * Esta interface prima pela criação de Entidades RICAS com métodos além de get/set de atributos. Ou seja, entidades devem
 * implementar suas respectivas regras de negócio.
 * 
 * Requisito mínimo: ter um ID do tipo TIPOID (parametrizado).
 * 
 * @author alyssonfr
 * 
 * @param <TIPOID> Tipo do ID (Long, Integer, String, etc.)
 */
public interface IEntidade<TIPOID extends Comparable<TIPOID>> extends Serializable {

    /**
     * Método de obtenção da propriedade id.
     * 
     * @return id
     */
    public TIPOID getId();

    /**
     * Método de atribuição da propriedade id.
     * 
     * @param id id
     */
    public void setId(TIPOID id);

    /**
     * Método para atribuição de valores persistíveis à entidade, tornando-a persistível sem erros.
     * Deve ser acionado automaticamente pelo método {@link IDao#instaciarObjetoPersistivelParaTestes()} de implementações de
     * {@link IDao}.
     */
    public void preencherComValoresPersistiveis();

    /**
     * Método a patir do qual devem ser acionadas rotinas (outros métodos internos a entidade) de validação necessárias
     * ao salvar a entidade.
     * 
     * Os métodos acionados a partir deste método podem fazer uso do recurso Method Validation do Hibernate Validator.
     * 
     * Deve ser acionado automaticamente pelo método {@link IDao#salvar(Entidade)} de implementações de {@link IDao}.
     * 
     * @throws ExcecaoNegocio exceção de negócio conhecida cuja messagem associada a um código será exibida ao usuário
     * 
     */
    public void validarSalvamento();

    /**
     * Método a partir do qual devem ser acionadas rotinas (outros métodos internos a entidade) de validação necessárias
     * ao excluir a entidade.
     * 
     * Os métodos acionados a partir deste método podem fazer uso do recurso Method Validation do Hibernate Validator.
     * 
     * Deve ser acionado automaticamente pelo método {@link IDao#excluir(Entidade)} de implementações de {@link IDao}.
     * 
     * @throws ExcecaoNegocio exceção de negócio conhecida cuja messagem associada a um código será exibida ao usuário
     * 
     */
    public void validarExclusao();

}
