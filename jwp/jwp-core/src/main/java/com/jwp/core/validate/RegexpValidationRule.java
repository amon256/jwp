/**
 * 
 */
package com.jwp.core.validate;

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
		if(value != null && (value instanceof String)){
			return value.toString().matches(regexp);
		}
		return false;
	}

	@Override
	public String getErrorMessage() {
		return "格式不正确";
	}

}
