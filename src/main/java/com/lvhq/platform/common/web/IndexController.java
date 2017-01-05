package com.lvhq.platform.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页Controller
 * 
 * @author lvhq
 * @date 2017年1月5日 上午10:57:02
 */
@Controller
public class IndexController extends BaseController {

	private static String INDEX = "index";

	/**
	 * 首页content内容跳转
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "${adminPath}/index")
	public String leftMenu(HttpServletRequest request, HttpServletResponse response) {
		return INDEX;
	}
}
