/**
 * Pagination.java.java
 * @author FengMy
 * @since 2015年7月15日
 */
package com.jwp.core.utils;

import java.io.Serializable;
import java.util.Collection;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年7月15日
 */
public class Pagination<T> implements Serializable {
	private static final long serialVersionUID = 1178299039073862046L;
	private static final long DEFAULT_PAGE = 1;
	public static int DEFAULT_PAGE_SIZE = 10;
	private long recordCount;
	private Collection<T> datas;
	private long pageSize = DEFAULT_PAGE_SIZE;
	private long currentPage = DEFAULT_PAGE;
	public Pagination(){
	}
	public Pagination(long pageSize, long page) {
		if (pageSize < 1) {
			throw new IllegalArgumentException("页码必须大于0!");
		} else if (currentPage < 1) {
			page = 1;
		} else {
			this.pageSize = pageSize;
			this.currentPage = page;
		}
	}
	
	public String getCount(){
		return this.getRecordCount()+"";
	}
	
	public void setPageSize(int countOnEachPage) {
		this.pageSize = countOnEachPage;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public long getPageSize() {
		return pageSize;
	}


	public void setRecordCount(long totalCount) {
		this.recordCount = totalCount;
	}
	

	public long getPageCount() {
		return (recordCount == 0) ? 1 : ((recordCount % pageSize == 0) ? (recordCount / pageSize)
				: (recordCount / pageSize) + 1);
	}

	public long getPreviousPage() {
		if(currentPage > 1) return currentPage - 1;
		else return DEFAULT_PAGE;
	}
	
	public long getNextPage() {
		if(currentPage < getPageCount()) return currentPage + 1;
		else return getPageCount();
	}
	
	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	
	public long getStartIndex(){
		return (getCurrentPage() - 1) * getPageSize();
	}
	public Collection<T> getDatas() {
		return datas;
	}
	public void setDatas(Collection<T> datas) {
		this.datas = datas;
	}
}
