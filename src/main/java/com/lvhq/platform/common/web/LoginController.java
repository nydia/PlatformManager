package com.lvhq.platform.common.web;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lvhq.platform.modules.sys.security.UsernamePasswordToken;
import com.lvhq.platform.modules.sys.user.entity.User;
import com.lvhq.platform.modules.sys.utils.UserUtils;

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
	public String login(User user, Model model) {
		return LOGIN;
	}

	@RequestMapping(value = "${adminPath}/login", method = { RequestMethod.POST })
	public String loginIn(User user, Model model) {
		String msg = loginUser(user);
		if (!"success".equals(msg)) {
			model.addAttribute("msg", "用户不存在或密码错误！");
		} else {
			model.addAttribute("msg", "登陆成功！");// 返回到页面说夹带的参数
			model.addAttribute("name", user.getLoginName());
		}
		return LOGIN;
	}

	@RequestMapping(value = "${adminPath}/logout", method = { RequestMethod.GET })
	public String logout() {
		UserUtils.getSubject().logout();
		System.out.println("adminPath---" + adminPath);
		return "redirect:" + adminPath + "/login";
	}

	private String loginUser(User user) {
		if (isRelogin(user)) {
			return "success";// 如果已经登陆，无需重新登录
		}
		return shiroLogin(user); // 调用shiro的登陆验证
	}

	private String shiroLogin(User user) {
		// 组装token，包括客户公司名称、简称、客户编号、用户名称；密码
		UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassword().toCharArray(),
				null);
		token.setRememberMe(true);

		// shiro登陆验证
		try {
			UserUtils.getSubject().login(token);
		} catch (UnknownAccountException ex) {
			return "用户不存在或者密码错误！";
		} catch (IncorrectCredentialsException ex) {
			ex.printStackTrace();
			return "用户不存在或者密码错误！";
		} catch (AuthenticationException ex) {
			ex.printStackTrace();
			return ex.getMessage(); // 自定义报错信息
		} catch (Exception ex) {
			ex.printStackTrace();
			return "内部错误，请重试！";
		}
		return "success";
	}

	private boolean isRelogin(User user) {
		Subject us = UserUtils.getSubject();
		if (us.isAuthenticated()) {
			return true; // 参数未改变，无需重新登录，默认为已经登录成功
		}
		return false; // 需要重新登陆
	}
}
