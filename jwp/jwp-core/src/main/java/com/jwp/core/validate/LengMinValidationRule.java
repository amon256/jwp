/**
 * 
 */
package com.jwp.core.validate;

/**
 * @author fengmengyue
 *
 */
public class LengMinValidationRule implements ValidationRule {
	
	private int minLength;
	
	public LengMinValidationRule(int minLength){
		this.minLength = minLength;
	}

	@Override
	public String type() {
		return "lengMin";
	}

	@Override
	public boolean validate(Object bean, Object value) {
		if(value != null && (value instanceof String)){
			int len = value.toString().trim().length();
			return len >= minLength;
		}
		return true;
	}

	@Override
	public String getErrorMessage() {
		return "最小长度"+minLength;
	}

}
