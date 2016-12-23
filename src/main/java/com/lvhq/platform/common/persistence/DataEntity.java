package com.lvhq.platform.common.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lvhq.platform.modules.sys.user.entity.User;

/**
 * 数据Entity类
 * 
 * @author lvhq
 * 
 */
public abstract class DataEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;

	@Length(min = 0, max = 5000)
	protected String remarks; // 备注

	@JsonIgnore
	protected User createBy; // 创建者

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date createDate; // 创建日期

	@JsonIgnore
	protected User updateBy; // 更新者

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date updateDate; // 更新日期

	@JsonIgnore
	@Length(min = 1, max = 1)
	protected String delFlag; // 删除标记（0：正常；1：删除；2：审核）

	protected Map<String, Object> inc = new HashMap<String, Object>();

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Map<String, Object> getInc() {
		return inc;
	}

	public void setInc(Map<String, Object> inc) {
		this.inc = inc;
	}

	/**
	 * 插入之前执行方法，子类实现
	 */
	@Override
	public void preInsert() {
		this.delFlag = DEL_FLAG_NORMAL;
	}

}
