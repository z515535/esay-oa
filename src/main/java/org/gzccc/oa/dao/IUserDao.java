package org.gzccc.oa.dao;

import java.util.List;
import java.util.Map;

import org.gzccc.oa.bean.User;

public interface IUserDao {
	List<User> findList(Map<String,Object> param);
	
	int addUser(User user);
	
	User login(String username);
	
	/**
	 * 根据激活码查找用户
	 * @param activation_code
	 * @return
	 */
	User findUser2Activation(String activationCode);
	
	/**
	 * 激活用户
	 * @param user
	 * @return
	 */
	int updateForState(User user);
}
