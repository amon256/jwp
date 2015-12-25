/**
 * 
 */
package com.jwp.core.validate;

/**
 * @author fengmengyue
 *
 */
public class MinValidationRule implements ValidationRule {
	
	private int min;
	
	public MinValidationRule(int min){
		this.min = min;
	}

	@Override
	public String type() {
		return "min";
	}

	@Override
	public boolean validate(Object bean, Object value) {
		if(value != null && (value instanceof Number)){
			Number v = (Number)value;
			return v.intValue() >= min;
		}
		return true;
	}

	@Override
	public String getErrorMessage() {
		return "最小值限制"+min;
	}

}
