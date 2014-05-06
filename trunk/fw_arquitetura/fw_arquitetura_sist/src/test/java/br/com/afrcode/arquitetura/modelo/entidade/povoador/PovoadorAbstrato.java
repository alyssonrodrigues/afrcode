package br.com.afrcode.arquitetura.modelo.entidade.povoador;

import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;
import br.com.afrcode.arquitetura.teste.unitario.spring.config.SpringTestConfig;

/**
 * Superclasse de povoadores de BD.
 * 
 * Uso típico: Ver PovoadorUmObjetoPersistente.
 * 
 * 
 */
public abstract class PovoadorAbstrato {
	protected static final Logger LOG = Logger
			.getLogger(PovoadorAbstrato.class);

	private static final DefaultTransactionDefinition DEFAUL_TRANSACTION_DEFINITION = new DefaultTransactionDefinition();

	@Autowired
	private JpaTransactionManager transactionManager;

	private TransactionStatus transactionStatus;

	private Class<? extends PovoadorAbstrato> classePovoador;

	public PovoadorAbstrato(Class<? extends PovoadorAbstrato> classePovoador) {
		this.classePovoador = classePovoador;
		System.setProperty("spring.profiles.active", Profiles.PROFILE_TU);
	}

	public PovoadorAbstrato() {
	}

	/**
	 * Método de execução do povoador em contexto transacional via métodos main
	 * de povoadores concretos.
	 */
	public void executar() {
		try {
			init();
		} catch (Throwable e) {
			LOG.error("Erro ao povoador dados ...", e);
		} finally {
			System.exit(0);
		}
	}

	/**
	 * Método de iniciação de um povoador qualquer durante execuções via métodos
	 * main de povoadores concretos.
	 */
	protected void init() {
		// Sinalizador de execução de povoador standAlone (via método main de um
		// povoador concreto).
		// Sinalizador em uso por HsqldbUtil.
		System.setProperty("executandoPovoadorStandAlone", "true");
		Validate.notNull(classePovoador,
				"Povoador sem classe concreta informada!");
		AnnotationConfigApplicationContext annAppCtx = new AnnotationConfigApplicationContext();
		annAppCtx.register(SpringTestConfig.class);
		annAppCtx.getEnvironment().setActiveProfiles(Profiles.PROFILE_TU);
		annAppCtx.refresh();
		PovoadorAbstrato povoador = annAppCtx.getBean(classePovoador);
		povoador.iniciarTransacao();
		povoador.povoar();
		povoador.finalizarTransacao();
		annAppCtx.close();
	}

	protected void iniciarTransacao() {
		TransactionStatus ts = getTransactionManager().getTransaction(
				DEFAUL_TRANSACTION_DEFINITION);
		setTransactionStatus(ts);
		LOG.info("Iniciando contexto transacional ...");
	}

	protected void finalizarTransacao() {
		try {
			getTransactionManager().commit(getTransactionStatus());
			LOG.info("Finalizando contexto transacional ...");
		} finally {
			setTransactionStatus(null);
		}
	}

	protected JpaTransactionManager getTransactionManager() {
		return transactionManager;
	}

	protected TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	protected void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	/**
	 * Método responsável por povoador dados em BD.
	 */
	public abstract void povoar();

}
