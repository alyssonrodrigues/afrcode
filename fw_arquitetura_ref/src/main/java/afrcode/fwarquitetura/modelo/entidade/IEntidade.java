package afrcode.fwarquitetura.modelo.entidade;

import java.io.Serializable;

import afrcode.fwarquitetura.modelo.entidade.dao.IDao;
import afrcode.fwarquitetura.util.excecao.ExcecaoNegocio;

/**
 * Interface de defini��o de uma Entidade, objeto persist�vel em banco de dados.
 * 
 * Esta interface prima pela cria��o de Entidades RICAS com m�todos al�m de get/set de atributos. Ou seja, entidades devem
 * implementar suas respectivas regras de neg�cio.
 * 
 * Requisito m�nimo: ter um ID do tipo TIPOID (parametrizado).
 * 
 * @author alyssonfr
 * 
 * @param <TIPOID> Tipo do ID (Long, Integer, String, etc.)
 */
public interface IEntidade<TIPOID extends Comparable<TIPOID>> extends Serializable {

    /**
     * M�todo de obten��o da propriedade id.
     * 
     * @return id
     */
    public TIPOID getId();

    /**
     * M�todo de atribui��o da propriedade id.
     * 
     * @param id id
     */
    public void setId(TIPOID id);

    /**
     * M�todo para atribui��o de valores persist�veis � entidade, tornando-a persist�vel sem erros.
     * Deve ser acionado automaticamente pelo m�todo {@link IDao#instaciarObjetoPersistivelParaTestes()} de implementa��es de
     * {@link IDao}.
     */
    public void preencherComValoresPersistiveis();

    /**
     * M�todo a patir do qual devem ser acionadas rotinas (outros m�todos internos a entidade) de valida��o necess�rias
     * ao salvar a entidade.
     * 
     * Os m�todos acionados a partir deste m�todo podem fazer uso do recurso Method Validation do Hibernate Validator.
     * 
     * Deve ser acionado automaticamente pelo m�todo {@link IDao#salvar(Entidade)} de implementa��es de {@link IDao}.
     * 
     * @throws ExcecaoNegocio exce��o de neg�cio conhecida cuja messagem associada a um c�digo ser� exibida ao usu�rio
     * 
     */
    public void validarSalvamento();

    /**
     * M�todo a partir do qual devem ser acionadas rotinas (outros m�todos internos a entidade) de valida��o necess�rias
     * ao excluir a entidade.
     * 
     * Os m�todos acionados a partir deste m�todo podem fazer uso do recurso Method Validation do Hibernate Validator.
     * 
     * Deve ser acionado automaticamente pelo m�todo {@link IDao#excluir(Entidade)} de implementa��es de {@link IDao}.
     * 
     * @throws ExcecaoNegocio exce��o de neg�cio conhecida cuja messagem associada a um c�digo ser� exibida ao usu�rio
     * 
     */
    public void validarExclusao();

}
