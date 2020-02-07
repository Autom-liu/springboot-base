package com.edu.scnu.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private DateUtils() {}
	
	private static final Map<DatePattern, ThreadLocal<DateFormat>> STANDAR_DATEFORMATTER_MAP = new HashMap<>();
	
	static {
		DatePattern[] patterns = DatePattern.values();
		for(DatePattern pattern : patterns) {
			STANDAR_DATEFORMATTER_MAP.put(pattern, pattern.getFormatter());
		}
	}
	
	public static String toString(Date date, DatePattern pattern) {
		ThreadLocal<DateFormat> formaterLocal = STANDAR_DATEFORMATTER_MAP.get(pattern);
		DateFormat formatter = formaterLocal.get();
		return formatter.format(date);
	}
	
	public enum DatePattern {
		/** yyyy-mm-dd **/
		STANDAR_DATE_FORMATER,
		/** yyyymmdd **/
		SIMPLE_DATE_FORMATER,
		/** yyyy-mm-dd hh:mi:ss **/
		STANDAR_DATETIME_FORAMTER,
		/**yyyymmddhhmiss **/
		SIMPLE_DATETIME_FORMATER,
		/**yyyymmddhhmissSSS**/
		SIMPLE_DATESEC_FORMATER,
		/** hh:mi:ss **/
		STANDAR_TIME_FORMATER,
		/** hhmiss **/
		SIMPLE_TIME_FORMATER,;
		
		private ThreadLocal<DateFormat> getFormatter() {
			switch(this) {
			case STANDAR_DATE_FORMATER: return ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
			case SIMPLE_DATE_FORMATER: return ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd"));
			case STANDAR_DATETIME_FORAMTER: return ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
			case SIMPLE_DATETIME_FORMATER: return ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddhhmmss"));
			case SIMPLE_DATESEC_FORMATER: return ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddhhmmssSSS"));
			case STANDAR_TIME_FORMATER: return ThreadLocal.withInitial(() -> new SimpleDateFormat("hh:mm:ss"));
			case SIMPLE_TIME_FORMATER: return ThreadLocal.withInitial(() -> new SimpleDateFormat("hhmmss"));
			default: return null;
			}
		}
	}

}
