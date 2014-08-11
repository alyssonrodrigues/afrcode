package br.com.afrcode.arquitetura.modelo.entidade.objetopersistente.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.afrcode.arquitetura.modelo.entidade.dao.DaoJpaAbstrato;
import br.com.afrcode.arquitetura.modelo.entidade.objetopersistente.UmObjetoPersistente;
import br.com.afrcode.arquitetura.util.dao.AbstractDaoObjetoPersistenteEmMemoriaAbstratoTest;

/**
 * Classe de teste de IDao para teste e valida��o inicial do
 * frameworkarquitetura.
 * 
 * 
 */
public class DaoUmObjetoPersistenteTest extends
        AbstractDaoObjetoPersistenteEmMemoriaAbstratoTest<Long, UmObjetoPersistente> {

    @Autowired
    private DaoUmObjetoPersistente daoUmObjetoPersistente;

    @Autowired
    private Validator validator;

    @Override
    protected DaoJpaAbstrato<Long, UmObjetoPersistente> getDao() {
        return daoUmObjetoPersistente;
    }

    @Test
    public void testarRecuperarTodosComPaginacao() {
        List<UmObjetoPersistente> objs = new ArrayList<UmObjetoPersistente>();
        for (int i = 0; i < 100; i++) {
            UmObjetoPersistente umObj = daoUmObjetoPersistente.instanciarObjetoPersistivel();
            daoUmObjetoPersistente.salvar(umObj);
            objs.add(umObj);
        }

        Collection<UmObjetoPersistente> objsPaginaZero = daoUmObjetoPersistente.recuperarTodos(0, 50);
        Assert.assertTrue("Deveriam ter sido recuperados 50 objetos para a p�gina 0!", !objsPaginaZero.isEmpty());
        Assert.assertEquals("Deveriam ter sido recuperados 50 objetos para a p�gina 0!", 50, objsPaginaZero.size());
        Assert.assertTrue("Os objetos recuperados para a p�gina 0 s�o diferentes dos objetos esperados!",
                objsPaginaZero.containsAll(objs.subList(0, 50)));

        Collection<UmObjetoPersistente> objsPaginaUm = daoUmObjetoPersistente.recuperarTodos(1, 50);
        Assert.assertTrue("Deveriam ter sido recuperados 50 objetos para a p�gina 1!", !objsPaginaUm.isEmpty());
        Assert.assertEquals("Deveriam ter sido recuperados 50 objetos para a p�gina 1!", 50, objsPaginaUm.size());
        Assert.assertTrue("Os objetos recuperados para a p�gina 0 s�o diferentes dos objetos esperados!",
                objsPaginaUm.containsAll(objs.subList(50, 100)));
    }

    @Test
    public void testarRecuperarObjetos() {
        boolean primeiro = true;
        for (int i = 0; i < 5; i++) {
            UmObjetoPersistente umObj = daoUmObjetoPersistente.instanciarObjetoPersistivel();
            if (primeiro) {
                umObj.setDescricao("O escolhido!");
                primeiro = false;
            }
            daoUmObjetoPersistente.salvar(umObj);
        }

        String qlString =
                "from " + UmObjetoPersistente.class.getName() + " as obj where obj.descricao = :param_descricao";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("param_descricao", "O escolhido!");

        Collection<UmObjetoPersistente> objsPaginaZero =
                daoUmObjetoPersistente.recuperarObjetos(qlString, params, 0, 5);
        Assert.assertEquals("Deveriam ter sido recuperado 1 objeto para a p�gina 0!", 1, objsPaginaZero.size());
        UmObjetoPersistente[] vetorObjsPaginaZero =
                objsPaginaZero.toArray(new UmObjetoPersistente[objsPaginaZero.size()]);
        Assert.assertEquals("O primeiro objeto da p�gina 0 deveria ter a descricao igual a \"O escolhido!\"",
                "O escolhido!", vetorObjsPaginaZero[0].getDescricao());

    }

    /**
     * Este teste visa apenas validar o uso do Hibernate Validator pelo
     * frameworkarquitetura.
     * 
     * ATEN��O: N�o � necess�rio produzir este tipo de teste para entidades
     * reais de um sistema!
     */
    @Test
    public void testarNotNullConstraintViolationExceptionLancadaAoSalvar() {
        UmObjetoPersistente umObj = daoUmObjetoPersistente.instanciarObjetoPersistivel();
        String descricaoInvalida = null;
        umObj.setDescricao(descricaoInvalida);
        try {
            daoUmObjetoPersistente.salvar(umObj);
            daoUmObjetoPersistente.sincronizar(); // � necess�rio disparar o
                                                  // evento flush pois as
                                                  // valida��es s�o disparadas
                                                  // por
                                                  // evento
                                                  // (insert/update/remove).
            Assert.fail("Uma ConstraintViolationException para NotNull deveria ser lan�ada!");
        } catch (ConstraintViolationException cve) {
            Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
            Assert.assertEquals("Uma ConstraingViolation deveria existir!", 1, constraintViolations.size());
            ConstraintViolation<?> constraintViolation = constraintViolations.iterator().next();
            Assert.assertEquals("Uma ConstraingViolation para NotNull deveria existir!", descricaoInvalida,
                    constraintViolation.getInvalidValue());
        }
    }

    /**
     * Este teste visa apenas validar o uso do Hibernate Validator pelo
     * frameworkarquitetura.
     * 
     * ATEN��O: N�o � necess�rio produzir este tipo de teste para entidades
     * reais de um sistema!
     */
    @Test
    public void testarNotNullConstraintViolationExistente() {
        UmObjetoPersistente umObj = daoUmObjetoPersistente.instanciarObjetoPersistivel();
        String descricaoInvalida = null;
        umObj.setDescricao(descricaoInvalida);
        Set<ConstraintViolation<UmObjetoPersistente>> constraintViolations = validator.validate(umObj);
        Assert.assertEquals("Uma ConstraingViolation deveria existir!", 1, constraintViolations.size());
        ConstraintViolation<UmObjetoPersistente> constraintViolation = constraintViolations.iterator().next();
        Assert.assertEquals("Uma ConstraingViolation para NotNull deveria existir!", descricaoInvalida,
                constraintViolation.getInvalidValue());
    }

    /**
     * Este teste visa apenas validar o uso do Hibernate Validator em rela��o a
     * anota��o @Past.
     * 
     * ATEN��O: N�o � necess�rio produzir este tipo de teste para entidades
     * reais de um sistema!
     */
    @Test
    public void testarPastConstraintViolationExceptionLancadaAoSalvar() {
        UmObjetoPersistente umObj = daoUmObjetoPersistente.instanciarObjetoPersistivel();
        Calendar dataAtual = Calendar.getInstance();
        dataAtual.add(Calendar.MONTH, 5);
        umObj.setData(dataAtual);
        try {
            daoUmObjetoPersistente.salvar(umObj);
            daoUmObjetoPersistente.sincronizar(); // � necess�rio disparar o
                                                  // evento flush pois as
                                                  // valida��es s�o disparadas
                                                  // por
                                                  // evento
                                                  // (insert/update/remove).
            Assert.fail("Uma ConstraintViolationException para Past deveria ser lan�ada!");
        } catch (ConstraintViolationException cve) {
            Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
            Assert.assertEquals("Uma ConstraingViolation deveria existir!", 1, constraintViolations.size());
            ConstraintViolation<?> constraintViolation = constraintViolations.iterator().next();
            Assert.assertEquals("Uma ConstraingViolation para Past deveria existir!", dataAtual,
                    constraintViolation.getInvalidValue());
        }
    }

    /**
     * Este teste visa apenas validar o uso do Hibernate Validator em rela��o a
     * anota��o @Past.
     * 
     * ATEN��O: N�o � necess�rio produzir este tipo de teste para entidades
     * reais de um sistema!
     */
    @Test
    public void testarPastConstraintViolationExistente() {
        UmObjetoPersistente umObj = daoUmObjetoPersistente.instanciarObjetoPersistivel();
        Calendar dataAtual = Calendar.getInstance();
        dataAtual.add(Calendar.MONTH, 5);
        umObj.setData(dataAtual);
        Set<ConstraintViolation<UmObjetoPersistente>> constraintViolations = validator.validate(umObj);
        Assert.assertEquals("Uma ConstraingViolation deveria existir!", 1, constraintViolations.size());
        ConstraintViolation<UmObjetoPersistente> constraintViolation = constraintViolations.iterator().next();
        Assert.assertEquals("Uma ConstraingViolation para Past deveria existir!", dataAtual,
                constraintViolation.getInvalidValue());
    }

    /**
     * Este teste visa apenas validar o uso do Hibernate Validator em rela��o a
     * anota��o @CPF.
     * 
     * ATEN��O: N�o � necess�rio produzir este tipo de teste para entidades
     * reais de um sistema!
     */
    @Test
    public void testarCPFConstraintViolationExistente() {
        UmObjetoPersistente umObj = daoUmObjetoPersistente.instanciarObjetoPersistivel();
        String numCpf = "123.456.789-00";
        umObj.setNumCpf(numCpf);
        Set<ConstraintViolation<UmObjetoPersistente>> constraintViolations = validator.validate(umObj);
        Assert.assertEquals("Uma ConstraingViolation deveria existir!", 1, constraintViolations.size());
        ConstraintViolation<UmObjetoPersistente> constraintViolation = constraintViolations.iterator().next();
        Assert.assertEquals("Uma ConstraingViolation para CPF deveria existir!", numCpf,
                constraintViolation.getInvalidValue());
    }

    /**
     * Este teste visa apenas validar o uso do Hibernate Validator pelo
     * frameworkarquitetura.
     * 
     * ATEN��O: N�o � necess�rio produzir este tipo de teste para entidades
     * reais de um sistema!
     */
    @Test
    public void testarLengthConstraintValidationExistente() {
        UmObjetoPersistente umObj = daoUmObjetoPersistente.instanciarObjetoPersistivel();
        String descricaoInvalida = "0";
        umObj.setDescricao(descricaoInvalida);
        Set<ConstraintViolation<UmObjetoPersistente>> constraintViolations = validator.validate(umObj);
        Assert.assertEquals("Uma ConstraingViolation deveria existir!", 1, constraintViolations.size());
        ConstraintViolation<UmObjetoPersistente> constraintViolation = constraintViolations.iterator().next();
        Assert.assertEquals("Uma ConstraingViolation para Length deveria existir!", descricaoInvalida,
                constraintViolation.getInvalidValue());
    }

}
