package afrcode.fwarquitetura.test.modelo.entidade.objetopersistente;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import afrcode.fwarquitetura.modelo.entidade.ObjetoPersistenteAbstrato;

/**
 * Classe de entidade para teste e validação inicial do frameworkarquitetura.
 * 
 * ATENÇÃO: Esta entidade encontra-se em src/test/java pois é uma Entidade para TESTE inicial do frameworkarquitetura e somente
 * por isto! Entidades da aplicação devem estar em src/main/java!
 * 
 * @author alyssonfr
 * 
 */
@Entity
public class UmObjetoPersistente extends ObjetoPersistenteAbstrato<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min = 3, max = 100)
    @NotNull
    private String descricao;

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
        setDescricao("Uma descrição");
    }

    @Override
    public void validarSalvamento() {

    }

    @Override
    public void validarExclusao() {

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

}
