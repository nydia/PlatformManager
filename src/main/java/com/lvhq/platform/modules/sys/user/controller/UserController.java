package com.lvhq.platform.modules.sys.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.lvhq.platform.common.utils.AjaxObject;
import com.lvhq.platform.modules.sys.user.entity.User;

@Controller
@RequestMapping(value = "/sys/user")
public class UserController {

	private final String LIST = "modules/sys/user/list";

	@RequestMapping(value = "/pre/list", method = { RequestMethod.GET })
	@ResponseBody
	public String preList(User user, HttpServletRequest request, HttpServletResponse response) {
		return LIST;
	}

	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	@ResponseBody
	public String list(User user) {
		List<User> list = Lists.newArrayList();

		return AjaxObject.newOk("", list).toString();
	}

}
