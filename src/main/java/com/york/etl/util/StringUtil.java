package com.york.etl.util;


/**
 * 
 * @author zhang
 * @since JDK1.8
 * @version 1.0
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * @param strings 字符串变参
	 * @return 如果字符串中有为null或者""的返回true
	 */
	public static boolean isEmpty(String... strings) {
		for (String string : strings) {
			if (string == null || "".equals(string)) {
				return true;
			}
		}
		return false;
	}
}
