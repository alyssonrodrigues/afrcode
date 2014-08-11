package br.com.afrcode.arquitetura.modelo.entidade.objetopersistente;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import br.com.afrcode.arquitetura.modelo.entidade.ObjetoPersistenteAbstrato;

/**
 * Classe de entidade para teste e validação inicial do frameworkarquitetura.
 * 
 * ATENÇÃO: Esta entidade encontra-se em src/test/java pois é uma Entidade para
 * TESTE inicial do frameworkarquitetura e somente por isto! Entidades da
 * aplicação devem estar em src/main/java!
 * 
 * 
 */
@Entity
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "SEQ_UM_OBJ_PERSISTENTE")
public class UmObjetoPersistente extends ObjetoPersistenteAbstrato {

    @Length(min = 3, max = 100)
    @NotNull
    private String descricao;

    @Past
    private Calendar data;

    @CPF
    private String numCpf;

    @Override
    public void preencherComValoresPersistiveis() {
        setDescricao("Uma descrição");
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao
     *            the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getNumCpf() {
        return numCpf;
    }

    public void setNumCpf(String numCpf) {
        this.numCpf = numCpf;
    }

}
