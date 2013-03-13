package afrcode.fwarquitetura.test.modelo.entidade.povoador;

import static afrcode.fwarquitetura.spring.config.util.Profiles.PROFILE_TESTES;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import afrcode.fwarquitetura.test.modelo.entidade.objetopersistente.povoador.PovoadorUmObjetoPersistente;
import afrcode.fwarquitetura.tu.spring.config.SpringTestConfig;

/**
 * Superclasse de povoadores de BD.
 * 
 * Uso típico: Ver {@link PovoadorUmObjetoPersistente}.
 * 
 * @author alyssonfr
 * 
 */
public abstract class PovoadorAbstrato {
    protected static final Logger LOG = Logger.getLogger(PovoadorAbstrato.class);

    private static final DefaultTransactionDefinition DEFAUL_TRANSACTION_DEFINITION = new DefaultTransactionDefinition();

    @Autowired
    private JpaTransactionManager transactionManager;

    private TransactionStatus transactionStatus;

    private Class<? extends PovoadorAbstrato> classePovoador;

    public PovoadorAbstrato(Class<? extends PovoadorAbstrato> classePovoador) {
        this.classePovoador = classePovoador;
    }

    public PovoadorAbstrato() {
    }

    /**
     * Método de execução do povoador em contexto transacional.
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
     * Método de iniciação de um povoador qualquer.
     */
    protected void init() {
        Validate.notNull(classePovoador, "Povoador sem classe concreta informada!");
        System.setProperty("spring.profiles.active", PROFILE_TESTES);
        AnnotationConfigApplicationContext annAppCtx = new AnnotationConfigApplicationContext();
        annAppCtx.register(SpringTestConfig.class);
        annAppCtx.getEnvironment().setActiveProfiles(PROFILE_TESTES);
        annAppCtx.refresh();
        String nomeBeanPovoador = classePovoador.getSimpleName();
        nomeBeanPovoador = StringUtils.lowerCase(nomeBeanPovoador.substring(0, 1)) +
                nomeBeanPovoador.substring(1, nomeBeanPovoador.length());
        PovoadorAbstrato povoador = (PovoadorAbstrato) annAppCtx.getBean(
                nomeBeanPovoador);
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
