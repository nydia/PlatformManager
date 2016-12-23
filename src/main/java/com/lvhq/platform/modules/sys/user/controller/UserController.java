package com.lvhq.platform.modules.sys.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.lvhq.platform.common.utils.AjaxObject;
import com.lvhq.platform.common.web.BaseController;
import com.lvhq.platform.modules.sys.user.entity.User;
import com.lvhq.platform.modules.sys.user.service.UserService;

/**
 * 用户管理 注[访问路径：http://localhost:8080/PlatformManager/admin/sys/user/pre/list]
 * 
 * @author lvhq
 * @date 2016年12月5日 下午4:36:02
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends BaseController {

	private final String LIST = "modules/sys/user/list";

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/pre/list", method = { RequestMethod.GET })
	public String preList(User user, HttpServletRequest request, HttpServletResponse response) {
		return LIST;
	}

	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	@ResponseBody
	public String list(User user) {
		List<User> list = Lists.newArrayList();

		return AjaxObject.newOk("", list).toString();
	}

	@RequestMapping(value = "/initData", method = { RequestMethod.POST, RequestMethod.GET })
	public String initData() {
		List<User> userList = Lists.newArrayList();
		User user_admin = new User();
		user_admin.setLoginName("admin");
		user_admin.setPassword(User.DEFAULT_PASSWORD);
		user_admin.setName("admin管理员");
		userList.add(user_admin);
		
		for(User e : userList){
			userService.insert(e);
		}
		return AjaxObject.newOk("init ok !!").toString();
	}
}
