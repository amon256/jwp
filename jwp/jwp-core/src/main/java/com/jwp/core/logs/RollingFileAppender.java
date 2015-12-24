/**
 * MyRollingFileAppender.java
 * 2015年5月25日
 */
package com.jwp.core.logs;

import java.io.File;


/**  
 * <b>功能：</b>MyRollingFileAppender.java<br/>
 * <b>描述：</b> 自定义FileAppender<br/>
 * <b>@author： </b>fengmengyue<br/>
 */
public class RollingFileAppender extends org.apache.log4j.RollingFileAppender {

	public static String root = System.getProperty("user.dir");
	
	public static void setRoot(String root){
		RollingFileAppender.root = root;
	}
	
	@Override
	public void setFile(String file) {
		file = root + File.separator + file;
		super.setFile(file);
	}
}
