package br.com.afrcode.arquitetura.spring.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Anota��o para uso em componentes do tipo gen�rico Component (camada Model do
 * MVC).
 * 
 * Ao usar esta anota��o um contexto transacional � associado implicitamente ao
 * componente atrav�s da anota��o Transactional com propaga��o do tipo
 * Propagation#REQUIRED - usa-se a transa��o existente, se existir, ou cria-se
 * uma nova caso contr�rio.
 * 
 * 
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Transactional(propagation = Propagation.REQUIRED)
public @interface Componente {

}
