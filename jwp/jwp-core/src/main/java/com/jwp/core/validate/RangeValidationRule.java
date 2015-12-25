/**
 * 
 */
package com.jwp.core.validate;

/**
 * @author fengmengyue
 *
 */
public class RangeValidationRule implements ValidationRule {
	
	private int min;
	private int max;
	
	public RangeValidationRule(int min,int max){
		if(min >= max){
			throw new IllegalArgumentException("min is eq or gt than max");
		}
		this.min = min;
		this.max = max;
	}

	@Override
	public String type() {
		return "range";
	}

	@Override
	public boolean validate(Object bean, Object value) {
		if(value != null && (value instanceof Number)){
			Number v = (Number)value;
			return v.intValue() >= min && v.intValue() <= max;
		}
		return true;
	}

	@Override
	public String getErrorMessage() {
		return "大小必须在"+min+"至"+max+"之间";
	}

}
