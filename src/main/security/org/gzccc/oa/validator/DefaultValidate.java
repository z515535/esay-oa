package org.gzccc.oa.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

/** 
* @author 作者 吴 耿锋: 
* @version 创建时间：2016年5月14日 下午7:47:43<p> 
* 类说明 默认数据验证器
*/
public class DefaultValidate implements Validate{
	
	@Inject
	private Premission premission;
	
	public boolean hasErrors(List<?> entitys){
		boolean result = true;
		Map<String,Method> strategy = premission.getStrategy();
		try{
			for(Object entity : entitys){
				result = result && check(entity, strategy);
			}
		}catch(Exception e){
			System.out.println("验证出现异常："+this.getClass().getName());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 数据验证
	 * @param entity
	 * @param strategy
	 * @return
	 * @throws Exception
	 */
	private boolean check(Object entity,Map<String,Method> strategy) 
			throws Exception {
		
		if(null == entity)
			return false;
		Map<String,Annotation[]> attribute = getField(entity);
		List<Object> values = getValue(entity);
		boolean result = true;
		Annotation[] ans;
		int index = 0;
		for(Map.Entry<String,Annotation[]> entry : attribute.entrySet()){
			ans = entry.getValue();
			for(Annotation an : ans){
				result = result && verify(values.get(index), an, strategy);
			}
			index ++;
		}
		return result;
	}
	
	/**
	 * 根据实体类获取其成员变量名及其注解
	 * @param entity
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private Map<String,Annotation[]> getField(Object entity) throws
			IllegalArgumentException, IllegalAccessException{
		Map<String,Annotation[]> attribute = new HashMap<String,Annotation[]>();
		Class<?> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		for(Field field : fields){
			field.setAccessible(true);
			attribute.put(field.getName(), field.getAnnotations());
		}
		return attribute;
	}
	
	/**
	 * 验证成员变量
	 * @param attr 实体属性
	 * @param an  实体注解
	 * @param strategy 验证策略
	 * @return
	 * @throws Exception
	 */
	private boolean verify(Object value,Annotation an,Map<String,Method> strategy) 
			throws Exception{
		boolean result = true;
		Method method = strategy.get(an.annotationType().getSimpleName());
		if(null == method)		//没有其注解的认证策略
			return result;
		if(null == value)
			return false;
		method.setAccessible(true);
		
		///////////////////
		
		result = result && (Boolean) method.invoke(premission,value,an);
		return result;
	}
	
	/**
	 * 获取实体成员变量的值
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private List<Object> getValue(Object entity) throws Exception{
		Class<?> clazz = entity.getClass();
		Field fields[] = clazz.getDeclaredFields();
		List<Object> value = new ArrayList<Object>();
		for(Field field : fields){
			field.setAccessible(true);
			value.add(field.get(entity));
		}
		return value;
	}

}
 