package com.lvhq.platform.modules.sys.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvhq.platform.modules.sys.user.entity.User;
import com.lvhq.platform.modules.sys.user.service.UserService;
import com.lvhq.platform.modules.sys.utils.UserUtils;

@Service
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	public static final String SESSION_USER_KEY = "nydia";

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		@SuppressWarnings("unused")
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(ShiroRealm.SESSION_USER_KEY);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole("admin");
		return info;
	}

	/**
	 * 认证回调函数，登录信息和用户验证信息验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		// 把token转换成User对象
		User userLogin = tokenToUser((UsernamePasswordToken) authcToken);
		// 验证用户是否可以登录
		User ui = userService.findByLoginName(userLogin.getLoginName());
		if (ui == null) {
			return null; // 异常处理，找不到数据
		}
		// 设置session
		Session session = UserUtils.getSubject().getSession();
		session.setAttribute(ShiroRealm.SESSION_USER_KEY, ui);
		// 当前 Realm 的 name
		String realmName = this.getName();
		// 登陆的主要信息: 可以是一个实体类的对象, 但该实体类的对象一定是根据 token 的 username 查询得到的.
		//Principal principal = (Principal) authcToken.getPrincipal();
		return new SimpleAuthenticationInfo(new Principal(ui), userLogin.getPassword(), realmName);
	}

	private User tokenToUser(UsernamePasswordToken authcToken) {
		User user = new User();
		user.setLoginName(authcToken.getUsername());
		user.setPassword(String.valueOf(authcToken.getPassword()));
		return user;
	}

}
