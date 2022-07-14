package com.cesgroup.prison.code.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 
 * <p>描述:</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * @author hp(张传乐 E-mail:zhang.chuanle@cesgroup.com.cn)
 * @date 2015-1-6  下午7:42:25
 * @version 1.0.2015.
 */
public class DateUtils {

	/**
	 * 日期和字符串之间的转换格式：年 yyyy
	 */
	public static final String PATTERN_YEAR = "yyyy";
	/**
	 * 日期和字符串之间的转换格式：年月 yyyy-MM
	 */
	public static final String PATTERN_YEARMONTH = "yyyy-MM";
	/**
	 * 日期和字符串之间的转换格式：年月日 yyyy-MM-dd
	 */
	public static final String PATTERN_DATE = "yyyy-MM-dd";

	/**格式 yyyy年MM月dd日*/
	public static final String PATTERN_CHINA_DATE = "yyyy年MM月dd日";
	/**
	 * 日期和字符串之间的转换格式：年月日时分 yyyy-MM-dd HH:mm
	 */
	public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm";
	/**
	 * 日期和字符串之间的转换格式：年月日时分秒 yyyy-MM-dd HH:mm:ss
	 */
	public static final String PATTERN_DATETIMESS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 日期和字符串之间的转换格式：年月日时分秒 yyyyMMddHHmmss
	 */
	public static final String PATTERN_DATETIMESSS = "yyyyMMddHHmmss";
	
	
	
	
	/**
	 * 日期和字符串之间的转换格式：时分 HH:mm
	 */
	public static final String PATTERN_HHMM = "HH:mm";	
	
	/**
	 * 格式化日期为字符串，格式为年
	 *
	 * @param date 日期
	 * @return String
	 */
	public static String formatYear(Date date) {
		String result = "";
		if (date != null) {
			result = DateFormatUtils.format(date, PATTERN_YEAR);
		}
		return result;
	}

	/**
	 * 格式化日期为字符串，格式为年月
	 *
	 * @param date
	 * @return
	 */
	public static String formatYearMonth(Date date) {
		String result = "";
		if (date != null) {
			result = DateFormatUtils
					.format(date, PATTERN_YEARMONTH);
		}
		return result;
	}

	/**
	 * 格式化日期为字符串，格式为年月日
	 *
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		String result = "";
		if (date != null) {
			result = DateFormatUtils.format(date, PATTERN_DATE);
		}
		return result;
	}
	
	/**
	 * 格式化日期为字符串，格式为年月日
	 *
	 * @param date
	 * @return
	 */
	public static String formatChinaDate(Date date) {
		String result = "";
		if (date != null) {
			result = DateFormatUtils.format(date, PATTERN_CHINA_DATE);
		}
		return result;
	}
	
	/**
	 * 格式化yyyy-MM-dd 为yyyy年MM月dd日
	 *
	 * @param String
	 * @return
	 */
	public static String formatChinaDate(String date) {
		String result = "";
		if(date !=null && date.length()>=10){
			String year = date.substring(0, 4)+"年";
			String month = String.valueOf(Integer.parseInt(date.substring(5, 7)))+"月";
			String day = String.valueOf(Integer.parseInt(date.substring(8,10)))+"日";
			result = year+month+day;
		}else{
			result=date;
		}
		return result;
	}
	
	
	/**
	 * 格式化日期为字符串，格式为年月日时分
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date) {
		String result = "";
		if (date != null) {
			result = DateFormatUtils.format(date, PATTERN_DATETIME);
		}
		return result;
	}

	/**
	 * 格式化日期为字符串，格式为年月日时分秒
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateTimess(Date date) {
		String result = "";
		if (date != null) {
			result = DateFormatUtils.format(date, PATTERN_DATETIMESS);
		}
		return result;
	}
	
	/**
	 * 格式化日期为字符串，格式为年月日时分秒
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateTimesss(Date date) {
		String result = "";
		if (date != null) {
			result = DateFormatUtils.format(date, PATTERN_DATETIMESSS);
		}
		return result;
	}
	
	

	/**
	 * 格式化日期为字符串，格式为时分
	 *
	 * @param date
	 * @return
	 */
	public static String formatTime(Date date){
		String result = "";
		if (date != null) {
			result = DateFormatUtils.format(date, PATTERN_HHMM);
		}
		return result;
	}

	/**
	 * 解析字符串为日期，格式为年
	 *
	 * @param str
	 * @return
	 */
	public static Date parseYear(String str) {
		Date result = null;
		if (str != null && !"".equals(str)) {
			try {
				result = org.apache.commons.lang.time.DateUtils.parseDate(str,
						new String[] { PATTERN_YEAR });
			} catch (ParseException e) {
				// e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 解析字符串为日期，格式为年月
	 *
	 * @param str
	 * @return
	 */
	public static Date parseYearMonth(String str) {
		Date result = null;
		if (str != null && !"".equals(str)) {
			try {
				result = org.apache.commons.lang.time.DateUtils.parseDate(str,
						new String[] { PATTERN_YEARMONTH });
			} catch (ParseException e) {
				// e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 解析字符串为日期，格式为年月日
	 *
	 * @param str
	 * @return
	 */
	public static Date parseDate(String str) {
		Date result = null;
		if (str != null && !"".equals(str)) {
			try {
				result = org.apache.commons.lang.time.DateUtils.parseDate(str,
						new String[] { PATTERN_DATE });
			} catch (ParseException e) {
				// e.printStackTrace();
			}
		}
		return result;
	}

	
	public static java.sql.Date parseDateYmd(String str) {
		java.sql.Date result = null;
		if (str != null && !"".equals(str)) {
			try {
				result = java.sql.Date.valueOf(str);
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		return result;
	}


	/**
	 * 解析字符串为日期，格式为年月日时分
	 *
	 * @param str
	 * @return
	 */
	public static Date parseDateTime(String str) {
		Date result = null;
		if (str != null && !"".equals(str)) {
			try {
				result = org.apache.commons.lang.time.DateUtils.parseDate(str,
						new String[] { PATTERN_DATETIME });
			} catch (ParseException e) {
				// e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 解析字符串为日期，格式为年月日时分秒
	 *
	 * @param str
	 * @return
	 */
	public static Date parseDateTimess(String str) {
		Date result = null;
		if (str != null && !"".equals(str)) {
			try {
				result = org.apache.commons.lang.time.DateUtils.parseDate(str,
						new String[] { PATTERN_DATETIMESS });
			} catch (ParseException e) {
				// e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 解析字符串为日期，格式为年月日时分
	 *
	 * @param str
	 * @return
	 */
	public static Date parseTime(String str) {
		Date result = null;
		if (str != null && !"".equals(str)) {
			try {
				result = org.apache.commons.lang.time.DateUtils.parseDate(str,
						new String[] { PATTERN_HHMM });
			} catch (ParseException e) {
				// e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 取得当前日期，格式为2008-01-01 01:01
	 * @return
	 */
	public static String getCurrentDate() {
		return getCurrentDate(true);
	}

	/**
	 * 取得当前日期
	 * @param hasTime 为true 格式为2008-01-01 01:01,hasTime为false 格式为2008-01-01
	 * @return
	 */
	public static String getCurrentDate(boolean hasTime) {
		return hasTime ? formatDateTime(new Date()) : formatDate(new Date());
	}
	

	/**
	 * 比较日期大小
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return cal1.compareTo(cal2);
	}

	/**
	 * 比较日期大小
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDate(String date1, String date2) {
		return compareDate(parseDateTime(date1),parseDateTime(date2));
	}

	/**
	 * 取得当月最小日期
	 *
	 * @param date
	 * @return
	 */
	public static String getMinOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		int min = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DATE, min);
		return formatDate(cal.getTime());
	}

	/**
	 * 取得当月最大日期
	 *
	 * @param date
	 * @return
	 */
	public static String getMaxOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		int max = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DATE, max);
		return formatDate(cal.getTime());
	}
	
	/**
	 * 取得日期前（后）几个月的日期
	 * @author hp(王梦宇 E-mail:wang.mengyu@cesgroup.com.cn)
	 * @date 2015-11-30  下午2:18:08
	 */
	public static String getDateByMonth(Date date,int month){
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		int max = cal.getActualMaximum(Calendar.DAY_OF_MONTH);//获取date当月的最大天数
		if(max==cal.get(Calendar.DAY_OF_MONTH)){//表示date是月底时间
			cal.add(Calendar.MONTH, month);
			cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		}else{
			cal.add(Calendar.MONTH, month);
		}
		return formatDate(cal.getTime());
	}
	
	/**
	 * 取得日期前（后）几个月的日期.
	 * @param date
	 * @param month
	 * @return 返回格式 yyyy-MM
	 * @author Administrator(ruandi E-mail:ruan.di@cesgroup.com.cn)
	 * @date 2015-11-12  下午4:15:37
	 */
	public static String getNyByMonth(Date date,int month){
		String ny = "";
		if(date != null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, month);
			ny = formatYearMonth(cal.getTime());
		}
		return ny;
	}
	
	
	/**
	 * 获得指定日期指定几天后的日期.
	 * @param date yyyy-Mm-dd
	 * @param day 指定的天数
	 * @return
	 * @author hp(ruandi E-mail:ruan.di@cesgroup.com.cn)
	 * @date 2015-6-9  下午3:46:38
	 */
	public static String getNextDay(String date,int day){
		Calendar cal = Calendar.getInstance();
		if(StringUtils.isNotEmpty(date)){
			String[] dateArr = date.split("-");
			cal.set(Integer.valueOf(dateArr[0]), Integer.valueOf(dateArr[1])-1, Integer.valueOf(dateArr[2]));
			cal.add(Calendar.DAY_OF_MONTH,day);
		}
		
		return formatDate(cal.getTime());
	}
	
	/**
	 * 根据生日换算今年的年龄
	 *
	 * @param birthday
	 * @return
	 */
	public static int getAgeByBirthday(Date birthday) {
		return getAgeByBirthday(birthday, new Date());
	}
	
	public static String getCurrentYear(){
		return getCurrentDate().substring(0, 4)+"-01-01";
	}

	/**
	 * 根据生日换算指定年的年龄
	 *
	 * @param birthday
	 * @param currentDate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getAgeByBirthday(Date birthday, Date currentDate) {
		int age = 0;
		if (birthday != null) {
			age = Integer.parseInt(formatYear(currentDate))
					- Integer.parseInt(formatYear(birthday));
			int bMonth = birthday.getMonth();
			int cMonth = currentDate.getMonth();
			int bDate = birthday.getDate();
			int cDate = currentDate.getDate();
			if((bMonth>cMonth)||(bMonth==cMonth&&bDate>cDate)){
				age = age -1;
			}
		}
		return age>=0?age:0;
	}
	/**
	 * 
	 * @param s a <code>String</code> object representing a date in 
     *        in the format "yyyy-mm-dd"
     * @return a <code>java.sql.Date</code> object representing the
     *         given date
	 * @author hp(张传乐 E-mail:zhang.chuanle@cesgroup.com.cn)
	 * @date 2015-1-6  下午8:22:36
	 */
	public static java.sql.Date valueOf(String s) {
		if(StringUtils.isEmpty(s)){
			return null;
		}else{
			try{
				return java.sql.Date.valueOf(s.substring(0, 10));
			}catch (Exception e) {
					return null;
			}
		}
	}
	
	public static Date formater(String s){
		
		if(StringUtils.isEmpty(s)){
			return null;
		}else{
			return parseDate(s.substring(0, 10));
		}
	}
	
	/**
	 * .判断字符串是否符合特定的日期字符串格式
	 * @author hp(王梦宇 E-mail:wang.mengyu@cesgroup.com.cn)
	 * @date 2015-1-8  下午7:58:49
	 */
	public boolean isValidDate(String dateStr,String format){
		SimpleDateFormat dateFormat=null;
		dateFormat=new SimpleDateFormat(format);
		if(dateStr==null||format==null||
				dateStr.isEmpty()||format.isEmpty()){
			return false;
		}else{
			try {
				dateFormat.parse(dateStr);
				return true;
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	/**
	 * .获取日期所在季度
	 * date为null取当前时间
	 * @param object
	 * @author hp(张小红 E-mail:zhang.xiaohong@cesgroup.com.cn)
	 * @date 2015-4-9  下午1:46:54
	 */
	public static String getJdByDate(Date date) {
		
		Calendar c = Calendar.getInstance();
		String jd = "";
		if(date != null) {
			c.setTime(date);
		} 
		
		int month = c.get(Calendar.MONTH);
		if(month >= 0 && month <= 2) {
			jd = "1";
		} else if(month >= 3 && month <= 5) {
			jd = "2";
		} else if(month >= 6 && month <= 8) {
			jd = "3";
		} else {
			jd = "4";
		}
		
		return jd;
	}
	/**
	 *获取当前季度的的开始时间
	 */
	public static String getStartTimeByCurrentSeason(){
		Calendar can = Calendar.getInstance();
		int currentMon = can.get(Calendar.MONTH)+1;
		String now=null;
		if(currentMon>=1 && currentMon<=3)
			can.set(Calendar.MONTH, 0);
		else if(currentMon>=4 && currentMon<=6)
			can.set(Calendar.MONTH, 3);
		else if(currentMon>=7 && currentMon<=9)
			can.set(Calendar.MONTH, 4);
		else if(currentMon>=10 && currentMon<=12)
			can.set(Calendar.MONTH, 9);
		can.set(Calendar.DATE, 1);
		now = DateFormatUtils.format(can.getTime(), PATTERN_DATE);
		
		return now;
	}
	
	/**
	 *获取当前季度的的结束时间
	 */
	public static String getEndTimeByCurrentSeason(){
		Calendar can = Calendar.getInstance();
		int currentMon = can.get(Calendar.MONTH)+1;
		String now=null;
		if(currentMon>=1 && currentMon<=3){
			can.set(Calendar.MONTH, 2);
			can.set(Calendar.DATE, 31);
		}else if(currentMon>=4 && currentMon<=6){
			can.set(Calendar.MONTH, 5);
			can.set(Calendar.DATE, 30);
		}else if(currentMon>=7 && currentMon<=9){
			can.set(Calendar.MONTH, 8);
			can.set(Calendar.DATE, 30);
		}else if(currentMon>=10 && currentMon<=12){
			can.set(Calendar.MONTH, 11);
			can.set(Calendar.DATE, 31);
		}
		now = DateFormatUtils.format(can.getTime(), PATTERN_DATE);
		
		return now;
	}
	
	/**
	 * 判断是否润平年
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获得日期的间隔.
	 * @param start yyyy-MM
	 * @param end yyyy-MM
	 * @return
	 * @date 2015-5-17  上午11:34:46
	 */
	public static int intevalBetweenDate(String start,String end){
		int inteval = 0;
		if(StringUtils.isNotEmpty(start)&&start.length()>=7&&
				StringUtils.isNotEmpty(end)&&end.length()>=7){
			Calendar startC = Calendar.getInstance();
			Calendar endC = Calendar.getInstance();
			startC.setTime(DateUtils.parseYearMonth(StringUtils.substring(start,0, 7)));
			endC.setTime(DateUtils.parseYearMonth(StringUtils.substring(end,0, 7)));
			inteval =  (endC.get(Calendar.YEAR)-startC.get(Calendar.YEAR))*12+endC.get(Calendar.MONTH)-startC.get(Calendar.MONTH)+1;
		} 
		return inteval;
	}
	
}
