package com.lvhq.platform.common.utils;

import java.util.List;

import com.lvhq.platform.common.persistence.Page;

/**
 * form提交后返回json数据结构statusCode=statusCode.ok表示操作成功, 做页面跳转等操作.
 * statusCode=statusCode.error表示操作失败, 提示错误原因.
 * statusCode=statusCode.timeout表示session超时，下次点击时跳转到loginUrl
 * {"statusCode":"200", "message":"操作成功", {"statusCode":"300", "message":"操作失败"}
 * {"statusCode":"301", "message":"会话超时"}
 * 
 * @author lvhq
 * @date 2016年11月14日 下午2:26:28
 */

public class AjaxObject {
	// 状态码
	public final static int STATUS_CODE_SUCCESS = 200;
	public final static int STATUS_CODE_FAILURE = 300;
	public final static int STATUS_CODE_TIMEOUT = 301;
	public final static int STATUS_CODE_FORBIDDEN = 403;

	private int statusCode = STATUS_CODE_SUCCESS;
	private String message = "";
	private String returnValue;

	// DataTable 参数定义
	private String sEcho = "";
	private String iTotalRecords = "0";
	private String iTotalDisplayRecords = "0";
	private String aaData = "";

	public AjaxObject() {

	}

	public AjaxObject(String message) {
		this.message = message;
	}

	/**
	 * 构造函数
	 * 
	 * @param statusCode
	 */
	public AjaxObject(int statusCode) {
		super();
		this.statusCode = statusCode;
	}

	/**
	 * 构造函数
	 * 
	 * @param statusCode
	 * @param message
	 */
	public AjaxObject(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	/**
	 * 返回 statusCode 的值
	 * 
	 * @return statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * 设置 statusCode 的值
	 * 
	 * @param statusCode
	 */
	public AjaxObject setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	/**
	 * 返回 message 的值
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置 message 的值
	 * 
	 * @param message
	 */
	public AjaxObject setMessage(String message) {
		this.message = message;
		return this;
	}

	public static AjaxObject newOk(String message) {
		return new AjaxObject(STATUS_CODE_SUCCESS, message);
	}

	public static AjaxObject newOk(String message, String returnValue) {
		AjaxObject ajaxObject = new AjaxObject(STATUS_CODE_SUCCESS, message);
		ajaxObject.setReturnValue(returnValue);
		return ajaxObject;
	}

	/**
	 * DataTable 返回值处理
	 * 
	 * @param message
	 * @param page
	 * @return
	 */
	public static AjaxObject newOk(String message, @SuppressWarnings("rawtypes") Page page) {
		AjaxObject ajaxObject = new AjaxObject(STATUS_CODE_SUCCESS, message);

		String echo = "";
		String jsonData = "{}";
		@SuppressWarnings("unused")
		String listSize = "0";
		String iTotalDisplayRecords = "0";
		String iTotalRecords = "0";
		if (page != null) {
			echo = String.valueOf(page.getInitEcho());
			iTotalDisplayRecords = String.valueOf(page.getCount());
			iTotalRecords = String.valueOf(page.getCount());
			if (page.getList() != null) {
				jsonData = JsonConvertUtils.writeTAsJson(page.getList());
				listSize = String.valueOf(page.getList().size());
			}
		}

		ajaxObject.setsEcho(echo);
		// ajaxObject.setiTotalDisplayRecords(listSize);
		// 设置这个只会显示当前页
		ajaxObject.setiTotalDisplayRecords(iTotalDisplayRecords);
		ajaxObject.setiTotalRecords(iTotalRecords);
		ajaxObject.setReturnValue(jsonData);
		return ajaxObject;
	}

	/**
	 * DataTable 返回值处理
	 * 
	 * @param message
	 * @param list
	 * @return
	 */
	public static <T> AjaxObject newOk(String message, List<T> list) {
		AjaxObject ajaxObject = new AjaxObject(STATUS_CODE_SUCCESS, message);
		ajaxObject.setsEcho("");
		String jsonData = "{}";
		String listSize = "0";
		if (list != null) {
			jsonData = JsonConvertUtils.writeTAsJson(list);
			listSize = String.valueOf(list.size());
		}
		ajaxObject.setiTotalDisplayRecords(listSize);
		ajaxObject.setiTotalRecords(listSize);
		ajaxObject.setReturnValue(jsonData);
		return ajaxObject;
	}

	/**
	 * DataTable 返回值处理
	 * 
	 * @param message
	 * @param page
	 * @return
	 */
	public static AjaxObject newError(String message, @SuppressWarnings("rawtypes") Page page) {
		AjaxObject ajaxObject = new AjaxObject(STATUS_CODE_FAILURE, message);
		ajaxObject.setsEcho(String.valueOf(page.getInitEcho()));
		String jsonData = JsonConvertUtils.writeTAsJson(page.getList());
		ajaxObject.setiTotalDisplayRecords(String.valueOf(page.getCount()));
		ajaxObject.setiTotalRecords(String.valueOf(page.getCount()));
		ajaxObject.setReturnValue(jsonData);
		return ajaxObject;
	}

	public static AjaxObject newError(String message) {
		return new AjaxObject(STATUS_CODE_FAILURE, message);
	}

	public static AjaxObject newTimeout(String message) {
		return new AjaxObject(STATUS_CODE_TIMEOUT, message);
	}

	public static AjaxObject newForbidden(String message) {
		return new AjaxObject(STATUS_CODE_TIMEOUT, message);
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{").append("\"statusCode\":\"" + statusCode + "\",").append("\"message\":\"" + message + "\",");
		if (StringUtils.isNoneBlank(returnValue) && (returnValue.startsWith("[") || returnValue.startsWith("{"))) {
			buffer.append("\"returnValue\":" + returnValue + ",");
		} else {
			if (returnValue == null) {
				buffer.append("\"returnValue\":[],");
			} else {
				buffer.append("\"returnValue\":\"" + returnValue + "\",");
			}
		}
		buffer.append("\"sEcho\":\"" + sEcho + "\",").append("\"iTotalRecords\":\"" + iTotalRecords + "\",")
				.append("\"iTotalDisplayRecords\":\"" + iTotalDisplayRecords + "\"").append("}");
		return buffer.toString();
	}

	// DataTable 属性
	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(String iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public String getAaData() {
		return aaData;
	}

	public void setAaData(String aaData) {
		this.aaData = aaData;
	}

	public String getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(String iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
