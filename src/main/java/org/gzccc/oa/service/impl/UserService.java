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
	@Inject
	private MailService mailService;
	public List<User> findList(Pages<User> pages,Map<String,Object> param) {
		return userDao.findList(param);
	}

	public void addUser(User user) {
		user.setSalt(SaltGenerator.generator());
		//加密密码
		user.setPassword(new Md5Hash(user.getPassword(),user.getSalt()).toHex());
		user.setRegisterDate(String.valueOf(System.currentTimeMillis()));
		user.setActivationCode(new Md5Hash(user.getUsername(), user.getSalt()).toHex()); //激活码
		int result = userDao.addUser(user);
		if(result < 1)
			throw new RuntimeException("注册账号出现异常");
		boolean send = mailService.send(user.getEmail(),"注册激活" ,"欢迎"+user.getName()+",激活码:"+user.getActivationCode());
		if(!send){
			throw new RuntimeException("激活码发送失败");
		}
	}

	public User login(String username) {
		return userDao.login(username);
	}

}
