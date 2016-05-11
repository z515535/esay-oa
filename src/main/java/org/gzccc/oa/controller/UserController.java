package org.gzccc.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.gzccc.oa.bean.User;
import org.gzccc.oa.paging.Pages;
import org.gzccc.oa.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	@Inject
	private IUserService userService;
	
	@ResponseBody
	@RequestMapping("/list")
	public List<User> list(Pages<User> pages){
		Map<String,Object> param = new HashMap<String,Object>(1);
		param.put("id", 1);
		return userService.findList(pages, param);
	}
}
