/**
 * 
 */
package cursojsf.test.modelo.entidade.povoador;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import cursojsf.test.modelo.entidade.util.TesteContextUtil;

/**
 * @author alysson
 *
 */
public abstract class Povoador {
	protected static final Logger LOG = Logger.getLogger(Povoador.class);
	
	private static final DefaultTransactionDefinition
    DEFAUL_TRANSACTION_DEFINITION = new DefaultTransactionDefinition();

	private JtaTransactionManager transactionManager;

	private TransactionStatus transactionStatus;
	
	private Class<? extends Povoador> classePovoador;
	
	public Povoador(Class<? extends Povoador> classePovoador) {
		this.classePovoador = classePovoador;
	}
	
	public Povoador() {
	}
	
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
	 * Método de iniciação de um povoador qualquer.
	 */
	protected void init() {
		Validate.notNull(classePovoador, "Povoador sem classe concreta informada!");
		ClassPathXmlApplicationContext xmlAppCtx =
			new ClassPathXmlApplicationContext(TesteContextUtil.CONFIG_LOCATIONS);
		String nomeBeanPovoador = classePovoador.getSimpleName();
		nomeBeanPovoador = StringUtils.lowerCase(nomeBeanPovoador.substring(0, 1)) +
			nomeBeanPovoador.substring(1, nomeBeanPovoador.length());
		Povoador povoador = (Povoador) xmlAppCtx.getBean(
				nomeBeanPovoador);
		povoador.iniciarTransacao();
		povoador.povoar();
		povoador.finalizarTransacao();
		xmlAppCtx.close();
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
	
	/**
	 * Método responsável por povoador dados em BD.
	 */
	public abstract void povoar();

	@Required
	public void setTransactionManager(JtaTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	protected JtaTransactionManager getTransactionManager() {
		return transactionManager;
	}

	protected TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	protected void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

}
