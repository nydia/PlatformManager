package com.lvhq.platform.common.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;
import com.lvhq.platform.modules.sys.security.Principal;
import com.lvhq.platform.modules.sys.security.UsernamePasswordToken;
import com.lvhq.platform.modules.sys.user.entity.User;
import com.lvhq.platform.modules.sys.user.service.UserService;
import com.lvhq.platform.modules.sys.utils.UserUtils;

/**
 * 登录Controller
 * 
 * @author lvhq
 * @date 2016年12月9日 下午1:55:01
 */
@Controller
public class LoginController extends BaseController {

	private static String LOGIN = "modules/sys/login2";
	private static String INDEX = "index";

	@Autowired
	private UserService userService;

	@RequestMapping(value = "${adminPath}/login", method = { RequestMethod.GET })
	public String login(User user, Model model) {
		//this.initUserData();
		return LOGIN;
	}

	@RequestMapping(value = "${adminPath}/login", method = { RequestMethod.POST })
	public String loginIn(User user, Model model) {
		String msg = loginUser(user);
		if (!"success".equals(msg)) {
			model.addAttribute("msg", "用户不存在或密码错误！");
			return LOGIN;
		} else {
			model.addAttribute("msg", "登陆成功！");// 返回到页面说夹带的参数
			model.addAttribute("name", user.getLoginName());
			return INDEX;
		}
	}
	
	/**
	 * 登录成功，进入管理首页
	 */
	@RequestMapping(value = "${adminPath}")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return INDEX;
	}

	@RequestMapping(value = "${adminPath}/logout", method = { RequestMethod.GET })
	public String logout() {
		Principal principal = UserUtils.getPrincipal();
		if (principal != null) {

		}
		UserUtils.getSubject().logout();
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

	@SuppressWarnings("unused")
	private void initUserData() {
		List<User> userList = Lists.newArrayList();
		User user_admin = new User();
		user_admin.setLoginName("admin");
		user_admin.setPassword(User.DEFAULT_PASSWORD);
		user_admin.setName("admin管理员");
		userList.add(user_admin);

		for (User e : userList) {
			userService.insert(e);
		}
	}
}
