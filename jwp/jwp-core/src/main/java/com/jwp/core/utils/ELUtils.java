/**
 * ELUtils.java.java
 * @author FengMy
 * @since 2015年12月11日
 */
package com.jwp.core.utils;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import de.odysseus.el.util.SimpleContext;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年12月11日
 */
public class ELUtils {
	public static <T> Object parseEL(String el,Object param,String rootKey,Class<T> resultType){
		try{
			//ExpressionFactory类的实现是de.odysseus.el.ExpressionFactoryImpl
			ExpressionFactory factory = new de.odysseus.el.ExpressionFactoryImpl();
			//de.odysseus.el.util provides包提供即时可用的子类ELContext
			SimpleContext context = new de.odysseus.el.util.SimpleContext();
			//设置变量
			factory.createValueExpression(context, "${"+rootKey+"}", int.class).setValue(context, param);
			//解析表达式
			ValueExpression e = factory.createValueExpression(context, el, resultType);
			return e.getValue(context);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
