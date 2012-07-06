package afrcode.fwarquitetura.modelo.entidade;

/**
 * Superclasse abstrata para entidades segundo defini��o de entidade em {@link IEntidade}.
 * 
 * Respons�vel por uniformizar a implementa��o dos m�todos {@link #equals(Object)} e {@link #hashCode()}.
 * 
 * @author alyssonfr
 * 
 * @param <TIPOID> Tipo do ID (Long, Integer, String, etc.)
 */
public abstract class EntidadeAbstrata<TIPOID> implements IEntidade<TIPOID> {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        }
        EntidadeAbstrata ent = (EntidadeAbstrata) obj;
        if (getId() != null) {
            return getId().equals(ent.getId());
        }
        else {
            return false;
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

}
