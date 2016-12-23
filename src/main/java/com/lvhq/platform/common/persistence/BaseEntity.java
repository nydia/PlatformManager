package com.lvhq.platform.common.persistence;

import java.io.Serializable;

/**
 * Entity支持类
 * 
 * @author lvhq
 */
public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 实体编号（唯一标识）
	 */
	protected Long id;
	/**
	 * 插入之前执行方法，子类实现
	 */
	public abstract void preInsert();
	
	public BaseEntity() {
		super();
	}

	public BaseEntity(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	/*
	 * 删除标记（0：正常；1：删除；2：审核；3：归档；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
	public static final String DEL_FLAG_ARCHIVE = "3";

}
