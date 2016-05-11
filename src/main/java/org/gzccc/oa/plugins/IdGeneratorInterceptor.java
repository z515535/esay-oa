package org.gzccc.oa.plugins;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.gzccc.oa.annotation.IdField;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/** 
* @author 作者 吴 耿锋: 
* @version 创建时间：2016年5月5日 下午10:03:40 <p>
* 类说明 自定义mybatis插件<p>
* id自动生成拦截器  
*/

@Intercepts({ @Signature(type = Executor.class, method = "update", args = {
		MappedStatement.class, Object.class }) })
public class IdGeneratorInterceptor implements Interceptor,BeanFactoryAware{
	
	/**默认的id生成策略*/
	private static final IdGenerator DEFALUT_ID_GENERATOR = new DefaultIdGenerator();
	
	/**spring容器的bean工厂*/
	private BeanFactory beanFactory;
	
	/**id生成策略，需要在spring配置文件中配置bean*/
	private IdGenerator idGenerator;
	
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement) args[0];	//获取 mapper信息
		if(ms.getSqlCommandType() == SqlCommandType.INSERT && args.length > 1){		//当且仅当为插入语句才自动生成ID
			Object entity = args[1];
			if(entity != null){
				injectId(entity);
			}
		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		//用当前拦截器生成对目标targer的代理
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {}
	
	/**
	 * 检索具有IdField注解的成员变量，如果该变量为空就用ID生成策略注入一个ID
	 * @param entity 拦截到的实体对象
	 */
	public void injectId(Object entity){
		Field field = findField(entity.getClass(), IdField.class);
		if(field != null){
			if(!field.isAccessible()){
				field.setAccessible(true);	//反射中 私有变量需要设置为可访问
			}
			try {
				Object id = field.get(entity);
				if(id == null || StringUtils.isBlank(id.toString())){
					IdField idField = field.getAnnotation(IdField.class);	//获取自定义 IdField 注解
					String strategy = idField.strategy();
					//判断是否指定了ID生成策略，若指定使用指定策略,否则使用默认策略
					if(StringUtils.isNotBlank(strategy)){
						//若使用其他生成策略，spring bean id 需要和注解配置的一样
						idGenerator = beanFactory.getBean(strategy, IdGenerator.class);
						id = idGenerator.generateId();
					} else {
						id = DEFALUT_ID_GENERATOR.generateId();
					}
					field.set(entity, id);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param clazz 拦截的实体对象
	 * @param annotationType 注解类型
	 * @return 带有指定注解的成员变量
	 */
	private Field findField(Class<?> clazz,Class<? extends Annotation> annotationType){
		while(!Object.class.equals(clazz) && clazz != null){	//Clazz不为Object 且不为空
			Field[] fields = clazz.getDeclaredFields();
			for(Field f : fields){
				if(f.getAnnotation(annotationType) != null){
					return f;
				}
			}
		}
		return null;
	}

	/**
	 * 设置id生成策略,提供在spring xml中 rel使用
	 * @param idGenerator
	 */
	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

}
 