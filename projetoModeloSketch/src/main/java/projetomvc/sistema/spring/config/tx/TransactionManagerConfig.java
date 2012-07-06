package projetomvc.sistema.spring.config.tx;

import static projetomvc.sistema.spring.util.Profiles.PROFILE_DESENV;
import static projetomvc.sistema.spring.util.Profiles.PROFILE_PROD;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
@Profile({PROFILE_DESENV, PROFILE_PROD})
public class TransactionManagerConfig {
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager =
			new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}

}
