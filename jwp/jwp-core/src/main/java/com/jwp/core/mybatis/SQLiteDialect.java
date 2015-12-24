/**
 * SQLiteDialect.java.java
 * @author FengMy
 * @since 2015年11月24日
 */
package com.jwp.core.mybatis;

import java.util.List;

import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.TextSqlNode;

/**  
 * 功能描述：
 * 
 * @author FengMy
 * @since 2015年11月24日
 */
public class SQLiteDialect extends AbstractDialect {

	@Override
	protected void limitNodes(List<SqlNode> sqlNodes, String startIndexName, String sizeName) {
		sqlNodes.add(new TextSqlNode(" LIMIT #{"+startIndexName+",jdbcType=INTEGER} , #{"+sizeName+",jdbcType=INTEGER}"));
	}

}
