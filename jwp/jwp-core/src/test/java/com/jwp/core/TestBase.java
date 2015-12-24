/**
 * 
 */
package com.jwp.core;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author fengmengyue
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations="classpath:applicationContext.xml") // 加载配置
public abstract class TestBase {
	
}
