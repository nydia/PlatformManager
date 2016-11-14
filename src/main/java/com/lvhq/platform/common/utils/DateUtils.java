package com.lvhq.platform.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.google.common.collect.Lists;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 * @author lvhq
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd",
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd",
			"yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期 格式（yyyy-MM-dd）
	 */
	public static Date getNowDate() {
		Date date = null;
		try {
			date = parseDate(getDate(), "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 得到当前年月日时分秒 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static Date getDateTimeInstance() {
		Date date = new Date();
		try {
			DateFormat df2 = DateFormat.getDateTimeInstance();// 可以精确到时分秒
			df2.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		try {
			if (pattern != null && pattern.length > 0) {
				formatDate = DateFormatUtils
						.format(date, pattern[0].toString());
			} else {
				formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
			}
		} catch (Exception exc) {
		}
		return formatDate;
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date) {
		Object[] pattern = parsePatterns;
		String formatDate = null;
		try {
			if (pattern != null && pattern.length > 0) {
				formatDate = DateFormatUtils
						.format(date, pattern[0].toString());
			} else {
				formatDate = formatDate(date, "yyyy-MM-dd");
			}
		} catch (Exception exc) {
		}
		return formatDate;
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd"
	 */
	public static String formatDate(Date date, String pattern) {
		String formatDate = null;
		try {
			if (date != null && StringUtils.isNoneBlank(pattern)) {
				formatDate = DateFormatUtils.format(date, pattern.toString());
			} else if (date != null && StringUtils.isBlank(pattern)) {
				formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
			} else {
				formatDate = "";
			}
		} catch (Exception exc) {
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 日期型字符串转化为指定日期 格式
	 */
	public static Date parseDateStr(Object str, String pattern) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), pattern);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 *
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 *
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 *
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 *
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60
				* 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "."
				+ sss;
	}

	/**
	 * 获取两个日期之间的天数
	 *
	 * @param before
	 * @param after
	 * @return
	 */
	public static long getDistanceOfTwoDate(Date before, Date after) {
		long distance = 0;
		try {
			if (before != null && after != null) {
				long beforeTime = before.getTime();
				long afterTime = after.getTime();
				distance = (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
			} else {
				distance = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			distance = 0;
		}
		return distance;
	}

	/**
	 * 获取两个日期之间的月数
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getMonthSpace(Date min, Date max) {
		try {
			int result = 0;
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(parseDateStr(DateFormatUtils.format(min, "yyyy-MM-dd"),
					"yyyy-MM-dd"));
			c2.setTime(parseDateStr(DateFormatUtils.format(max, "yyyy-MM-dd"),
					"yyyy-MM-dd"));
			int monthDiffer = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
			int yearDiffer = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12;
			monthDiffer = monthDiffer == 0 ? 1 : Math.abs(monthDiffer);
			result = monthDiffer + yearDiffer;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 比较Date大小
	 *
	 * @param smallDate
	 *            小
	 * @param bigDate
	 *            大
	 * @return 如果正确为true，否则为false
	 */
	public static boolean compareDate(Date smallDate, Date bigDate) {
		if (smallDate == null || bigDate == null) {
			return false;
		}
		if (smallDate.getTime() <= bigDate.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 比较time大小(只比较时分秒)
	 *
	 * @param bigTime
	 *            小(HH:mm:ss)
	 * @param smallTime
	 *            大(HH:mm:ss)
	 * @return -1小于，0等于，1大于，-2其他
	 */
	public static int compareTime(String bigTime, String smallTime) {
		if (StringUtils.isBlank(smallTime) || StringUtils.isBlank(bigTime)) {
			return -2;
		}
		Date db = parseDateStr("1000-01-01 " + bigTime, "yyyy-MM-dd HH:mm:ss");
		Date ds = parseDateStr("1000-01-01 " + smallTime, "yyyy-MM-dd HH:mm:ss");
		if (db.getTime() > ds.getTime()) {
			return 1;
		}
		if (db.getTime() == ds.getTime()) {
			return 0;
		}
		return -1;
	}

	/**
	 * 比较Date是否相等
	 *
	 * @param date1
	 * @param date2
	 * @return 如果正确为true，否则为false
	 */
	public static boolean equalDate(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return true;
		}
		if (date1.getTime() == date2.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 获取某段时间内的所有日期
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<Date> getDatesTwoDate(Date startDate, Date endDate) {
		try {
			if (startDate != null && endDate != null) {
				List<Date> date = new ArrayList<Date>();
				// date.add(startDate);
				Calendar calBegin = Calendar.getInstance();
				// 使用给定的 Date 设置此 Calendar 的时间
				calBegin.setTime(startDate);
				Calendar calEnd = Calendar.getInstance();
				// 使用给定的 Date 设置此 Calendar 的时间
				calEnd.setTime(endDate);
				// 测试此日期是否在指定日期之后
				while (endDate.after(calBegin.getTime()) || (endDate.getTime() == calBegin.getTime().getTime())) {
					date.add(calBegin.getTime());
					// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
					calBegin.add(Calendar.DAY_OF_MONTH, 1);
				}
				return date;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据给定时间获取N天之前的时间
	 *
	 * @param date
	 * @param beforeDays
	 * @return
	 */
	public static Date getBeforeDate(Date date, int beforeDays) {
		try {
			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, -beforeDays); // 得到前一天
				return calendar.getTime();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String HaveTimeToName() {
		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		return year + "" + month + "" + date + "" + hour + "" + minute + ""
				+ second;
	}

	/**
	 * 获取当前天
	 */
	public static int getCurrentDay() {
		int day = 0;
		try {
			Calendar cal = Calendar.getInstance();
			day = cal.get(Calendar.DATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day;
	}

	/**
	 * 获取当前月
	 */
	public static int getCurrentMonth() {
		int month = 0;
		try {
			Calendar cal = Calendar.getInstance();
			month = cal.get(Calendar.MONTH) + 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return month;
	}

	/**
	 * 获取当前年
	 */
	public static int getCurrentYear() {
		int year = 0;
		try {
			Calendar cal = Calendar.getInstance();
			year = cal.get(Calendar.YEAR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return year;
	}

	/**
	 * 根据自然年，自然月获得--月开始日期
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMinMonthDate(Integer year, Integer month) {
		if (month != null) {
			Date nowdate = new Date();
			Calendar cal = Calendar.getInstance();
			/* 设置为当前时间 */
			cal.setTime(nowdate);
			/* 当前日期年份 */
			cal.add(Calendar.YEAR, year - getCurrentYear());
			/* 当前日期月份 */
			cal.add(Calendar.MONTH, month - getCurrentMonth());
			// 得到当前月的第一天
			cal.set(Calendar.DAY_OF_MONTH,
					cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			return cal.getTime();
		}
		return null;
	}

	/**
	 * 根据自然年，自然月获得--月结束日期
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMaxMonthDate(Integer year, Integer month) {
		if (month != null) {
			Date nowdate = new Date();
			Calendar cal = Calendar.getInstance();
			/* 设置为当前时间 */
			cal.setTime(nowdate);
			/* 当前日期年份 */
			cal.add(Calendar.YEAR, year - getCurrentYear());
			/* 当前日期月份 */
			cal.add(Calendar.MONTH, month - getCurrentMonth());
			// 得到当前月的最后一天
			cal.set(Calendar.DAY_OF_MONTH,
					cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			return cal.getTime();
		}
		return null;
	}

	/**
	 * 根据时间获得--月开始日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getMinMonthDate(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			/* 设置为指定时间 */
			cal.setTime(date);
			// 得到指定时间所在月的第一天
			cal.set(Calendar.DAY_OF_MONTH,
					cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			return cal.getTime();
		}
		return null;
	}

	/**
	 * 根据时间获得--月结束日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getMaxMonthDate(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			/* 设置为指定时间 */
			cal.setTime(date);
			// 得到指定时间所在月的最后一天
			cal.set(Calendar.DAY_OF_MONTH,
					cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			return cal.getTime();
		}
		return null;
	}

	/**
	 * 根据时间获得--得到本周周一
	 *
	 * @return yyyy-MM-dd
	 */
	public static Date getWeekMonday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		return c.getTime();
	}

	/**
	 * 根据时间获得--得到本周周日
	 *
	 * @return yyyy-MM-dd
	 */
	public static Date getWeekSunday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 7);
		return c.getTime();
	}

	/**
	 * 判断时间是否在时间段内
	 *
	 * @param date
	 *            当前时间 yyyy-MM-dd HH:mm:ss
	 * @param strDateBegin
	 *            开始时间 00:00:00
	 * @param strDateEnd
	 *            结束时间 00:05:00
	 * @return
	 */
	public static boolean isInDate(Date date, String strDateBegin,
			String strDateEnd) {
		String strDate = formatDate(date, "yyyy-MM-dd HH:mm:ss ");
		if (StringUtils.isBlank(strDateBegin)
				|| StringUtils.isBlank(strDateEnd)) {
			return false;
		}
		// 截取当前时间年月日
		int strDateY = Integer.parseInt(strDate.substring(0, 4));
		int strDateM1 = Integer.parseInt(strDate.substring(5, 7));
		int strDateD = Integer.parseInt(strDate.substring(8, 10));
		// 截取开始时间年月日
		int strDateBeginY = Integer.parseInt(strDateBegin.substring(0, 4));
		int strDateBeginM1 = Integer.parseInt(strDateBegin.substring(5, 7));
		int strDateBeginD = Integer.parseInt(strDateBegin.substring(8, 10));
		// 截取结束时间年月日
		int strDateEndY = Integer.parseInt(strDateEnd.substring(0, 4));
		int strDateEndM1 = Integer.parseInt(strDateEnd.substring(5, 7));
		int strDateEndD = Integer.parseInt(strDateEnd.substring(8, 10));
		if (strDateY < strDateEndY) {
			return true;
		}
		if (strDateY > strDateEndY) {
			return false;
		}
		// 跨年判断
		if (strDateY > strDateBeginY) {
			if (strDateM1 <= strDateEndM1) {
				if (strDateD <= strDateEndD) {
					return true;
				}
			}
		}
		if (strDateY == strDateBeginY && strDateY == strDateEndY) {
			if (strDateM1 < strDateEndM1) {
				return true;
			}
			if (strDateM1 == strDateEndM1) {
				if(strDateD <= strDateEndD){
					return true;
				}
			}
			if (strDateM1 >= strDateBeginM1 && strDateM1 <= strDateEndM1) {
				if (strDateD >= strDateBeginD && strDateD <= strDateEndD) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 根据日期获取天
	 *
	 * @param date
	 * @return
	 */
	public static int getDayByDate(Date date) {
		int day = 0;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			day = cal.get(Calendar.DATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day;
	}

	/**
	 * 根据日期获取月
	 *
	 * @param date
	 * @return
	 */
	public static int getMonthByDate(Date date) {
		int month = 0;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			month = cal.get(Calendar.MONTH) + 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return month;
	}

	/**
	 * 根据日期获取年
	 *
	 * @param date
	 * @return
	 */
	public static int getYearByDate(Date date) {
		int year = 0;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			year = cal.get(Calendar.YEAR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return year;
	}

	/**
	 * 计算N月之前的时间
	 *
	 * @param month
	 * @return
	 */
	public static Date getDateByNMonth(Integer month) {
		try {
			if (month != null) {
				Date nowdate = new Date();
				Calendar cal = Calendar.getInstance();
				/* 设置为当前时间 */
				cal.setTime(nowdate);
				/* 当前日期年份 */
				cal.add(Calendar.YEAR, -month / 12);
				/* 当前日期月份 */
				cal.add(Calendar.MONTH, -month % 12);
				// 得到当前月的第一天
				cal.set(Calendar.DAY_OF_MONTH, getCurrentDay());
				return cal.getTime();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * 根据年月 获取该月有多少天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Integer getDayByMonth(Integer year, Integer month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year); // 2010年
		c.set(Calendar.MONTH, month); // 6 月
		/*
		 * System.out.println("------------" + c.get(Calendar.YEAR) + "年" +
		 * (c.get(Calendar.MONTH) + 1) + "月的天数和周数-------------");
		 */
		return c.getActualMaximum(Calendar.DAY_OF_MONTH + 1);
	}

	public static Integer getMonthDays(String year, String month) {
		int days = 0;
		if (!month.equals("02")) {
			switch (month) {
			case "01":
			case "03":
			case "05":
			case "07":
			case "08":
			case "10":
			case "12":
				days = 31;
				break;
			case "04":
			case "06":
			case "09":
			case "11":
				days = 30;

			}
		} else {
			// 闰年
			if (Integer.parseInt(year) % 4 == 0
					&& Integer.parseInt(year) % 100 != 0
					|| Integer.parseInt(year) % 400 == 0)
				days = 29;
			else
				days = 28;

		}
		return days;

	}

	/**
	 * 根据日期获取开始时间
	 * 格式化日期：yyyy-MM-dd 00:00:00
	 *
	 * @param date
	 * @return
	 */
	public static Date getMinDateCondition(Date date) {
		if (date != null) {
			String dateStr = formatDate(date,"yyyy-MM-dd");
			Date newDate = parseDateStr(dateStr+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
			return newDate;
		}
		return null;
	}

	/**
	 * 根据日期获取结束时间
	 * 格式化日期：yyyy-MM-dd 23:59:59
	 *
	 * @param date
	 * @return
	 */
	public static Date getMaxDateCondition(Date date) {
		if (date != null) {
			String dateStr = formatDate(date,"yyyy-MM-dd");
			Date newDate = parseDateStr(dateStr+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
			return newDate;
		}
		return null;
	}

	/**
	 * 当前季度的开始时间，即2012-01-1 00:00:00
	 *
	 * @return
	 */
	public static Date getCurrentQuarterStartTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = parseDateStr(formatDate(c.getTime()) + " 00:00:00",
					"yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 当前季度的结束时间，即2012-03-31 23:59:59
	 *
	 * @return
	 */
	public static Date getCurrentQuarterEndTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 2);
				c.set(Calendar.DATE, 31);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 8);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 11);
				c.set(Calendar.DATE, 31);
			}
			now = parseDateStr(formatDate(c.getTime()) + " 23:59:59",
					"yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 获取上周一
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastWeekMonday(Date date) {
		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		return cal.getTime();
	}

	/**
	 * 获取上周日
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastWeekSunday(Date date) {
		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.set(Calendar.DAY_OF_WEEK, 1);

		return cal.getTime();
	}

	/**
	 * 获取上月第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastMonthMinDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取上月最后天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastMonthMaxDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 获取上月的当前时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastMonthCurrentDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 取得上一个时间
		calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) - 1);
		// 取得上一个月的下一天
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 返回指定日期的季度
	 *
	 * @param date
	 * @return
	 */
	public static int getQuarterOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) / 3 + 1;
	}

	/**
	 * 返回指定年季的季的第一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1) {
			month = 1 - 1;
		} else if (quarter == 2) {
			month = 4 - 1;
		} else if (quarter == 3) {
			month = 7 - 1;
		} else if (quarter == 4) {
			month = 10 - 1;
		} else {
			month = calendar.get(Calendar.MONTH);
		}
		return getMinMonthDate(year, month);
	}

	/**
	 * 返回指定年季的季的最后一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1) {
			month = 3 - 1;
		} else if (quarter == 2) {
			month = 6 - 1;
		} else if (quarter == 3) {
			month = 9 - 1;
		} else if (quarter == 4) {
			month = 12 - 1;
		} else {
			month = calendar.get(Calendar.MONTH);
		}
		return getMaxMonthDate(year, month);
	}

	/**
	 * 获取某年第一天日期
	 *
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 *
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 得到本周周一
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getMondayOfThisWeek() {
		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		return sdfInput.format(c.getTime());
	}

	/**
	 * 得到本周周日
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getSundayOfThisWeek() {
		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 7);
		return sdfInput.format(c.getTime());
	}

	/**
	 * 得到本周周一
	 *
	 * @return yyyy-MM-dd
	 */
	public static Date getCurrentWeekMonday() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		return c.getTime();
	}

	/**
	 * 得到本周周日
	 *
	 * @return yyyy-MM-dd
	 */
	public static Date getCurrentWeekSunday() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 7);
		return c.getTime();
	}

	/**
	 * 获取下个月第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getNextMonthMinDay(Date date) {
		if(date == null){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 获取下个月最后天
	 *
	 * @param date
	 * @return
	 */
	public static Date getNextMonthMaxDay(Date date) {
		if(date == null){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 2);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		return cal.getTime();
	}

	public static Date getMonthStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (1 - index));
		return calendar.getTime();
	}

	public static Date getMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (-index));
		return calendar.getTime();
	}

	private static Date getNext(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

    /**
     * 输入日期,求出当月的所有日期
     * @param date
     * @return
     */
	public static List<Date> getMonthAllDate(Date date) {
		List<Date> dateList = Lists.newArrayList();
		Date monthStart = getMonthStart(date);
		Date monthEnd = getMonthEnd(date);
		while (!monthStart.after(monthEnd)) {
			dateList.add(monthStart);
			monthStart = getNext(monthStart);
		}
		return dateList;
	}

	/**
	 * 获取一段时间内的所有日期
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static List<Date> getPeriodAllDate(Date startTime, Date endTime) {
		List<Date> dateList = Lists.newArrayList();
		while (!startTime.after(endTime)) {
			dateList.add(startTime);
			startTime = getNext(startTime);
		}
		return dateList;
	}

	public static List<Date> getMonthWorkDays(Date date) {
		List<Date> dates = new ArrayList<Date>();

		int year = getYearByDate(date);
		int month = getMonthByDate(date);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);

		while (cal.get(Calendar.YEAR) == year
				&& cal.get(Calendar.MONTH) < month) {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
				dates.add((Date) cal.getTime().clone());
			}
			cal.add(Calendar.DATE, 1);
		}
		return dates;
	}

    /**
     * 对时间进行排序
     * @return List<String>
     * @throws ParseException *
     * @author songlc
     */
    public static List<String> sortListDesc(List<Date> dateList) {
    	 List<String> retStr=new ArrayList<String>();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         for(int i=0;i<dateList.size();i++){
             String dateStr = sdf.format(dateList.get(i));
             retStr.add(dateStr);
         }
         Collections.sort(retStr);
         return retStr;
    }

	/**
	 * 根据年份获取月份
	 */
	public static String[] getAllMonthByyear(String year) {
		int monthNum=Integer.parseInt(formatDate(new Date(), "MM"));
		if (StringUtils.isNotBlank(year)) {
			if (!year.equals(formatDate(new Date(), "yyyy"))) {
				monthNum = 12;
			}
		}else{
			year=formatDate(new Date(), "yyyy");
		}
		String[] datetime = new String[monthNum];
		for (int i = 1; i <=monthNum; i++) {
			String months = i <= 9 ? "0" + i : i + "";
			datetime[i-1]=year+"-"+months;
		}
		return datetime;
	}

	/***
	 * 获取当前年份 最近五年
	*/
	public static List<String> getRecentYear(){
		List<String> list=new ArrayList<String>();
		String nowyear=formatDate(new Date(), "yyyy");
		  for(int i=0;i<5;i++){
			  String sYearc = Integer.parseInt(nowyear)-i+"";
			  list.add(sYearc);
		  }
		return list;
	}

	public static Date getYearMoonDate(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
	    String startDate=DateUtils.getDate("yyyy-MM");
	    java.util.Date date=null;
	    try {
			date=sdf.parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static List<String> getMonthBetween(String minDate, String maxDate)  {
		ArrayList<String> result = new ArrayList<String>();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
	    Calendar min = Calendar.getInstance();
	    Calendar max = Calendar.getInstance();
	    try {
	    	min.setTime(sdf.parse(minDate));
		    min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		    max.setTime(sdf.parse(maxDate));
		    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		    Calendar curr = min;
		    while (curr.before(max)) {
		     result.add(sdf.format(curr.getTime()));
		     curr.add(Calendar.MONTH, 1);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return result;
	  }

	public static boolean isToday(String myString) {
		boolean flag=false;
		try {
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
			  Date d= sdf.parse(myString);
			  if (d.equals(getNowDate())) {
				return false;
			  }
			  flag = d.before(getNowDate());
			  if(flag){
				  flag=true;
			  }
		 } catch (ParseException e) {
			e.printStackTrace();
		 }
		return flag;
      }

	/**
	 * 计算2个时间的相差小时数
	 *
	 * @param comeTimeStr
	 * @param departTimeStr
	 * @return
	 */
	public static long getHourBetween(String comeTimeStr, String departTimeStr) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date comeTime = myFormatter.parse(comeTimeStr);
			Date departTime = myFormatter.parse(departTimeStr);
			long hour = (departTime.getTime() - comeTime.getTime()) / (60 * 60 * 1000);
			return hour;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

     /**
      * 获取两时间相差多少分钟
      * @author songlc
      * @param minDate
      * @param maxDate
      * @return
      */
    public static long getMinutesBetween(String minDate, String maxDate) {
		try {
		   SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   Date begin=dfs.parse(minDate);
		   Date end = dfs.parse(maxDate);
		   long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
		   long minutes=between/60;
		   return minutes;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return 0;
    }

    public static void main(String[] args) {
    	Date date = getCurrentWeekSunday();
    	System.out.println(formatDate(date));

    	Calendar c  = Calendar.getInstance();
    	System.out.println(c.get(Calendar.DAY_OF_WEEK));
	}
}
