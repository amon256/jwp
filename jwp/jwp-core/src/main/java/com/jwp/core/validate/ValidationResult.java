/**
 * 
 */
package com.jwp.core.validate;

/**
 * @author fengmengyue
 *
 */
public class ValidationResult {
	
	/**
	 * 校验类型
	 */
	private String type;
	
	/**
	 * 字段名
	 */
	private String fieldName;

	/**
	 * 是否校验错误
	 */
	private boolean error = false;
	
	/**
	 * 错误信息
	 */
	private String message;
	
	public ValidationResult(){}
	
	public ValidationResult(boolean error){
		this.error = error;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
