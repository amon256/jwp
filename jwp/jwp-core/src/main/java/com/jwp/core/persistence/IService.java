/**
 * 
 */
package com.jwp.core.persistence;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import com.jwp.core.entitys.CoreEntity;
import com.jwp.core.utils.Pagination;

/**
 * @author fengmengyue
 *
 */
@Transactional
public interface IService<T extends CoreEntity> {

	/**
	 * 新增实体
	 * @param entity
	 * @return
	 */
	public T insert(T entity);
	
	/**
	 * 更新实体
	 * @param entity
	 * @return
	 */
	public T update(T entity);
	
	/**
	 * 更新实体指定字段
	 * @param entity
	 * @param updateFields
	 * @return
	 */
	public T update(T entity,Collection<String> updateFields);
	
	/**
	 * 按ID删除
	 * @param id
	 * @return
	 */
	public int delete(String id);
	
	/**
	 * 删除实体
	 * @param entity
	 * @return
	 */
	public int delete(T entity);
	
	/**
	 * 根据主键查询实体
	 * @param id
	 * @return
	 */
	public T findEntity(String id);
	
	/**
	 * 列表查询
	 * @param pagination
	 * @param param
	 */
	public List<T> find(PrepareQueryHandler<T> handler);
	
	/**
	 * 查询总数
	 * @param handler
	 */
	public long count(PrepareQueryHandler<Long> handler);
	
	/**
	 * 分页查询
	 * @param pagination
	 * @param param
	 */
	public void findPagination(Pagination<T> pagination,PrepareQueryHandler<T> handler);
	
}
