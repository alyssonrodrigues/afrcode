/**
 * 
 */
package cursojsf.modelo.entidade.chamado.dao;

import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.springframework.stereotype.Repository;

import cursojsf.modelo.entidade.chamado.Responsavel;
import cursojsf.modelo.entidade.dao.DaoJpaAbstrato;

/**
 * @author alysson
 *
 */
@BypassInterceptors
@Repository
public class DaoResponsavel extends DaoJpaAbstrato<Responsavel> {

}
