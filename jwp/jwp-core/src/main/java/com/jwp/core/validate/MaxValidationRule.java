/**
 * 
 */
package com.jwp.core.validate;

/**
 * @author fengmengyue
 *
 */
public class MaxValidationRule implements ValidationRule {
	
	private int max;
	
	public MaxValidationRule(int max){
		this.max = max;
	}

	@Override
	public String type() {
		return "max";
	}

	@Override
	public boolean validate(Object bean, Object value) {
		if(value != null && (value instanceof Number)){
			Number v = (Number)value;
			return v.intValue() <= max;
		}
		return true;
	}

	@Override
	public String getErrorMessage() {
		return "最大值限制"+max;
	}

}
