package com.lvhq.platform.modules.sys.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

import com.lvhq.platform.common.config.Global;
import com.lvhq.platform.common.utils.StringUtils;

/**
 * 表单验证（包含验证码）过滤类
 */
@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

	public static final String DEFAULT_MESSAGE_PARAM = "message";
	public static final String DEFAULT_ISAJAXLOGIN_PARAM = "isAjaxLogin";

	private String messageParam = DEFAULT_MESSAGE_PARAM;
	private String isAjaxLogin = DEFAULT_ISAJAXLOGIN_PARAM;

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password == null) {
			password = "";
		}
		return new UsernamePasswordToken(username, password.toCharArray(), null);
	}

	public String getIsAjaxLogin() {
		return isAjaxLogin;
	}

	protected boolean isAjaxLogin(ServletRequest request) {
		return WebUtils.isTrue(request, getIsAjaxLogin());
	}

	public String getMessageParam() {
		return messageParam;
	}

	/**
	 * 登录成功之后跳转URL
	 */
	public String getSuccessUrl() {
		return super.getSuccessUrl();
	}

	@Override
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		if (isAjaxLogin(request)) {
			WebUtils.issueRedirect(request, response, Global.getAdminPath() + "/ajaxLogin", null, true);
		} else {
			WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
		}
	}

	/**
	 * 登录失败调用事件
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		String className = e.getClass().getName(), message = "";
		if (IncorrectCredentialsException.class.getName().equals(className)
				|| UnknownAccountException.class.getName().equals(className)) {
			message = "用户或密码错误, 请重试.";
		} else if (e.getMessage() != null && StringUtils.startsWith(e.getMessage(), "msg:")) {
			message = StringUtils.replace(e.getMessage(), "msg:", "");
		} else {
			message = "系统出现点问题，请稍后再试！";
			e.printStackTrace(); // 输出到控制台
		}
		request.setAttribute(getFailureKeyAttribute(), className);
		request.setAttribute(getMessageParam(), message);
		return true;
	}

}