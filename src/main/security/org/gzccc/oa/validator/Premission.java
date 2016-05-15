package org.gzccc.oa.validator;

import java.lang.reflect.Method;
import java.util.Map;

/** 
* @author 作者 吴 耿锋: 
* @version 创建时间：2016年5月14日 下午9:55:46<p> 
* 类说明 数据验证策略接口
*/
public interface Premission {
	Map<String,Method> init();
	
	Map<String,Method> getStrategy();
}
 