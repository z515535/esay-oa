package org.gzccc.oa.plugins;

import java.util.UUID;

/** 
* @author 作者 吴 耿锋: 
* @version 创建时间：2016年5月5日 下午10:27:16<p> 
* 类说明 ID生成器，使用jdk UUID 生成
*/
public class DefaultIdGenerator implements IdGenerator{
	public String generateId(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
 