package com.edu.scnu.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private DateUtils() {}
	
	private static final ThreadLocal<DateFormat> local = new ThreadLocal<DateFormat>() {
		
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
		
	};
	
	
	public static String toString(Date date) {
		DateFormat formatter = local.get();
		return formatter.format(date);
	}
	
	public static String toString(Date date, String pattern) {
		DateFormat formatter = new SimpleDateFormat(pattern);
		local.set(formatter);
		return formatter.format(date);
	}
}
