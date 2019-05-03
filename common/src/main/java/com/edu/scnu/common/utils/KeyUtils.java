package com.edu.scnu.common.utils;

import java.util.UUID;

/**
 * 生成键值工具类
 * @author Autom
 *
 */
public class KeyUtils {
	
	public static final int DEFAULT_LENGTH = 16;
	
	public static String randomString() {
		return randomString(DEFAULT_LENGTH);
	}
	
	public static String randomString(int len) {
		String result = UUID.randomUUID().toString().replace("-", "");
		
		return result.substring(0, len);
	}
	
	public static String randomString(int len, String prefix) {
		String result = UUID.randomUUID().toString().replace("-", "");
		
		return prefix + result.substring(0, len);
	}
	
	public static String stringWithTime(int len, String prefix) {
		String result = randomString(len, prefix);
		return result + "_" + System.currentTimeMillis();
	}
	
	public static String randomNum() {
		 Integer randomInt = (int) (Math.random() * 100000 + 100000);
		 return System.currentTimeMillis() + String.valueOf(randomInt);
	}
	
	public static String randomNum(String prefix) {
		 Integer randomInt = (int) (Math.random() * 100000 + 100000);
		 return prefix + System.currentTimeMillis() + String.valueOf(randomInt);
	}
}
