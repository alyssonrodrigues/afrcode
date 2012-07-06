package afrcode.fwarquitetura.modelo.entidade;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Superclasse dos objetos persistentes {@link MappedSuperclass}.
 * Definição da estratégia de controle de lock otimista através do campo versao.
 * 
 * @author alyssonfr
 * 
 * @param <TIPOID> Tipo do ID (Long, Integer, String, etc.)
 * 
 */
@MappedSuperclass
public abstract class ObjetoPersistenteAbstrato<TIPOID> extends EntidadeAbstrata<TIPOID> {

    @Version
    // TODO: usar @Column
    private Long versao;

    /**
     * @return the versao
     */
    public Long getVersao() {
        return versao;
    }

    /**
     * @param versao the versao to set
     */
    public void setVersao(Long versao) {
        this.versao = versao;
    }

}
