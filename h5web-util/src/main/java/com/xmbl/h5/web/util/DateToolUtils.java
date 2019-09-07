package com.xmbl.h5.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类 默认使用 "yyyy-MM-dd HH:mm:ss" 格式化日期
 * 
 */
public final class DateToolUtils {
    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT_YMD = "yyyy-MM-dd";
    /**
     * 英文简写（默认）如：2010-12
     */
    public static String FORMAT_SHORT_YM = "yyyy-MM";
    /**
     * 英文简写（默认）如：2010-12
     */
    public static String FORMAT_SHORT_Y = "yyyy";
    /**
     * 英文简写（默认）如：23:15:06
     */
    public static String FORMAT_SHORT_HMS = "HH:mm:ss";
    /**
     * 英文全称 如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    /**
     * 英文全称 如：20101201231506123
     */
    public static String FORMAT_LONG_SSS = "yyyyMMddHHmmssSSS";
    /**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    /**
     * 中文简写 如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN_YMD = "yyyy年MM月dd日";
    /**
     * 中文简写 如：23时15分06秒
     */
    public static String FORMAT_SHORT_CN_HMS = "HH时mm分ss秒";
    /**
     * 中文全称 如：2010年12月01日 23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return FORMAT_LONG;
    }

    /**
     * 根据预设格式返回当前日期
     * 
     * @return
     */
    public static String getNow() {
        return format(new Date());
    }

    /**
     * 根据用户格式返回当前日期
     * 
     * @param format
     * @return
     */
    public static String getNow(String format) {
        return format(new Date(), format);
    }

    /**
     * 使用预设格式格式化日期
     */
    public static String format(Date date) {
        return format(date, getDatePattern());
    }

    /**
     * 使用用户格式格式化日期
     * 
     * @param date
     *            日期
     * @param pattern
     *            日期格式
     * @return
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return returnValue;
    }

    /**
     * 使用预设格式提取字符串日期
     * 
     * @param strDate
     *            日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());
    }

    /**
     * 使用用户格式提取字符串日期
     * 
     * @param strDate
     *            日期字符串
     * @param pattern
     *            日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获取给定时间的时间戳
     */
    public static String getTimeString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DateToolUtils.FORMAT_LONG_SSS);
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return df.format(calendar.getTime());
    }

    /**
     * 获取当前时间的时间戳
     */
    public static String getNowTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(DateToolUtils.FORMAT_LONG_SSS);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }
    /**
     * 获取给定时间的毫秒数
     */
    public static Long getTimeCount(Date date) {
        return date.getTime();
    }
    /**
     * 获取当前时间的毫秒数
     */
    public static Long getNowTimeCount() {
        return System.currentTimeMillis();
    }

    /**
     * 获取date 1 - date 2 的时间差
     * @param date1
     * @param date2
     * @return
     */
    public static Long getDate1AndDate2Interval(Date date1,Date date2) {
    	Long currTimeMillis = date1.getTime() - date2.getTime();
    	return currTimeMillis;
    }
    /**
     * 在日期上增加天数
     * 
     * @param date
     *            日期
     * @param n
     *            要增加的天数
     * @return
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }
    
    /**
     * 获取某一年某一月某一日某一天某一小时某一分某一秒某一毫秒的时间
     */
    public static Date getDate(int year,int month,int date ,int hours,int minute,int second,int millisecond) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.YEAR, year);
    	calendar.set(Calendar.MONTH, month);
    	calendar.set(Calendar.DAY_OF_MONTH, date);
    	calendar.set(Calendar.HOUR_OF_DAY, hours);
    	calendar.set(Calendar.MINUTE, minute);
    	calendar.set(Calendar.SECOND, second);
    	calendar.set(Calendar.MILLISECOND, millisecond);
    	return calendar.getTime();
    }
    
    /**
     * 获取第二天的某一小时某一分钟某一秒的时间
     * @param currentDate
     * @param hours
     * @param minute
     * @param second
     * @param millisecond
     * @return
     */
    public static Date getTwoedDate(Date currentDate, int hours, int minute, int second,int millisecond) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
    	calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
    	calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
    	calendar.set(Calendar.HOUR_OF_DAY, hours);
    	calendar.set(Calendar.MINUTE, minute);
    	calendar.set(Calendar.SECOND, second);
    	calendar.set(Calendar.MILLISECOND, millisecond);
    	return calendar.getTime();
    }
    
    /**
     * 两个时间之间相差多少天
     * 
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) {    
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DateToolUtils.FORMAT_SHORT_YMD);
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
	        return Integer.parseInt(String.valueOf(between_days));           
		} catch (ParseException e) {
			return 0;
		}
    }
}
