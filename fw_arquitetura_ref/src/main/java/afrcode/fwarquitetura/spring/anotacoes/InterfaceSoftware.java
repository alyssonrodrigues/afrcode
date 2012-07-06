package afrcode.fwarquitetura.spring.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Anota��o para uso em componentes do tipo InterfaceSoftware (camada de exposi��o de servi�os ao mundo externo).
 * 
 * Ao usar esta anota��o um contexto transacional � associado implicitamente ao componente atrav�s da anota��o
 * {@link Transactional} com propaga��o do tipo {@link Propagation#REQUIRED} - usa-se a transa��o existente, se existir, ou
 * cria-se uma nova caso contr�rio.
 * 
 * @author alyssonfr
 * 
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Transactional(propagation = Propagation.REQUIRED)
public @interface InterfaceSoftware {

}
