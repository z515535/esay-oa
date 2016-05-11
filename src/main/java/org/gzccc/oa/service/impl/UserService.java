package org.gzccc.oa.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.gzccc.oa.bean.User;
import org.gzccc.oa.dao.IUserDao;
import org.gzccc.oa.paging.Pages;
import org.gzccc.oa.service.IUserService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;

@Service("userService")
public class UserService implements IUserService{
	
	@Inject
	private IUserDao userDao;
	
	public List<User> findList(Pages<User> pages,Map<String,Object> param) {
		return userDao.findList(param);
	}

	public void addUser(User user) {
		userDao.addUser(user);
	}

}
