package com.lvhq.platform.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import com.google.common.collect.Lists;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author lvhq
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	private static final char SEPARATOR = '_';
	private static final String CHARSET_NAME = "UTF-8";

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] getBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
	}

	/**
	 * 是否包含字符串
	 * 
	 * @param str
	 *            验证字符串
	 * @param strs
	 *            字符串组
	 * @return 包含返回true
	 */
	public static boolean inString(String str, String... strs) {
		if (str != null) {
			for (String s : strs) {
				if (str.equals(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * 
	 * @param html
	 * @return
	 */
	public static String replaceMobileHtml(String html) {
		if (html == null) {
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}

	/**
	 * 替换相对应的字符
	 * @param text
	 * @param searchList
	 * @param replacementList
	 * @return
	 */
	public static String replaceEach(String text, String[] searchList,
			String[] replacementList) {
		String result = "";
		if (isNotBlank(text)) {
			if (searchList != null && searchList.length > 0
					&& replacementList != null && replacementList.length > 0){
				result = org.apache.commons.lang3.StringUtils.replaceEach(text, searchList, replacementList);
			} else {
				result = text;
			}
		}
		return result;
	}
	
	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * 
	 * @param txt
	 * @return
	 */
	public static String toHtml(String txt) {
		if (txt == null) {
			return "";
		}
		return replace(replace(Encodes.escapeHtml(txt), "\n", "<br/>"), "\t", "&nbsp; &nbsp; ");
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String abbr2(String param, int length) {
		if (param == null) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		int n = 0;
		char temp;
		boolean isCode = false; // 是不是HTML代码
		boolean isHTML = false; // 是不是HTML特殊字符,如&nbsp;
		for (int i = 0; i < param.length(); i++) {
			temp = param.charAt(i);
			if (temp == '<') {
				isCode = true;
			} else if (temp == '&') {
				isHTML = true;
			} else if (temp == '>' && isCode) {
				n = n - 1;
				isCode = false;
			} else if (temp == ';' && isHTML) {
				isHTML = false;
			}
			try {
				if (!isCode && !isHTML) {
					n += String.valueOf(temp).getBytes("GBK").length;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (n <= length - 3) {
				result.append(temp);
			} else {
				result.append("...");
				break;
			}
		}
		// 取出截取字符串中的HTML标记
		String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)", "$1$2");
		// 去掉不需要结素标记的HTML标记
		temp_result = temp_result
				.replaceAll("</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
						"");
		// 去掉成对的HTML标记
		temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
		// 用正则表达式取出标记
		Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
		Matcher m = p.matcher(temp_result);
		List<String> endHTML = Lists.newArrayList();
		while (m.find()) {
			endHTML.add(m.group(1));
		}
		// 补全不成对的HTML标记
		for (int i = endHTML.size() - 1; i >= 0; i--) {
			result.append("</");
			result.append(endHTML.get(i));
			result.append(">");
		}
		return result.toString();
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val) {
		return toLong(val).intValue();
	}

	/**
	 * 获得i18n字符串
	 */
	public static String getMessage(String code, Object[] args) {
		LocaleResolver localLocaleResolver = (LocaleResolver) SpringContextHolder.getBean(LocaleResolver.class);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Locale localLocale = localLocaleResolver.resolveLocale(request);
		return SpringContextHolder.getApplicationContext().getMessage(code, args, localLocale);
	}

	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/**
	 * 驼峰命名法工具
	 * 
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 驼峰命名法工具
	 * 
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 驼峰命名法工具
	 * 
	 * @return toCamelCase("hello_world") == "helloWorld"
	 *         toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 *         toUnderScoreCase("helloWorld") = "hello_world"
	 */
	public static String toUnderScoreCase(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i > 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 如果不为空，则设置值
	 * 
	 * @param target
	 * @param source
	 */
	public static void setValueIfNotBlank(String target, String source) {
		if (isNotBlank(source)) {
			target = source;
		}
	}

	/**
	 * 转换为JS获取对象值，生成三目运算返回结果
	 * 
	 * @param objectString
	 *            对象串 例如：row.user.id
	 *            返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
	 */
	public static String jsGetVal(String objectString) {
		StringBuilder result = new StringBuilder();
		StringBuilder val = new StringBuilder();
		String[] vals = split(objectString, ".");
		for (int i = 0; i < vals.length; i++) {
			val.append("." + vals[i]);
			result.append("!" + (val.substring(1)) + "?'':");
		}
		result.append(val.substring(1));
		return result.toString();
	}

	/**
	 * 根据对象的某个属性，返回以指定分隔符分割的字符串
	 * 
	 * @param sourcelist
	 * @param property
	 * @param separator
	 * @return
	 */
	public static <T> String getPropertyValues(List<T> list, String property, String separator) {
		String values = "";
		List<String> valueList = Lists.newArrayList();
		for (int i = 0; i < list.size(); i++) {
			T e = list.get(i);
			if (StringUtils.isNotBlank(ReflectionUtils.invokeGetter(e, property))) {
				String value = (String) ReflectionUtils.invokeGetter(e, property);
				valueList.add(value);
			}
		}
		if (valueList != null && valueList.size() > 0) {
			values = StringUtils.join(valueList, separator);
		}
		return values;
	}

	/**
	 * 比较字符串是否相等
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean compareStr(String str1, String str2) {
		if (isEmpty(str2) && isEmpty(str1))
			return true;
		if (isEmpty(str2) || isEmpty(str1))
			return false;
		if (str1.equals(str2))
			return true;
		return false;
	}

	/**
	 * 将以特定符号分割的字符串分割返回字符列表
	 * 
	 * @param str
	 * @param split
	 * @return
	 */
	public static List<String> strToList(String str, String split) {
		if (StringUtils.isNotBlank(str) && StringUtils.isNotBlank(split)) {
			List<String> list = Lists.newArrayList();
			String[] strArray = str.split(split);
			for (int i = 0; i < strArray.length; i++) {
				if (StringUtils.isNotBlank(strArray[i]))
					list.add(strArray[i]);
			}
			if (list.size() > 0) {
				return list;
			}
		}
		return null;
	}

	/**
	 * 将字符串数组转为字符串
	 * @param array
	 * @return
	 */
	public static String arrayToString(String[] array) {
		if(array!=null && array.length > 0){
			StringBuffer buffer = new StringBuffer();
			for(int i = 0; i < array.length; i++){
				buffer.append(array[i]+",");
			}
			if(buffer.toString().length() > 0){
				return buffer.toString().substring(0,buffer.toString().length()-1);
			}
		}
		return "";
	}
	
	/**
	 * 检测是否是正整数
	 * @param str
	 * @return
	 */
	public static boolean isPlusNum(String str){
		boolean b = true;
		if(isEmpty(str))
			b = false;
		else
			b = str.matches("^[+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
		return b;
	}
	
	//判断数组中是否有重复值
	public static boolean checkRepeat(String[] array){
	  Set<String> set = new HashSet<String>();
	  for(String str : array){
	    set.add(str);
	  }
	  if(set.size() != array.length){
	    return false;//有重复
	  }else{
	    return true;//不重复
	  }
	}
	
	/**
	 * 转移html标签
	 * @param str
	 * @return
	 */
	public static String escapeHtml3(String str){
		if(isNotBlank(str)){
			return StringEscapeUtils.escapeHtml3(str);
		}
		return str;
	}
	
	/**
	 * 反转移html标签
	 * @param str
	 * @return
	 */
	public static String unescapeHtml3(String str){
		if(isNotBlank(str)){
			return StringEscapeUtils.unescapeHtml3(str);
		}
		return str;
	}
	
	/**
	 * 反转义中文标点符号
	 * @param str
	 * @return
	 */
	public static String unzhPunctuation(String str){
		if(isNotBlank(str)){
			str = str.replaceAll("&ldquo;", "“").replaceAll("&rdquo;", "”");
		}
		return str;
	}
	
	/**
	 * 格式化Long型数据，不足位数的前面补0
	 * @param l
	 * @param strLength
	 * @return
	 */
	public static String formatLong(Long l, int strLength){
		String str = String.format("%"+strLength+"d", l).replace(" ", "0");
		return str;
	}
	
	/**
	 * 格式化Integer型数据，不足位数的前面补0
	 * 
	 * @param n
	 * @param strLength
	 * @return
	 */
	public static String formatInt(Integer n, int strLength){
		String str = String.format("%"+strLength+"d", n).replace(" ", "0");
		return str;
	}
	
	/**
	 * 格式化String 型数据，不足位数的前面补0
	 * 
	 * @param str
	 * @param strLength
	 * @return
	 */
	public static String formatStr(String str, int strLength){
		str = String.format("%"+strLength+"s", str).replace(" ", "0");
		return str;
	}
	
	public static String delHTMLTag(String htmlStr) {
		try{
			String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
			String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			
			Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			Matcher m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			
				Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			Matcher m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			
			Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			Matcher m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
		}catch(Exception e){
    		e.printStackTrace();
    	}
        return htmlStr.trim(); // 返回文本字符串
    }

    public static String delBase64(String tempContent) {
    	int index = 1;
    	try{
    		do {
	            int index1 = tempContent.indexOf("&lt;img");
	            String content1 = "";
	            if (index1 > 0) {
	                content1 = tempContent.substring(0, index1);
	                tempContent = tempContent.substring(index1 + 1);
	            }
	            String content2 = "";
	            int index2 = tempContent.indexOf("&lt;");
	            if (index2 > 0) {
	                content2 = tempContent.substring(index2 + 1, tempContent.length());
	            }
	            if (index1 > 0 && index2 > 0) {
	                tempContent = content1 + content2;
	            }
	            
	            index ++;
	            if(index == 1000){
	            	break;
	            }
	        } while (tempContent.indexOf("&lt;img") > 0);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return tempContent;
    }
	
	public static void main(String[] args) {
		String str = formatStr("0",3);
		System.out.println(str);
	}
}
