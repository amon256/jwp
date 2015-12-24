/**
 * DataEntity.java.java
 * @author FengMy
 * @since 2015年7月1日
 */
package com.jwp.core.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月1日
 */
@MappedSuperclass
public class DataEntity extends CoreEntity {
	private static final long serialVersionUID = 3377057353374426809L;

	/**
	 * 创建日期
	 */
	@Column
	private Date createTime;
	
	/**
	 * 最后更新日期
	 */
	@Column
	private Date lastUpdateTime;
	
	/**
	 * 查询时使用一键搜索的关键字
	 */
	@Transient
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	@Override
	public void beforePersistence() {
		super.beforePersistence();
		if(this.createTime == null){
			this.createTime = new Date();
		}
		this.lastUpdateTime = new Date();
	}
	
	@Override
	public void beforeUpdate() {
		super.beforeUpdate();
		this.lastUpdateTime = new Date();
	}
}
