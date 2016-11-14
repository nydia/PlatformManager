package com.lvhq.platform.common.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lvhq.platform.common.config.Global;

/**
 * 系统管理
 * 
 * @author lvhq
 * @date 2016年11月10日 下午1:11:59
 */
@Service
@Transactional(readOnly = true)
public class SystemService extends BaseService implements InitializingBean {
	
	/**
	 * 获取Key加载信息
	 */
	public static boolean printKeyLoadMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n======================================================================\r\n");
		sb.append("\r\n                   ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆     \r\n");
		sb.append("\r\n    欢迎使用 " + Global.getConfig("productName") + "  - Powered By http://127.0.0.1:8080/platformMgr\r\n");
		sb.append("\r\n                   ☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★     \r\n");
		sb.append("\r\n======================================================================\r\n");
		System.out.println(sb.toString());
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}

}
