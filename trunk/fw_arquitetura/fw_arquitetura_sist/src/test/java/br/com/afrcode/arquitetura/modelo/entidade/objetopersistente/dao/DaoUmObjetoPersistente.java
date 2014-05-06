package br.com.afrcode.arquitetura.modelo.entidade.objetopersistente.dao;

import br.com.afrcode.arquitetura.modelo.entidade.dao.DaoHibernateJpaAbstrato;
import br.com.afrcode.arquitetura.modelo.entidade.objetopersistente.UmObjetoPersistente;
import br.com.afrcode.arquitetura.spring.anotacoes.Dao;

/**
 * Dao para teste e validação inicial do frameworkarquitetura.
 * 
 * ATENÇÃO: Este Dao encontra-se em src/test/java pois é um Dao para TESTE inicial do frameworkarquitetura e somente por isto!
 * Daos da aplicação devem estar em src/main/java!
 * 
 * 
 */
@Dao
public class DaoUmObjetoPersistente extends DaoHibernateJpaAbstrato<Long, UmObjetoPersistente> {

}
