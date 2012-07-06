package afrcode.fwarquitetura.test.modelo.entidade.objetopersistente.dao;

import afrcode.fwarquitetura.modelo.entidade.dao.DaoHibernateJpaAbstrato;
import afrcode.fwarquitetura.spring.anotacoes.Dao;
import afrcode.fwarquitetura.test.modelo.entidade.objetopersistente.UmObjetoPersistente;

/**
 * Dao para teste e valida��o inicial do frameworkarquitetura.
 * 
 * ATEN��O: Este Dao encontra-se em src/test/java pois � um Dao para TESTE inicial do frameworkarquitetura e somente por isto!
 * Daos da aplica��o devem estar em src/main/java!
 * 
 * @author alyssonfr
 * 
 */
@Dao
public class DaoUmObjetoPersistente extends DaoHibernateJpaAbstrato<Long, UmObjetoPersistente> {

}
