package org.gzccc.oa.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.gzccc.oa.annotation.Verify;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/** 
* @author 作者 吴 耿锋: 
* @version 创建时间：2016年5月14日 下午3:08:57<p> 
* 类说明 验证数据切面类 默认使用 JRS-303 仅对@Verify注解有效
*/

@Component
@Aspect
public class ValidatorAspect implements BeanFactoryAware{
	
	private BeanFactory beanFactory;
	
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	
	// 切入点 只切入有@Verify的方法
	@Pointcut("@annotation(org.gzccc.oa.annotation.Verify)")    
	private void verifyAspect(){}
	
	@Before("verifyAspect()")
	public void doBefore(JoinPoint joinPoint){
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature; 
		Method method = methodSignature.getMethod();
		Object[] params = joinPoint.getArgs();	//获取参数
		Verify verify = method.getAnnotation(Verify.class);
		String strategy = verify.strategy();
		//默认使用JRS303框架验证，支持热插拔
		if("JRS303".equals(strategy)){
			JRS303Validator(params);
		} else {
			customizeValidator(strategy, params, method);
		}
	}
	
	/**
	 * 使用JRS303进行验证
	 * @param params 参数
	 */
	private void JRS303Validator(Object[] params){
		BindingResult result = getBindingResult(params);
		if(null == result){
			throw new RuntimeException("Controller方法缺少 BindingResult 参数");
		}
		if(result.hasErrors()){
			throw new RuntimeException();
		}
	}
	
	/**
	 * 获取BindingResult对象，JRS303使用这个对象经行验证
	 * @return BindingResult
	 */
	private BindingResult getBindingResult(Object[] parmas){
		for(Object param : parmas){
			if(param instanceof BindingResult){
				return (BindingResult) param;
			}
		}
		return null;
	}
	
	private void customizeValidator(String strategy,Object[] params,Method method){
		
		Annotation[][] ans = method.getParameterAnnotations();	//获取参数的注解
		List<Object> entitys = getValidationData(ans,params);
		Validate validate = (Validate) beanFactory.getBean(strategy);
		boolean check;
		check = validate.hasErrors(entitys);
		if(!check){
			throw new RuntimeException("数据格式不正确，验证失败");
		}
	}
	
	/**
	 * 根据@Valid注解获取需要验证的数据
	 * @param ans 注解
	 * @param params 参数
	 * @return
	 */
	private List<Object> getValidationData(Annotation[][] ans,Object[] params){
		List<Object> entitys = new ArrayList<Object>();
		Annotation an;
		for(int i=0; i<ans.length; i++){
			if(ans[i].length > 0){
				an = ans[i][0];
				if(an instanceof Valid){
					entitys.add(params[i]);
				}
			}
		}
		return entitys;
	}
}
 