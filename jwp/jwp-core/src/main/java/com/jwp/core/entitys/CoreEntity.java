/**
 * CoreEntity.java.java
 * @author FengMy
 * @since 2015年7月1日
 */
package com.jwp.core.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;


/**  
 * 功能描述：实体基类
 * 
 * @author FengMy
 * @since 2015年7月1日
 */
@MappedSuperclass
public abstract class CoreEntity implements Serializable{
	private static final long serialVersionUID = 925904316481738175L;
	
	@Id
	@Column(length=40)
	private String id;
	
	/**
	 * 分页使用，不持久化
	 */
	@Transient
	private int start_;
	
	/**
	 * 分页使用，不持久化
	 */
	@Transient
	private int size_;
	
	/**
	 * 排序使用(字段)，不持久化
	 */
	@Transient
	private String sort_;
	/**
	 * 排序使用(正序倒序)，不持久化
	 */
	@Transient
	private String order_;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getStart_() {
		return start_;
	}

	public void setStart_(int start_) {
		this.start_ = start_;
	}

	public int getSize_() {
		return size_;
	}

	public void setSize_(int size_) {
		this.size_ = size_;
	}

	public String getSort_() {
		return sort_;
	}

	public void setSort_(String sort_) {
		this.sort_ = sort_;
	}

	public String getOrder_() {
		return order_;
	}

	public void setOrder_(String order_) {
		this.order_ = order_;
	}
	
	/**
	 * 持久之前
	 */
	public void beforePersistence(){
	}
	
	/**
	 * 更新之前
	 */
	public void beforeUpdate(){
	}
}
