package afrcode.projetoweb.sgos.modelo.entidade.ordemservico;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import afrcode.fwarquitetura.modelo.entidade.ObjetoPersistenteAbstrato;
import afrcode.projetoweb.sgos.modelo.entidade.contrato.Contrato;

/**
 * <b>ATENÇÃO</b>
 * 
 * CLASSE PARA TESTE DE IMPLEMENTAÇÃO DE CRUD.
 * 
 * NÃO HÁ COMPROMISSO DE ADERÊNCIA TOTAL AS DEFINIÇÕES PRÉVIAS DA ARQUITETURA POR SE TRATAR DE UM TESTE DE ADEQUAÇÃO E USO!
 * 
 * @author alyssonfr
 * 
 */
@Entity
@Table(name = "ORDEM_SERVICO")
public class OrdemServico extends ObjetoPersistenteAbstrato<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Long numero;

    @Length(min = 3, max = 100)
    @NotNull
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @NotNull
    private Contrato contrato;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void preencherComValoresPersistiveis() {
        setNumero(new Long(hashCode()));
        setDescricao("Uma descrição qualquer!");
        Contrato umContrato = new Contrato();
        umContrato.preencherComValoresPersistiveis();
        umContrato.adicionarOrdemServico(this);
    }

    @Override
    public void validarSalvamento() {
        // TODO Preencher stub gerado automaticamente por alyssonfr

    }

    @Override
    public void validarExclusao() {
        // TODO Preencher stub gerado automaticamente por alyssonfr

    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the contrato
     */
    public Contrato getContrato() {
        return contrato;
    }

    /**
     * @param contrato the contrato to set
     */
    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    /**
     * @return the numero
     */
    public Long getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Long numero) {
        this.numero = numero;
    }

}
