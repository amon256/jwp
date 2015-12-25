/**
 * 
 */
package com.jwp.core.validate;

/**
 * @author fengmengyue
 *
 */
public interface ValidationRule {
	
	/**
	 * 校验类型
	 * @return
	 */
	String type();

	/**
	 * 校验值
	 * @param value
	 * @return
	 */
	boolean validate(Object bean,Object value);
	
	/**
	 * 异常信息
	 * @param value
	 * @return
	 */
	String getErrorMessage();
}
