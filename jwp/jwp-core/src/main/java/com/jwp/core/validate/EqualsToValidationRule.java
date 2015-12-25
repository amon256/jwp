/**
 * 
 */
package com.jwp.core.validate;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.jwp.core.utils.ELUtils;

/**
 * @author fengmengyue
 *
 */
public class EqualsToValidationRule implements ValidationRule {
	private String field;
	private String fieldName;
	
	public EqualsToValidationRule(String field,String fieldName){
		if(field == null || StringUtils.isEmpty(field.trim())){
			throw new IllegalArgumentException("field  is empty");
		}
		if(fieldName == null || StringUtils.isEmpty(fieldName.trim())){
			throw new IllegalArgumentException("fieldname  is empty");
		}
		this.field = field.trim();
		this.fieldName = fieldName.trim();
	}

	@Override
	public String type() {
		return "equalsTo";
	}

	@Override
	public boolean validate(Object bean, Object value) {
		Object otherValue = ELUtils.parseEL("${r."+field+"}", bean, "r", Object.class);
		return ObjectUtils.equals(value, otherValue);
	}

	@Override
	public String getErrorMessage() {
		return "值必须与"+fieldName+"一致";
	}

}
