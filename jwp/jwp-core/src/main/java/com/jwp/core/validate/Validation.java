/**
 * 
 */
package com.jwp.core.validate;

import java.util.LinkedList;
import java.util.List;

import com.jwp.core.utils.ELUtils;

/**
 * 校验类
 * @author fengmengyue
 *
 */
public class Validation {
	
	private String field;
	private String fieldName;
	private ValidationRule[] rules;
	
	public Validation(String field,String fieldName,ValidationRule...rules){
		this.field = field;
		this.fieldName = fieldName;
		this.rules = rules;
	}
	
	@SuppressWarnings("el-syntax")
	public List<ValidationResult> validate(Object bean){
		List<ValidationResult> results = new LinkedList<ValidationResult>();
		if(rules != null && rules.length > 0){
			for(ValidationRule rule : rules){
				Object value = ELUtils.parseEL("${r."+field+"}", bean, "r", Object.class);
				if(!rule.validate(bean, value)){
					//校验不通过
					ValidationResult result = new ValidationResult(false);
					result.setFieldName(fieldName);
					result.setType(rule.type());
					result.setMessage(rule.getErrorMessage());
					results.add(result);
					break;
				}
			}
		}
		return results;
	}
	
}
