package org.gzccc.oa.paging;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/** 
* @author 作者 吴 耿锋: 
* @version 创建时间：2016年5月7日 下午10:08:13<p> 
* 类说明 AOP 分页切面<p>
*/
@Component
@Aspect
public class PagesAspect{
	
	/**
	 * 配置切入点 类级别
	 */
	@Pointcut("execution(* org.gzccc.oa.service.*.*(..))")
	private void classPointcut(){};
	
	/**
	 * 匹配以 Pages结尾的方法
	 */
	@Pointcut("execution(* *List(..))")
	private void methodPointcut(){}
	
	
	/**
	 * 前置通知，判断是否执行分页插件 
	 * @param pjp
	 */
	@Before("classPointcut() && methodPointcut()")
	public void pageInit(JoinPoint point){
		Object[] params = point.getArgs();	//获取参数
		Pages<?> pages = isPage(params);
		
		if(null != pages){
			PageHelper.startPage(pages.getPageNum(), pages.getPageSize());
		}
	}
	
	
	/**
	 * 返回通知<p>
	 * 如果是分页查询，把分页查询结果返回到Pages 参数中
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings({ "rawtypes",  "unchecked" })
	 @AfterReturning(value="classPointcut() && methodPointcut()",returning="result")
	public void packingPage(JoinPoint point,Page result) throws Throwable{
		boolean isPage = isPageReturn(result);
		if(isPage){
			Object[] params = point.getArgs();	//获取参数
			Pages<?> pages = isPage(params);
			Pages retVal = new Pages(result);
			pages.setTotal(result.getTotal()); //将返回的分页属性赋值给Pages参数对象
			pages.setList(retVal.getList());
			pages.setSize(retVal.getSize());
		}
		
	}
	
	
	/**
	 * 判断参数列表是否存在 Pages,是否分页
	 * @param args 拦截的方法参数
	 * @return 分页对象
	 */
	private Pages<?> isPage(Object[] params){
		for(Object param : params){
			if(param instanceof Pages){
				return (Pages<?>) param;
			}
		}
		return null;
	}
	
	
	/**
	 * 判断返回值是否为 Page 对象
	 * @param ret 返回值
	 * @return
	 */
	private boolean isPageReturn(Object ret){
		if(ret instanceof Page<?>){
			return true;
		}	
		return false;
	}
}
 