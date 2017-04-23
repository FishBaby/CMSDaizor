package com.cecb2b.cms.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * 
 */
public final class DateUtils {

	public static String PATTERN_YYYY_MM_DD = "yyyy-MM-dd hh:mm:ss";
	public static String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss";
	public static String PATTERN_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

	private static final DateFormat YYYY_MM_DD_DATE_FORMAT = newDateFormat(PATTERN_YYYY_MM_DD);
	private static final DateFormat YYYY_MM_DD_HH_MM_SS_DATE_FORMAT = newDateFormat(PATTERN_YYYY_MM_DD_HH_MM_SS);
	private static final DateFormat PATTERN_YYYY_MM_DD_HH_MM_SS_SSS_DATE_FORMAT = newDateFormat(PATTERN_YYYY_MM_DD_HH_MM_SS_SSS);

	public static Date parse(String str, String pattern) {
		try {
			return newDateFormat(pattern).parse(str);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private static DateFormat strictDateFormatForPattern(String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(false);
		return dateFormat;
	}

	public static DateFormat newDateFormat(String pattern) {
		return strictDateFormatForPattern(pattern);
	}

	public static String getCurrentTime(String pattern) {
		Date date = new Date(System.currentTimeMillis());
		return newDateFormat(pattern).format(date);
	}
}
