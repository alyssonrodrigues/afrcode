/**
 * 
 */
package cursojsf.modelo.entidade.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import curso.modelo.entidade.Entidade;
import curso.modelo.entidade.dao.Dao;
import curso.modelo.util.Contexto;

/**
 * @author alysson
 * 
 */
public abstract class DaoJpaAbstrato<C extends Entidade<Long>> extends
		JpaDaoSupport implements Dao<Long, C> {

	private static final Logger LOG = Logger.getLogger(DaoJpaAbstrato.class);

	private Class<C> classeEntidade;

	public DaoJpaAbstrato() {
		init();
	}

	private void init() {
		Class<?> clazz = this.getClass();
		Type superClazz = clazz.getGenericSuperclass();
		ParameterizedType tipoParametrizado = (ParameterizedType) superClazz;
		Type[] params = tipoParametrizado.getActualTypeArguments();
		classeEntidade = (Class<C>) params[0];
	}

	public Class<C> getClasseEntidade() {
		return classeEntidade;
	}
	
	public void excluir(C obj, Contexto contexto) {
		try {
		obj.validarExclusao(contexto);
		} catch (RuntimeException re) {
			// TODO: Tratar e preparar para envio ao usuario!
			throw re;
		}
		getJpaTemplate().remove(obj);
	}
	
	public C procurar(Long id) {
		registrarParametrosEmLog(Collections.singletonMap("id", (Object)id));
		return getJpaTemplate().find(getClasseEntidade(), id);
	}
	
	public C procurar(String chave, Object valor) {
		C obj = null;
		registrarParametrosEmLog(Collections.singletonMap(chave, valor));
		Collection<C> objs = procurar(Collections.singletonMap(chave, valor));
		Validate.isTrue(objs.size() <= 1, "Foi encontrado mais de um objeto " +
		"para a tupla {chave, valor} informada!");
    if (!objs.isEmpty()) {
	    obj = objs.iterator().next();
    }
		return obj;
	}
	
	private void registrarParametrosEmLog(Map<String, Object> parametros) {
		StringBuffer sb = new StringBuffer();
		sb.append("Pesquisando por ").append(getClasseEntidade().getName()).
			append(", parametros de pesquisa = {");
		boolean primeiro = true;
		for (Entry<String, Object> entry : parametros.entrySet()) {
			if (!primeiro) {
				sb.append(", ");
			}
			else {
				primeiro = false;
			}
			sb.append(entry.getKey()).append(" = ").append(entry.getValue());
		}
		sb.append("}.");
		LOG.info(sb.toString());
	}
	
	public List<C> procurar(Map<String, Object> parametros) {
		registrarParametrosEmLog(parametros);
		StringBuffer queryString = new StringBuffer();
		queryString.append("select obj from ").append(
				getClasseEntidade().getName()).append(" obj");
		boolean primeiro = true;
		for (String param : parametros.keySet()) {
			if (primeiro) {
				queryString.append(" where ");
				primeiro = false;
			} else {
				queryString.append(" and ");
			}
			queryString.append(param).append(" = :").
				append(param);
		}
		return getJpaTemplate().findByNamedParams(queryString.toString(),
				parametros);
	}
	
	public Collection<C> procurarPorExemplo(C obj) {
		Collection<C> objs = new ArrayList<C>();
		 try {
			 PropertyDescriptor props[] = PropertyUtils.getPropertyDescriptors(
				 obj);
			 Map<String, Object> parametros =
				 new HashMap<String, Object>();
			 for (PropertyDescriptor prop : props) {
				 String nome = prop.getName();
				 Object valor = prop.getReadMethod().invoke(
						 obj, new Object[0]);
				 if (isPropAConsiderar(prop) && 
						 StringUtils.isNotBlank(ObjectUtils.toString(valor))) {
					 parametros.put(nome, valor);
				 }
			 }
			 objs = procurar(parametros);
		} catch (Throwable e) {
			LOG.error("Erro ao pesquisar por exemplo!", e);
		}
		return objs;
	}
	
	private boolean isPropAConsiderar(PropertyDescriptor prop)
	{
		String nome = prop.getName();
		return !Collection.class.isAssignableFrom(prop.getPropertyType()) &&
		 !nome.equals("id") && !nome.equals("versao") && 
		 !nome.equals("class"); 
	}

	public Collection<C> recuperarTodos() {
		return procurar(new HashMap<String, Object>());
	}
	
	public void salvar(C obj, Contexto contexto) {
		try {
		obj.validarSalvamento(contexto);
		} catch (RuntimeException re) {
			// TODO: Tratar e preparar para envio ao usuario!
			throw re;
		}
		getJpaTemplate().persist(obj);
	}
	
	public void sincronizar() {
		getJpaTemplate().flush();
	}
	
	/**
	 * Deve ser sobrescrito para atribuicoes de atributos nao nulos do objeto
	 * instanciado.
	 * @return C
	 */
	public C instanciarObjetoPadrao() {
		C obj = null;
		try {
			obj = getClasseEntidade().newInstance();
			obj.preencherComValoresDefault();
		} catch (InstantiationException e) {
			LOG.error("InstantiationException", e);
		} catch (IllegalAccessException e) {
			LOG.error("IllegalAccessException", e);
		}
		return obj;
	}
}
