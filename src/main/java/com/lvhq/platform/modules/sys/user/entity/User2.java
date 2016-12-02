package com.lvhq.platform.modules.sys.user.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lvhq.platform.common.config.Global;
import com.lvhq.platform.common.persistence.DataEntity;

/**
 * 用户Entity
 * 
 * @author lvhq
 */

//@Entity
//@Table(name = "sys_user")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "com.lvhq.platform.modules.sys.user.entity.User")
public class User2 extends DataEntity<User2> {
	private static final long serialVersionUID = 1L;

	public static String DEFAULT_PASSWORD = "123456";

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 姓名
	 */
	@Length(min = 1, max = 100, message = "姓名长度必须介于 1 和 100 之间")
	@NotNull(message = "姓名不能为空")
	private String name;

	/**
	 * 密码
	 */
	@JsonIgnore
	@Length(min = 1, max = 100, message = "密码长度必须介于 1 和 100 之间")
	private String password;

	/**
	 * 最后登录IP
	 */
	private String loginIp;

	/**
	 * 最后登录日期
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginDate;

	/**
	 * 是否允许登陆，1=是，2=否
	 */
	private String loginFlag;

	/**
	 * 累计登录次数
	 */
	private Integer loginTimes;

	/**
	 * 盐值
	 */
	@Length(min = 1, max = 64, message = "盐值长度必须介于 1 和 64 之间")
	private String salt;

	/**
	 * 用户类型 1-总部用户，2-中心用户，3幼儿园用户
	 */
	@Length(min = 1, max = 64, message = "盐值长度必须介于 0 和 1 之间")
	private String userType;

	private String userType_;// 数据插入

	// 临时变量
	@JsonBackReference
	private String plainPassword;// 原密码
	@JsonBackReference
	private String oldLoginName;// 原登录名
	@JsonBackReference
	private String newPassword;// 新密码
	@JsonBackReference
	private String oldLoginIp;// 上次登陆IP
	@JsonBackReference
	private Date oldLoginDate;// 上次登陆日期
	private UserInfo userInfo = new UserInfo();
	@JsonBackReference
	private boolean superAdmin; // 是否是超级管理员
	@JsonBackReference
	private String rePassword;// 二次输入密码
	private boolean canEdit = true;// 是否可以编辑(不同级别的用户不可以相互修改,自己不可以修改自己的[角色/机构]等信息)

	public User2() {
		super();
		this.loginFlag = Global.YES;
	}

	public User2(Long id) {
		super(id);
	}

	public User2(Long id, String loginName) {
		super(id);
		this.loginName = loginName;
	}

	public User2(UserInfo userInfo) {
		super();
		this.userInfo = userInfo;
	}

	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

	public Long getId() {
		return id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemarks() {
		return remarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getOldLoginName() {
		return oldLoginName;
	}

	public void setOldLoginName(String oldLoginName) {
		this.oldLoginName = oldLoginName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldLoginIp() {
		if (oldLoginIp == null) {
			return loginIp;
		}
		return oldLoginIp;
	}

	public void setOldLoginIp(String oldLoginIp) {
		this.oldLoginIp = oldLoginIp;
	}

	public Date getOldLoginDate() {
		if (oldLoginDate == null) {
			return loginDate;
		}
		return oldLoginDate;
	}

	public void setOldLoginDate(Date oldLoginDate) {
		this.oldLoginDate = oldLoginDate;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public boolean isSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	public Date getCreateDateStr() {
		return createDate;
	}

	public String getUserType_() {
		return userType_;
	}

	public void setUserType_(String userType_) {
		this.userType_ = userType_;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

}