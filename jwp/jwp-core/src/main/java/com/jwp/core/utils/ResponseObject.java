/**
 * ResponseObject.java.java
 * @author FengMy
 * @since 2015年12月3日
 */
package com.jwp.core.utils;

import java.util.HashMap;

/**  
 * 功能描述：HTTP ajax请求返回封装对象
 * 
 * @author FengMy
 * @since 2015年12月3日
 */
public class ResponseObject extends HashMap<String, Object> {
	private static final long serialVersionUID = 3645189269131851273L;
	private static final String STATUS = "status";
	private static final String MSG = "msg";
	
	public static class Status{
		public static final String FAIL = "fail";
		public static final String SUCCESS = "success";
		public static final String EXCEPTION = "exception";
	}
	
	public static ResponseObject newInstance(){
		return new ResponseObject();
	}
	
	public ResponseObject fail(){
		setStatus(Status.FAIL);
		return this;
	}
	public ResponseObject success(){
		setStatus(Status.SUCCESS);
		return this;
	}
	public ResponseObject exception(){
		setStatus(Status.EXCEPTION);
		return this;
	}

	public void setStatus(String status){
		put(STATUS, status);
	}
	
	public String getStatus(){
		return (String) get(STATUS);
	}
	
	public void setMsg(String msg){
		put(MSG, msg);
	}
	
	public String getMsg() {
		return (String) get(MSG);
	}
}
