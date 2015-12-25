/**
 * 
 */
package com.jwp.core.validate;

import org.apache.commons.lang.StringUtils;

/**
 * @author fengmengyue
 *
 */
public class RequiredValidationRule implements ValidationRule {
	private static String MSG = "不能为空";
	@Override
	public boolean validate(Object bean,Object value) {
		if(value == null){
			return false;
		}
		if(value instanceof String){
			if(StringUtils.isEmpty(value.toString().trim())){
				return false;
			}
		}
		return true;
	}

	@Override
	public String getErrorMessage() {
		return MSG;
	}

	@Override
	public String type() {
		return "required";
	}
}
