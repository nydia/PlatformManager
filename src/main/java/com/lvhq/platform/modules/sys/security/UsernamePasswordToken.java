package com.lvhq.platform.modules.sys.security;

public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	public UsernamePasswordToken(String loginName, char[] charArray, Object object) {
		super(loginName, charArray, null);
	}

}
