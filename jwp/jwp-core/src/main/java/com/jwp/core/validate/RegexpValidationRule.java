/**
 * 
 */
package com.jwp.core.validate;

import org.apache.commons.lang.StringUtils;

/**
 * @author fengmengyue
 *
 */
public class RegexpValidationRule implements ValidationRule {
	
	private String regexp;
	
	public RegexpValidationRule(String regexp){
		this.regexp = regexp;
	}

	@Override
	public String type() {
		return "regexp";
	}

	@Override
	public boolean validate(Object bean, Object value) {
		if(value != null && (value instanceof String) && StringUtils.isNotEmpty(value.toString().trim())){
			return value.toString().matches(regexp);
		}
		return true;
	}

	@Override
	public String getErrorMessage() {
		return "格式不正确";
	}

}
