package org.gzccc.oa.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @author 作者 吴 耿锋: 
* @version 创建时间：2016年5月14日 下午9:09:14<p> 
* 类说明 
*/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mobile {
}
 