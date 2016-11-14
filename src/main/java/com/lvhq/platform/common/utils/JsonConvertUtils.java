package com.lvhq.platform.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

public class JsonConvertUtils {
	private static ObjectMapper oMapper = new ObjectMapper();

	/**
	 * 对象转json的时候时间的格式，默认为yyyy-MM-dd HH:mm:ss
	 */
	private static String defaultDateFormat = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 将实体对象转换成json字符串
	 *
	 * @param t
	 * @return
	 */
	public static <T> String writeTAsJson(T t) {
		String json = "{}";
		try {
			oMapper.setDateFormat(new SimpleDateFormat(defaultDateFormat));
			if (t != null)
				return oMapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 将实体对象转换成json字符串
	 *
	 * @param t
	 * @param dateFormat
	 * @return
	 */
	public static <T> String writeTAsJson(T t, String dateFormat) {
		String json = "{}";
		try {
			if (StringUtils.isNoneBlank(dateFormat))
				oMapper.setDateFormat(new SimpleDateFormat(dateFormat));
			else
				oMapper.setDateFormat(new SimpleDateFormat(defaultDateFormat));
			if (t != null)
				return oMapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 字符串数组转list
	 *
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> writeJson2List(String json) {
		List<T> list = Lists.newArrayList();
		try {
			if (StringUtils.isNotBlank(json)) {
				list = oMapper.readValue(json, List.class);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 字符串数组转Object
	 *
	 * @param json
	 * @param target
	 * @return
	 */
	public static <T> Object writeJson2Object(String json, Object target) {
		Object t = null;
		try {
			if (StringUtils.isNotBlank(json)) {
				t = oMapper.readValue(json, Object.class);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 字符串转成实体类
	 *
	 * @param content
	 * @param valueType
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T readValue(String content, Class<T> valueType) {
		Object result = null;
		try {
			if (StringUtils.isNotBlank(content)) {
				result = oMapper.readValue(content, valueType);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (T) result;
	}

	public static void main(String[] args) {
	}
}
