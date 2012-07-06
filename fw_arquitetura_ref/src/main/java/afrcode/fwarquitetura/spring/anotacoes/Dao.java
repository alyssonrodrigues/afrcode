package afrcode.fwarquitetura.spring.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Anotação para uso em componentes do tipo {@link afrcode.fwarquitetura.modelo.entidade.dao.IDao} (camada Model do MVC).
 * 
 * Ao usar esta anotação um contexto transacional é associado implicitamente ao componente através da anotação
 * {@link Transactional} com propagação do tipo {@link Propagation#REQUIRED} - usa-se a transação existente, se existir, ou
 * cria-se uma nova caso contrário.
 * 
 * TODO: Verificar se é desejável associar contexto transacional obrigatório neste tipo de componente.
 * 
 * @author alyssonfr
 * 
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public @interface Dao {

}
