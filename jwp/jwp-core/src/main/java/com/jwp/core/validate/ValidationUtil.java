/**
 * ValidationUtil.java.java
 * @author FengMy
 * @since 2015年12月25日
 */
package com.jwp.core.validate;

import java.util.LinkedList;
import java.util.List;

/**  
 * 功能描述：bean数据校验
 * 
 * @author FengMy
 * @since 2015年12月25日
 */
public class ValidationUtil {

	/**
	 * 只返回校验异常信息
	 * @param bean
	 * @param validations
	 * @return
	 */
	public static List<ValidationResult> validate(Object bean,Validation...validations){
		List<ValidationResult> results = new LinkedList<ValidationResult>();
		if(bean != null && validations != null && validations.length > 0){
			for(Validation validation : validations){
				results.addAll(validation.validate(bean));
			}
		}
		return results;
	}
}
