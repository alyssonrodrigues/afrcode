package afrcode.fwarquitetura.modelo.entidade.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import afrcode.fwarquitetura.modelo.entidade.IEntidade;

/**
 * Implementação padrão da interface {@link IDao}.
 * 
 * @author alyssonfr
 * 
 * @param <TIPOID> Tipo do ID (Long, Integer, String, etc.)
 * @param <TIPOENTIDADE> Subtipo de {@link IEntidade}
 */
public abstract class DaoJpaAbstrato<TIPOID extends Comparable<TIPOID>, TIPOENTIDADE extends IEntidade<TIPOID>> implements IDao<TIPOID, TIPOENTIDADE> {

    protected static final Logger LOG = Logger.getLogger(DaoJpaAbstrato.class);

    @PersistenceContext
    private EntityManager entityManager;

    private Class<TIPOENTIDADE> classeEntidade;

    private Class<TIPOID> classeId;

    public DaoJpaAbstrato() {
        iniciar();
    }

    /**
     * Método de obtenção do tipo associado ao DAO via generic parameterers.
     */
    private void iniciar() {
        Class<?> clazz = this.getClass();
        Type superClazz = clazz.getGenericSuperclass();
        // Em geral um DAO contém apenas um supertipo genérico, porém mais de um supertipo genérico pode surgir na presença de
        // aspectos associados ao DAO.
        while (!ParameterizedType.class.isAssignableFrom(superClazz.getClass())) {
            clazz = clazz.getSuperclass();
            superClazz = clazz.getGenericSuperclass();
        }
        ParameterizedType tipoParametrizado = (ParameterizedType) superClazz;
        Type[] params = tipoParametrizado.getActualTypeArguments();
        classeId = (Class<TIPOID>) params[0];
        classeEntidade = (Class<TIPOENTIDADE>) params[1];
    }

    @Override
    public TIPOENTIDADE procurarPorId(TIPOID id) {
        TIPOENTIDADE obj = entityManager.find(getClasseEntidade(), id);
        return obj;
    }

    @Override
    public Collection<TIPOENTIDADE> recuperarTodos() {
        String qlString = "from " + getClasseEntidade().getName();
        TypedQuery<TIPOENTIDADE> query = entityManager.createQuery(qlString, 
        		getClasseEntidade());
        Collection<TIPOENTIDADE> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public void salvar(TIPOENTIDADE obj) {
        obj.validarSalvamento();
        entityManager.persist(obj);
    }

    @Override
    public void excluir(TIPOENTIDADE obj) {
        obj.validarExclusao();
        entityManager.remove(obj);
    }

    @Override
    public void sincronizar() {
        entityManager.flush();
    }

    @Override
    public void limparCache() {
        entityManager.clear();
    }

    /**
     * Deve ser sobrescrito para atribuição de atributos não nulos do objeto
     * instanciado.
     * 
     * @return TIPOENTIDADE
     */
    @Override
    public TIPOENTIDADE instaciarObjetoPersistivelParaTestes() {
        TIPOENTIDADE obj = null;
        try {
            obj = getClasseEntidade().newInstance();
            obj.preencherComValoresPersistiveis();
        } catch (InstantiationException e) {
            LOG.error("InstantiationException", e);
        } catch (IllegalAccessException e) {
            LOG.error("IllegalAccessException", e);
        }
        return obj;
    }

    /**
     * @return the classeEntidade
     */
    public Class<TIPOENTIDADE> getClasseEntidade() {
        return classeEntidade;
    }

    /**
     * @return the classeId
     */
    public Class<TIPOID> getClasseId() {
        return classeId;
    }

    /**
     * Método de acesso ao EntityManager associado ao DAO via injeção de dependência.
     * 
     * @return
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
