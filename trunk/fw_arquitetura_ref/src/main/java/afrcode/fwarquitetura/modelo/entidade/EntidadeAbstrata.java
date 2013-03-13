package afrcode.fwarquitetura.modelo.entidade;

import org.apache.commons.lang.ObjectUtils;

/**
 * Superclasse abstrata para entidades segundo definição de entidade em {@link IEntidade}.
 * 
 * Responsável por uniformizar a implementação dos métodos {@link #equals(Object)} e {@link #hashCode()}.
 * 
 * @author alyssonfr
 * 
 * @param <TIPOID> Tipo do ID (Long, Integer, String, etc.)
 */
public abstract class EntidadeAbstrata<TIPOID extends Comparable<TIPOID>>
    implements IEntidade<TIPOID>, Comparable<IEntidade<TIPOID>> {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else {
        	EntidadeAbstrata ent = (EntidadeAbstrata) obj;
        	return ObjectUtils.equals(getId(), ent.getId());
        }
    }

    @Override
    public int hashCode() {
        TIPOID aux = getId();
        if (aux != null) {
            return aux.hashCode();
        }
        return super.hashCode();
    }
    
    @Override
    public int compareTo(IEntidade<TIPOID> obj) {
        if (this == obj) {
            return 0;
        } else if (obj == null) {
            return -1;
        } else {
        	return ObjectUtils.compare(getId(), obj.getId());
        }
    }

}
