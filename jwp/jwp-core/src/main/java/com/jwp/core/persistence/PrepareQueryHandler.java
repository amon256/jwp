/**
 * 
 */
package com.jwp.core.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * @author fengmengyue
 *
 */
public interface PrepareQueryHandler<T> {

	/**
	 * 创建查询
	 * @param cb
	 * @return
	 */
	public CriteriaQuery<T> create(CriteriaBuilder cb);
	
}
