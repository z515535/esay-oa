package org.gzccc.oa.service;

import java.util.List;
import java.util.Map;

import org.gzccc.oa.bean.User;
import org.gzccc.oa.paging.Pages;


public interface IUserService {
	List<User> findList(Pages<User> pages,Map<String,Object> param);
	
	void addUser(User user);
	
	User login(String username);
}
