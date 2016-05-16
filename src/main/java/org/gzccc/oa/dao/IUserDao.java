package org.gzccc.oa.dao;

import java.util.List;
import java.util.Map;

import org.gzccc.oa.bean.User;

public interface IUserDao {
	List<User> findList(Map<String,Object> param);
	
	int addUser(User user);
	
	User login(String username);
}
