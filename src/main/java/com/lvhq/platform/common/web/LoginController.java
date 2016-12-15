package com.lvhq.platform.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 登录Controller
 * 
 * @author lvhq
 * @date 2016年12月9日 下午1:55:01
 */
@Controller
public class LoginController extends BaseController {

	private static String LOGIN = "modules/sys/login";

	@RequestMapping(value = "${adminPath}/login", method = { RequestMethod.GET })
	public String login() {
		return LOGIN;
	}

	@RequestMapping(value = "${adminPath}/logout", method = { RequestMethod.GET })
	public String logout() {
		return LOGIN;
	}
}
