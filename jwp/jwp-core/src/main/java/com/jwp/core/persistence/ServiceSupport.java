/**
 * 
 */
package com.jwp.core.persistence;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.jwp.core.entitys.CoreEntity;
import com.jwp.core.utils.Pagination;

/**
 * service基础实现
 * @author fengmengyue
 *
 */
public abstract class ServiceSupport<T extends CoreEntity> implements IService<T> {

	/**
	 * JPA实体管理接口
	 */
	@PersistenceContext
	protected EntityManager entityManager;
	
	protected Class<T> getCurrentClassType(){
		Type[] types = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments();
		if(types!=null && types.length > 0){
			@SuppressWarnings("unchecked")
			Class<T> c = (Class<T>)(types[0]);
			return c;
		}
		return null;
	}
	
	@Override
	public T insert(T entity) {
		if(StringUtils.isEmpty(entity.getId())){
			entity.setId(UUID.randomUUID().toString().toLowerCase().replaceAll("-", ""));
		}
		entity.beforePersistence();
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		entity.beforeUpdate();
		return entityManager.merge(entity);
	}

	@Override
	public T update(T entity, Collection<String> updateFields) {
		if(entity == null || StringUtils.isEmpty(entity.getId()) || updateFields==null || updateFields.isEmpty()){
			return null;
		}
		T old = findEntity(entity.getId());
		if(old != null){
			PropertyDescriptor[] propertys = BeanUtils.getPropertyDescriptors(old.getClass());
			List<String> ignoreProperties = new LinkedList<String>();
			for(PropertyDescriptor pd : propertys){
				if(!updateFields.contains(pd.getName())){
					ignoreProperties.add(pd.getName());
				}
			}
			String[] attr = new String[ignoreProperties.size()];
			BeanUtils.copyProperties(entity, old, ignoreProperties.toArray(attr));
			update(old);
		}
		return old;
	}

	@Override
	public int delete(String id) {
		T entity = null;
		try {
			entity = getCurrentClassType().newInstance();
		}catch (Exception e) {
		}
		if(entity != null){
			entity.setId(id);
			entityManager.remove(entity);
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(T entity) {
		entityManager.remove(entity);
		return 1;
	}

	@Override
	public T findEntity(String id) {
		return entityManager.find(getCurrentClassType(), id);
	}
	
	private <K> CriteriaQuery<K> getCriteriaQuery(PrepareQueryHandler<K> handler){
		CriteriaQuery<K> query = null;
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		if(handler != null){
			query = handler.create(cb);
		}
		if(query == null){
			throw new RuntimeException("PrepareQueryHandler return query is null");
		}
		if(query.getRoots() == null){
			throw new RuntimeException("PrepareQueryHandler return query's root is null");
		}
		return query;
	}

	@Override
	public List<T> find(PrepareQueryHandler<T> handler) {
		CriteriaQuery<T> query = getCriteriaQuery(handler);
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public long count(PrepareQueryHandler<Long> handler) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = getCriteriaQuery(handler);
		Root<?> root = query.getRoots().iterator().next();
		query.select(cb.count(root));
		return entityManager.createQuery(query).getSingleResult();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void findPagination(Pagination<T> pagination,PrepareQueryHandler<T> handler) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = getCriteriaQuery(handler);
		Selection<T> selection = query.getSelection();
		CriteriaQuery tmpQuery = query;
		CriteriaQuery<Long> countQuery = tmpQuery;
		countQuery.select(cb.count(query.getRoots().iterator().next()));
		Long recordCount = entityManager.createQuery(countQuery).getSingleResult();
		pagination.setRecordCount(recordCount);
		if(recordCount > 0){
			query.select(selection);
			List<T> dataList = entityManager.createQuery(query).setFirstResult((int) pagination.getStartIndex()).setMaxResults((int) pagination.getPageSize()).getResultList();
			pagination.setDatas(dataList);
		}
	}

	
}
