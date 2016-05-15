package org.gzccc.oa.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.gzccc.oa.annotation.*;

/** 
* @author 作者 吴 耿锋: 
* @version 创建时间：2016年5月14日 下午9:11:31<p> 
* 类说明 验证数据策略
*/
@SuppressWarnings("unused")
public class ValidatorPremission implements Premission{
	private  Map<String,Method> strategy = new HashMap<String,Method>();
	
	public ValidatorPremission(){
		this.strategy = init();
	}
	
	public Map<String, Method> init() {
		Map<String,Method> strategy = new HashMap<String,Method>();
		Class<?> clazz = this.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method : methods){
			strategy.put(method.getName(), method);
		}
		return strategy;
	}
	
	public Map<String,Method> getStrategy(){
		return strategy;
	}
	
	private boolean Mobile(String attr,Mobile an){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(attr);
		return m.matches();
	}
}
 