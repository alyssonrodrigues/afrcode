package afrcode.fwarquitetura.spring.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Anotação para uso em componentes do tipo Controlador (camada Controller do MVC).
 * 
 * Ao usar esta anotação um contexto transacional é associado implicitamente ao componente através da anotação
 * {@link Transactional} com propagação do tipo {@link Propagation#REQUIRED} - usa-se a transação existente, se existir, ou
 * cria-se uma nova caso contrário.
 * 
 * @author alyssonfr
 * 
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@Transactional(propagation = Propagation.REQUIRED)
public @interface Controlador {

}
