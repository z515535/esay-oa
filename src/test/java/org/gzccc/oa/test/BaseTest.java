package org.gzccc.oa.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.gzccc.oa.bean.User;
import org.gzccc.oa.paging.Pages;
import org.gzccc.oa.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class BaseTest {
	@Inject
	private IUserService userService;

	/*
	 * @Test public void tran(){ User user = new User();
	 * user.setUsername("test"); user.setPassword("test");
	 * userService.addUser(user); }
	 */

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

}
