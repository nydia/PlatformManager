package com.lvhq.platform.modules.sys.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lvhq.platform.common.persistence.DataEntity;

/**
 * 用户Entity sys_user_info和sys_user 主键相同
 * 
 * @author lvhq
 */
@Entity
@Table(name = "sys_user_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "com.lvhq.platform.modules.sys.user.entity.UserInfo")
public class UserInfo extends DataEntity<UserInfo> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	/**
	 * 工号
	 */
	@Length(min = 0, max = 100, message = "工号长度必须介于 1 和 100 之间")
	private String no;

	/**
	 * 邮箱
	 */
	@Email(message = "邮箱格式不正确")
	@Length(min = 0, max = 200, message = "邮箱长度必须介于 1 和 200 之间")
	private String email;

	/**
	 * 电话
	 */
	@Length(min = 0, max = 200, message = "电话长度必须介于 1 和 200 之间")
	private String phone;

	/**
	 * 手机号
	 */
	@Length(min = 0, max = 200, message = "手机号长度必须介于 1 和 200 之间")
	private String mobile;

	/**
	 * 性别:1-男,2-女,3-未知
	 */
	@Length(min = 0, max = 2, message = "性别长度必须介于 1 和 2 之间")
	private String sex = "3";

	/**
	 * 出生日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Length(min = 0, max = 64, message = "出生日期长度必须介于 1 和 64 之间")
	private Date birthday;

	/**
	 * 证件类型，1=身份证，2=护照，3=港澳通告证，4=其它
	 */
	@Length(min = 0, max = 2, message = "证件类型长度必须介于 1 和 2 之间")
	private String idType;

	/**
	 * 证件号码
	 */
	@Length(min = 0, max = 64, message = "证件号码长度必须介于 1 和 64 之间")
	private String IDNo;

	/**
	 * 紧急联系人
	 */
	@Length(min = 0, max = 64, message = "紧急联系人长度必须介于 1 和 64 之间")
	private String emergencyContacts;

	/**
	 * 紧急联系人电话
	 */
	@Length(min = 0, max = 64, message = "紧急联系人长度必须介于 1 和 64 之间")
	private String emergencyPhone;

	/**
	 * 地址
	 */
	@Length(min = 0, max = 255, message = "地址长度必须介于 1 和 255 之间")
	private String address;

	/**
	 * 头像
	 */
	private String photo;// 默认图片

	/**
	 * 卡号 关联表dev_card字段card_uid
	 */
	private String cardUid;

	/**
	 * 最高学历，1=高中，2=专科，3=本科，4=硕士，5=博士
	 */
	@Length(min = 0, max = 2, message = "最高学历长度必须介于 1 和 2 之间")
	private String maxedu;

	/**
	 * 户籍
	 */
	@Length(min = 0, max = 255, message = "户籍长度必须介于 1 和 255 之间")
	private String province;

	/**
	 * 民族
	 */
	@Length(min = 0, max = 255, message = "民族长度必须介于 1 和 255 之间")
	private String nation;

	/**
	 * 婚姻状况，1=未婚，2=已婚，3=离异
	 */
	@Length(min = 0, max = 2, message = "婚姻状况长度必须介于 1 和 2 之间")
	private String marriage;

	/**
	 * QQ号
	 */
	@Length(min = 0, max = 15, message = "QQ长度必须介于 1 和 15 之间")
	private String qq;

	/**
	 * 微信号
	 */
	@Length(min = 0, max = 100, message = "微信号长度必须介于 1 和 100 之间")
	private String weixin;

	// 临时变量

	public UserInfo() {
		super();
	}

	public UserInfo(Long id) {
		super();
		this.id = id;
	}

	public String getPhoto() {
		if (StringUtils.isBlank(this.photo)
				|| (this.phone != null && this.phone.equals("/upload/images/touxiang_m.png"))
				|| (this.phone != null && this.phone.equals("/upload/images/touxiang_f.png"))) {
			if (this.sex != null && this.sex.equals("1")) {// 如果是男
				this.photo = "/upload/images/touxiang_m.png";
			} else {// 女
				this.photo = "/upload/images/touxiang_f.png";
			}
		} else {
			this.setPhoto(this.photo);
		}
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Long getId() {
		return id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIDNo() {
		return IDNo;
	}

	public void setIDNo(String iDNo) {
		IDNo = iDNo;
	}

	public String getEmergencyContacts() {
		return emergencyContacts;
	}

	public void setEmergencyContacts(String emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCardUid() {
		return cardUid;
	}

	public void setCardUid(String cardUid) {
		this.cardUid = cardUid;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getMaxedu() {
		return maxedu;
	}

	public void setMaxedu(String maxedu) {
		this.maxedu = maxedu;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}