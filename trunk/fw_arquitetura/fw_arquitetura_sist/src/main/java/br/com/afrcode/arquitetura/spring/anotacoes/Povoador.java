package br.com.afrcode.arquitetura.spring.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

/**
 * Anotação para uso em componentes do tipo Povoador.
 * 
 * Ao usar esta anotação um contexto transacional é associado implicitamente ao
 * componente através da anotação Transactional com propagação do tipo
 * Propagation#REQUIRED - usa-se a transação existente, se existir, ou cria-se
 * uma nova caso contrário.
 * 
 * 
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
@Transactional(propagation = Propagation.REQUIRED)
@Profile(Profiles.PROFILE_TU)
public @interface Povoador {

}
