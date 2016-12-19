package com.lvhq.platform.modules.sys.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户工具类
 * 
 * @author lvhq
 * @date 2016年12月9日 下午2:02:41
 */
public class UserUtils {

	protected static Logger logger = LoggerFactory.getLogger(UserUtils.class);
	
	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}
}
