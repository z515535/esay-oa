package org.gzccc.oa.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.gzccc.oa.bean.User;
import org.gzccc.oa.dao.IUserDao;
import org.gzccc.oa.paging.Pages;
import org.gzccc.oa.service.IUserService;
import org.gzccc.oa.tools.SaltGenerator;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements IUserService{
	
	@Inject
	private IUserDao userDao;
	
	public List<User> findList(Pages<User> pages,Map<String,Object> param) {
		return userDao.findList(param);
	}

	public void addUser(User user) {
		user.setSalt(SaltGenerator.generator());
		//加密密码
		user.setPassword(new Md5Hash(user.getPassword(),user.getSalt()).toHex());
		userDao.addUser(user);
	}

	public User login(String username) {
		return userDao.login(username);
	}

}
