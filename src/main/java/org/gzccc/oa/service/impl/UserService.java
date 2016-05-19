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
import org.gzccc.oa.util.DateUtil;
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
	
	/**
	 * 注册账号并发送邮件
	 */
	public void addUser(User user) {
		user.setSalt(SaltGenerator.generator());
		//加密密码
		user.setPassword(new Md5Hash(user.getPassword(),user.getSalt()).toHex());
		user.setRegisterDate(String.valueOf(System.currentTimeMillis()));
		user.setActivationCode(new Md5Hash(user.getUsername(), user.getSalt()).toHex()); //激活码
		int result = userDao.addUser(user);
		if(result < 1)
			throw new RuntimeException("注册账号出现异常");
		String url = "</br><a href='http://localhost:8080/easy-oa/user/activation/"+user.getActivationCode()+"'>点击激活</a>";
		boolean send = mailService.send(user.getEmail(),"你已成功在Easy-oa上注册账号，请激活" ,url);
		if(!send){
			throw new RuntimeException("激活码发送失败");
		}
	}
	
	/**
	 * 使用shiro认证登陆和授权
	 */
	public User login(String username) {
		return userDao.login(username);
	}

	@Override
	public void activationUser(String activationCode) throws RuntimeException{
		long now = System.currentTimeMillis();
		User user = userDao.findUser2Activation(activationCode);
		if(null == user){
			throw new RuntimeException("用户不存在");
		}
		if("1".equals(user.getState())){
			throw new RuntimeException("账号已激活");
		}
		int hour = DateUtil.interval2TimeStamp(Long.parseLong(user.getRegisterDate()), now);
		if(hour > 48){
			throw new RuntimeException("激活码已失效");
		}
		user.setState("1");
		userDao.updateForState(user);
	}

}
