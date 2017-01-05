package com.lvhq.platform.modules.sys.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lvhq.platform.common.utils.SpringContextHolder;
import com.lvhq.platform.modules.sys.security.Principal;
import com.lvhq.platform.modules.sys.user.dao.UserDao;
import com.lvhq.platform.modules.sys.user.entity.User;

/**
 * 用户工具类
 * 
 * @author lvhq
 * @date 2016年12月9日 下午2:02:41
 */
public class UserUtils {

	protected static Logger logger = LoggerFactory.getLogger(UserUtils.class);
	
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);

	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
		} catch (UnavailableSecurityManagerException e) {
			logger.error(e.getMessage(), e);
		} catch (InvalidSessionException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 获取当前用户
	 * 
	 * @return 取不到返回 new User()
	 */
	public static User getUser() {
		Principal principal = getPrincipal();
		if (principal != null) {
			User user = userDao.getOne(principal.getId());
			if (user != null) {
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}
}
