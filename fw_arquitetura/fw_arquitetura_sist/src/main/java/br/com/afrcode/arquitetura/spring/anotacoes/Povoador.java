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
 * Anota��o para uso em componentes do tipo Povoador.
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
@Service
@Transactional(propagation = Propagation.REQUIRED)
@Profile(Profiles.PROFILE_TU)
public @interface Povoador {

}
