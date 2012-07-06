package afrcode.fwarquitetura.test.modelo.entidade.objetopersistente.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import afrcode.fwarquitetura.modelo.entidade.dao.DaoJpaAbstrato;
import afrcode.fwarquitetura.modelo.entidade.dao.IDao;
import afrcode.fwarquitetura.test.modelo.entidade.objetopersistente.UmObjetoPersistente;
import afrcode.fwarquitetura.test.util.dao.TesteDaoObjetoPersistenteAbstrato;

/**
 * Classe de teste de {@link IDao} para teste e validação inicial do frameworkarquitetura.
 * 
 * @author alyssonfr
 * 
 */
public class TesteDaoUmObjetoPersistente extends TesteDaoObjetoPersistenteAbstrato<Long, UmObjetoPersistente> {

    @Autowired
    private DaoUmObjetoPersistente daoUmObjetoPersistente;

    @Autowired
    private Validator validator;

    @Override
    protected DaoJpaAbstrato<Long, UmObjetoPersistente> getDao() {
        return daoUmObjetoPersistente;
    }

    /**
     * Este teste visa apenas validar o uso do Hibernate Validator pelo frameworkarquitetura.
     * 
     * ATENÇÃO: Não é necessário produzir este tipo de teste para entidades reais de um sistema!
     */
    @Test
    public void testarNotNullConstraintViolationExceptionLancadaAoSalvar() {
        UmObjetoPersistente umObj = daoUmObjetoPersistente.instaciarObjetoPersistivelParaTestes();
        String descricaoInvalida = null;
        umObj.setDescricao(descricaoInvalida);
        try {
            daoUmObjetoPersistente.salvar(umObj);
            daoUmObjetoPersistente.sincronizar(); // É necessário disparar o evento flush pois as validações são disparadas por
                                                  // evento (insert/update/remove).
            fail("Uma ConstraintViolationException para NotNull deveria ser lançada!");
        } catch (ConstraintViolationException cve) {
            Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
            assertEquals("Uma ConstraingViolation deveria existir!", 1, constraintViolations.size());
            ConstraintViolation<?> constraintViolation = constraintViolations.iterator().next();
            assertEquals("Uma ConstraingViolation para NotNull deveria existir!", descricaoInvalida,
                    constraintViolation.getInvalidValue());
        }
    }

    /**
     * Este teste visa apenas validar o uso do Hibernate Validator pelo frameworkarquitetura.
     * 
     * ATENÇÃO: Não é necessário produzir este tipo de teste para entidades reais de um sistema!
     */
    @Test
    public void testarNotNullConstraintViolationExistente() {
        UmObjetoPersistente umObj = daoUmObjetoPersistente.instaciarObjetoPersistivelParaTestes();
        String descricaoInvalida = null;
        umObj.setDescricao(descricaoInvalida);
        Set<ConstraintViolation<UmObjetoPersistente>> constraintViolations = validator.validate(umObj);
        assertEquals("Uma ConstraingViolation deveria existir!", 1, constraintViolations.size());
        ConstraintViolation<UmObjetoPersistente> constraintViolation = constraintViolations.iterator().next();
        assertEquals("Uma ConstraingViolation para NotNull deveria existir!", descricaoInvalida,
                constraintViolation.getInvalidValue());
    }

    /**
     * Este teste visa apenas validar o uso do Hibernate Validator pelo frameworkarquitetura.
     * 
     * ATENÇÃO: Não é necessário produzir este tipo de teste para entidades reais de um sistema!
     */
    @Test
    public void testarLengthConstraintValidationExistente() {
        UmObjetoPersistente umObj = daoUmObjetoPersistente.instaciarObjetoPersistivelParaTestes();
        String descricaoInvalida = "0";
        umObj.setDescricao(descricaoInvalida);
        Set<ConstraintViolation<UmObjetoPersistente>> constraintViolations = validator.validate(umObj);
        assertEquals("Uma ConstraingViolation deveria existir!", 1, constraintViolations.size());
        ConstraintViolation<UmObjetoPersistente> constraintViolation = constraintViolations.iterator().next();
        assertEquals("Uma ConstraingViolation para Length deveria existir!", descricaoInvalida,
                constraintViolation.getInvalidValue());
    }

}
