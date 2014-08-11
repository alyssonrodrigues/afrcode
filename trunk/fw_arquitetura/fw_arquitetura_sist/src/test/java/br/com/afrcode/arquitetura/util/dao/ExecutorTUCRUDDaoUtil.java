package br.com.afrcode.arquitetura.util.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.junit.Assert;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import br.com.afrcode.arquitetura.modelo.entidade.IEntidade;
import br.com.afrcode.arquitetura.modelo.entidade.dao.DaoJpaAbstrato;
import br.com.afrcode.arquitetura.spring.config.util.Profiles;

@Component
@Profile(Profiles.PROFILE_TU)
public class ExecutorTUCRUDDaoUtil {

    private boolean isComIdEGeneratedValue(Class<?> clazz) {
        boolean comIdEGeneratedValue = false;
        Id annotationId = AnnotationUtils.findAnnotation(clazz, Id.class);
        GeneratedValue annotationGeneratedValue = AnnotationUtils.findAnnotation(clazz, GeneratedValue.class);
        comIdEGeneratedValue = annotationId != null && annotationGeneratedValue != null;
        return comIdEGeneratedValue;
    }

    public <TIPOID extends Comparable<TIPOID>, TIPOENTIDADE extends IEntidade<TIPOID>> void testarExcluir(
            DaoJpaAbstrato<TIPOID, TIPOENTIDADE> dao) {
        TIPOENTIDADE stub = dao.instanciarObjetoPersistivel();
        dao.salvar(stub);
        TIPOID id = stub.getId();
        dao.excluir(stub);
        TIPOENTIDADE r = dao.procurarPorId(id);
        Assert.assertNull("O objeto não foi excluído!", r);
    }

    public <TIPOID extends Comparable<TIPOID>, TIPOENTIDADE extends IEntidade<TIPOID>> void testarProcurarPorId(
            DaoJpaAbstrato<TIPOID, TIPOENTIDADE> dao) {
        TIPOENTIDADE stub = dao.instanciarObjetoPersistivel();
        dao.salvar(stub);
        TIPOID id = stub.getId();
        TIPOENTIDADE r = dao.procurarPorId(id);
        Assert.assertEquals("O objeto recuperado não foi o esperado!", id, r.getId());
    }

    public <TIPOID extends Comparable<TIPOID>, TIPOENTIDADE extends IEntidade<TIPOID>> void
            testarRecuperarObjetosComPaginacao(DaoJpaAbstrato<TIPOID, TIPOENTIDADE> dao) {
        TIPOENTIDADE stub = dao.instanciarObjetoPersistivel();
        dao.salvar(stub);
        TIPOID idA = stub.getId();

        Collection<TIPOENTIDADE> objs = new ArrayList<TIPOENTIDADE>();
        objs.add(stub);

        String qlString = "from " + stub.getClass().getName() + " as obj where id = :param_idA";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("param_idA", idA);

        Collection<TIPOENTIDADE> r = dao.recuperarObjetos(qlString, params, 0, 1);
        Assert.assertTrue("Os objetos recuperados não são os esperados", r.containsAll(objs));
    }

    public <TIPOID extends Comparable<TIPOID>, TIPOENTIDADE extends IEntidade<TIPOID>> void
            testarRecuperarTodosJaCadastradosComPaginacao(DaoJpaAbstrato<TIPOID, TIPOENTIDADE> dao) {
        int pagina = 0;
        int quantidadeDeItens = 1;
        Collection<TIPOENTIDADE> r = dao.recuperarTodos(pagina, quantidadeDeItens);
        Assert.assertEquals("Deveria ter sido recuperado um único objeto!", 1, r.size());
    }

    public <TIPOID extends Comparable<TIPOID>, TIPOENTIDADE extends IEntidade<TIPOID>> void testarSalvarEProcurar(
            DaoJpaAbstrato<TIPOID, TIPOENTIDADE> dao) {
        TIPOENTIDADE stub = dao.instanciarObjetoPersistivel();
        validarId(stub);
        dao.salvar(stub);
        Assert.assertNotNull("O id do objeto persistível NÃO deveria ser nulo depois de salvar!", stub.getId());
        TIPOENTIDADE r = dao.procurarPorId(stub.getId());
        Assert.assertEquals("O objeto não foi persistido e/ou recuperado!", stub, r);
    }

    private <TIPOID extends Comparable<TIPOID>, TIPOENTIDADE extends IEntidade<TIPOID>> void
            validarId(TIPOENTIDADE stub) {
        if (isComIdEGeneratedValue(stub.getClass())) {
            Assert.assertNull("O id do objeto persistível deveria ser nulo antes de salvar!");
        } else {
            Assert.assertNotNull("O id do objeto persistível não deveria ser nulo antes de salvar!");
        }
    }

}
