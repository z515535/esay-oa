package org.gzccc.oa.validator;

import java.util.List;

/** 
* @author 作者 吴 耿锋: 
* @version 创建时间：2016年5月14日 下午5:30:06<p> 
* 接口说明 自定义验证策略接口
*/
public interface Validate {
	boolean hasErrors(List<?> entitys);
}
 