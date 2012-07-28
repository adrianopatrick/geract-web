package com.implementsystem.geract.services.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import com.implementsystem.geract.services.IServiceRemote;

@Stateless
public class GenericServiceImpl<T> implements IServiceRemote<T>{

	@PersistenceContext
	protected EntityManager em;

	@Override
	public T inserir(T entity) {
		
		//TODO parei aqui
		/*Class<T> clazz = (Class<T>)entity.getClass();
		Method methods[] = clazz.getDeclaredMethods();
		
		for (Method method : methods) {
			
			if(method.getName().contains("setDataCriacao")){
				try {					
					method.invoke(new Date());
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}*/
		
		em.persist(entity);
		return entity;
	}

	@Override
	public T atualizar(T entity) {		
		return em.merge(entity);
	}

	@Override
	public void excluir(T entity) {
		em.remove(em.merge(entity));
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> modelClass, Boolean initialize) {
		
		Boolean initializeDependecies = true;
		if (modelClass == null) {
			throw new IllegalArgumentException("class n‹o pode ser null!");
		}

		Criteria criteria = montarCriteria(modelClass);
		
		List<T> lista = criteria.list();
		
		if(initialize){
			if(initializeDependecies){
				for(T t: lista){
					initializeDependecies(t);
				}
			}
			
		}
		
		return lista;
	}
	
	@Override
	public T buscarPorId(Class<T> modelClass, Integer id) {		
		return em.find(modelClass, id);
	}	
	
	@SuppressWarnings("unchecked")
	public List<T> example(T bean, Boolean initialize) {
		Criteria criteria = getCriteria(bean);
		
		Example example = Example.create(bean);
		example.excludeZeroes();
		example.enableLike(MatchMode.ANYWHERE).ignoreCase();
        criteria.add(example);
        
        List<T> list = criteria.list();
        if(initialize){
	        for(T t: list) {
	        	initializeDependecies(t);
	        }
        }
        
		return list;
	}
	
	public Object initializeDependecies(Object t) {
		
		Method[] methods = t.getClass().getMethods();
		for(Method method: methods){
			Class<?> returnTypeClass = method.getReturnType();
			if(returnTypeClass.isAnnotationPresent(Entity.class) ||
					Collection.class.isAssignableFrom(returnTypeClass)) {
				try {
					Object object = method.invoke(t);
					if(object != null){
						object.toString();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return t;
	}
	
	@SuppressWarnings("unchecked")
	protected Criteria getCriteria(T bean) {
		return montarCriteria((Class<T>) bean.getClass());
	}
	
	private Criteria montarCriteria(Class<T> modelClass) {
		Session session = em.unwrap(Session.class);

		String aliasEntityRoot = modelClass.getClass().getSimpleName()
				.substring(0, 1).toLowerCase()
				+ modelClass.getSimpleName().substring(1,
						modelClass.getSimpleName().length());

		Criteria criteria = session.createCriteria(modelClass.getName(),
				aliasEntityRoot);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	
}
