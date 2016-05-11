package org.gzccc.oa.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 吴耿锋	
 * @version 2016年5月5日21:57:28
 * 自动生成id注解 
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IdField {
	String strategy() default "";	//生成策略
}
