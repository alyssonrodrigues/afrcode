/**
 * 
 */
package cursojsf.modelo.entidade.dao;

import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.springframework.stereotype.Repository;

import cursojsf.modelo.entidade.EntidadeStub;

/**
 * @author alysson
 *
 */
@BypassInterceptors
@Repository
public class DaoEntidadeStub extends DaoJpaAbstrato<EntidadeStub> {

}
