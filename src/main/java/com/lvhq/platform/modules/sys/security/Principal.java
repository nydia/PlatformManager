package com.lvhq.platform.modules.sys.security;

import java.io.Serializable;

import com.lvhq.platform.modules.sys.user.entity.User;

/**
 * 授权用户信息
 * 
 * @author lvhq
 * @date 2016年12月16日 下午4:29:36
 */
public class Principal implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id; // 编号
	private String loginName; // 登录名
	private String name; // 姓名

	public Principal(User user) {
		this.id = user.getId();
		this.loginName = user.getLoginName();
		this.name = user.getName();
	}

	public String getLoginName() {
		return loginName;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
