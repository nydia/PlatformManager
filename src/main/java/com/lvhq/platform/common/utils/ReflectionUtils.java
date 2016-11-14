/**
 * Copyright (c) 2005-2012 springside.org.cn
 */
package com.lvhq.platform.common.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.NestedNullException;

/**
 * 反射工具类
 * 
 * @author lvhq
 */

public class ReflectionUtils {

	public static <T> String invokeGetter(T bean, String propertyName) {
		try {
			return BeanUtils.getNestedProperty(bean, propertyName);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NestedNullException e) {
		} catch (Exception e) {
		}
		return null;
	}

	public static <T> void invokeSetter(T bean, String propertyName, Object value) {
		try {
			BeanUtils.setProperty(bean, propertyName, value);
		} catch (IllegalAccessException | InvocationTargetException e) {
		}
	}

}
