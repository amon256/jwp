/**
 * 
 */
package com.jwp.core.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import com.jwp.core.entitys.CoreEntity;

/**
 * 简单实体查询handler
 * @author fengmengyue
 *
 */
public class SimplePrepareQueryhandler<T extends CoreEntity> implements PrepareQueryHandler<T> {
	
	private T entity;
	
	public SimplePrepareQueryhandler(T entity){
		this.entity = entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CriteriaQuery<T> create(CriteriaBuilder cb) {
		CriteriaQuery<T> query = (CriteriaQuery<T>) cb.createQuery(entity.getClass());
		Root<T> root = (Root<T>) query.from(entity.getClass());
		if(StringUtils.isNotEmpty(entity.getSort_())){
			if("asc".equalsIgnoreCase(entity.getOrder_())){
				query.orderBy(cb.asc(root.get(entity.getSort_())));
			}else if("desc".equalsIgnoreCase(entity.getOrder_())){
				query.orderBy(cb.desc(root.get(entity.getSort_())));
			}
		}
		Predicate[] conditions = getWhereCondition(cb, entity,root);
		if(conditions != null && conditions.length > 0){
			query.where(conditions);
		}
		return query;
	}
	
	

	protected Predicate[] getWhereCondition(CriteriaBuilder cb,T entity,Root<T> root){
		return null;
	}
}
