package org.gzccc.oa.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @author 作者 吴 耿锋: 
* @version 创建时间：2016年5月14日 下午3:01:37<p> 
* 类说明 自定义注解，验证后台数据 默认使用 JRS 303
*/
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Verify {
	String strategy() default "JRS303";
}
 