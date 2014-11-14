package br.com.afrcode.arquitetura.modelo.entidade;

import org.apache.commons.lang.ObjectUtils;

/**
 * Superclasse abstrata para entidades segundo definição de entidade em
 * IEntidade.
 * 
 * Responsável por uniformizar a implementação dos métodos equals(Object),
 * hashCode() e compareTo(IEntidade).
 * 
 * 
 * @param <T>
 *            Tipo do ID (Long, Integer, String, etc.)
 */
public abstract class EntidadeAbstrata<T extends Comparable<T>> implements
		IEntidade<T>, Comparable<IEntidade<T>> {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (!EntidadeAbstrata.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		EntidadeAbstrata ent = (EntidadeAbstrata) obj;
		if (getId() == null) {
			return false;
		} else {
			return getId().equals(ent.getId());
		}
	}

	@Override
	public int hashCode() {
		T aux = getId();
		if (aux != null) {
			return aux.hashCode();
		}
		return super.hashCode();
	}

	/**
	 * Null safe comparison of Comparables. {@code null} is assumed to be less
	 * than a non-{@code null} value.
	 * 
	 * Veja ObjectUtils#compare(Comparable, Comparable).
	 */
	@Override
	public int compareTo(IEntidade<T> o) {
		int r = -1;
		if (o != null) {
			if (getId() == null && o.getId() == null) {
				r = ObjectUtils.compare(this.hashCode(), o.hashCode());
			} else {
				r = ObjectUtils.compare(getId(), o.getId());
			}
		}
		return r;
	}
}
