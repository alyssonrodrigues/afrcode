package afrcode.projetoweb.sgos.modelo.entidade.contrato;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import afrcode.fwarquitetura.modelo.entidade.ObjetoPersistenteAbstrato;
import afrcode.projetoweb.sgos.modelo.entidade.ordemservico.OrdemServico;

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
@Table(name = "CONTRATO")
public class Contrato extends ObjetoPersistenteAbstrato<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Long numero;

    @Digits(integer = 9, fraction = 2)
    @NotNull
    private BigDecimal valor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contrato", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @Access(AccessType.FIELD)
    private Set<OrdemServico> ordensServico;

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
        setValor(BigDecimal.ONE);
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
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * @return the ordensServico
     */
    public Set<OrdemServico> getOrdensServico() {
        iniciarOrdensServico();
        return Collections.unmodifiableSet(ordensServico);
    }

    public void adicionarOrdemServico(OrdemServico ordemServico) {
        iniciarOrdensServico();
        ordensServico.add(ordemServico);
        ordemServico.setContrato(this);
    }

    public void removerOrdemServico(OrdemServico ordemServico) {
        iniciarOrdensServico();
        ordensServico.remove(ordemServico);
        ordemServico.setContrato(null);
    }

    private void iniciarOrdensServico() {
        if (ordensServico == null) {
            ordensServico = new TreeSet<OrdemServico>();
        }

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
