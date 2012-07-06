/**
 * 
 */
package cursojsf.modelo.entidade;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.NotNull;

/**
 * @author alysson
 *
 */
@Entity
@SequenceGenerator(name = "SEQ_STORE", sequenceName = "SEQ_ENTIDADESTUB")
public class EntidadeStub extends ObjetoPersistente {
	private String stub;

	@NotNull
	public String getStub() {
		return stub;
	}

	public void setStub(String stub) {
		this.stub = stub;
	}
	
	@Override
	public void preencherComValoresDefault() {
		super.preencherComValoresDefault();
		this.setStub("Um valor qualquer!");
	}

}
