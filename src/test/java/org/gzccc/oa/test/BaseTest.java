package org.gzccc.oa.test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.gzccc.oa.bean.User;
import org.gzccc.oa.paging.Pages;
import org.gzccc.oa.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class BaseTest {
	@Inject
	private IUserService userService;

	
	  @Test 
	  public void tran(){
		  User user = new User();
		  user.setUsername("a515535"); 
		  user.setPassword("qq3410650");
		 // int result = userService.addUser(user);
		 // System.out.println(result);
	  }
	 

	@Test
	public void page() {
		//PageHelper.startPage(1, 2);
		Pages<User> pages = new Pages<User>();
		pages.setPageNum(1);
		pages.setPageSize(5);
		
		Map<String, Object> param = new HashMap<String, Object>(1);
		param.put("id", 1);
		userService.findList(pages,param);
		System.out.println(pages.getTotal());
		for (User u : pages.getList()) {
			System.out.println(u);
		}
	}
	
	@Test
	public void login(){
		User user = new User("test","test");
		System.out.println(userService.login(""));
	}
	
	public void foo (String text,String aa){
		System.out.println(text);
		System.out.println(aa);
	}
	
	@Test
	public void invokes() throws Exception{
		Method[] method = BaseTest.class.getMethods();
		Map<String,Method> map = new HashMap<String,Method>();
		for(Method m : method){
			map.put(m.getName(), m);
		}
		Method me = map.get("foo");
		me.invoke(this, "aaa","bbb");
	}
	

}
