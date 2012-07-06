/**
 * 
 */
package curso.modelo.entidade.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

import curso.modelo.entidade.Entidade;
import curso.modelo.util.Contexto;

/**
 * @author alysson
 * 
 */
public abstract class DaoAbstrato<C extends Entidade<Long>> implements
		Dao<Long, C> {

	private static final Logger LOG = Logger.getLogger(Dao.class);

	private static Map<Class<?>, Map<Long, ? extends Entidade<Long>>> cache = 
		new HashMap<Class<?>, Map<Long, ? extends Entidade<Long>>>();

	private Class<C> classeEntidade;
	
	private static long ultimoId = 1;

	public DaoAbstrato() {
		init();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(C obj, Contexto contexto) {
		getCachePorTipo(obj.getClass()).remove(obj.getId());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public C procurar(Long id) {
		Class<?> clazz = getClasseEntidade();
		return procurar(clazz, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<C> procurar(Map<String, Object> parametros) {
		List<C> list = new ArrayList<C>();
		Class<?> clazz = getClasseEntidade();
		Map<Long, C> cachePorTipo = getCachePorTipo(clazz);
		for (C ent : cachePorTipo.values()) {
			list.add(ent);
			for (Map.Entry<String, Object> entry :
				parametros.entrySet()) {
				Object valor = getValorPropriedade(ent, entry.getKey());
				if (!entry.getValue().equals(valor)) {
					list.remove(ent);
					break;
				}
			}
		}
		return list;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public C procurar(String chave, Object valor) {
		Collection<C> objs = new ArrayList<C>();
		Class<?> clazz = getClasseEntidade();
		C obj = null;
		Map<Long, C> cachePorTipo = getCachePorTipo(clazz);
		for (C ent : cachePorTipo.values()) {
			Object value = getValorPropriedade(ent, chave);
			if (value != null && value.equals(valor)) {
				objs.add(ent);
			}
		}
		Validate.isTrue(objs.size() <= 1, "Foi encontrado mais de um objeto " +
				"para a tupla {chave, valor} informada!");
		if (!objs.isEmpty()) {
			obj = objs.iterator().next();
		}
		return obj;
	}
	
	@Override
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<C> recuperarTodos() {
		return getCachePorTipo(getClasseEntidade()).values();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void salvar(C obj, Contexto contexto) {
		if (obj.getId() == null)
		{
			gerarId(obj);
		}
		getCachePorTipo(obj.getClass()).put(obj.getId(), obj);
	}
	
	private void gerarId(C obj)
	{
		LOG.info("gerarId:" + ultimoId);
		obj.setId(ultimoId++);
	}

	private Map<Long, C> getCachePorTipo(Class<?> clazz) {
		Map<Long, C> cachePorTipo = (Map<Long, C>) cache.get(clazz);
		if (cachePorTipo == null) {
			cachePorTipo = new HashMap<Long, C>();
		}
		cache.put(clazz, cachePorTipo);
		return cachePorTipo;
	}

	private Object getValorPropriedade(C ent, String propriedade) {
		Object value = null;
		try {
			value = PropertyUtils.getProperty(ent, propriedade);
		} catch (IllegalAccessException e) {
			LOG.error(e);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			LOG.error(e);
		} catch (NoSuchMethodException e) {
			LOG.error(e);
		}
		return value;
	}

	private void init() {
		Class<?> clazz = this.getClass();
		Type superClazz = clazz.getGenericSuperclass();
		ParameterizedType tipoParametrizado = (ParameterizedType) superClazz;
		Type[] params = tipoParametrizado.getActualTypeArguments();
		classeEntidade = (Class<C>) params[0];
	}

	private C procurar(Class<?> clazz, Long id) {
		return getCachePorTipo(clazz).get(id);
	}

	protected Class<C> getClasseEntidade() {
		return classeEntidade;
	}
}
