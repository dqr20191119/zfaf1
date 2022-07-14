package com.cesgroup.framework.utils;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import com.cesgroup.prison.utils.CommonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;

public class Util implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Gson工具
	 */
	private static final Gson gson = new GsonBuilder().create();
	
	private static long currentId = 0;

	final public static SimpleDateFormat DF_YEAR = new SimpleDateFormat("yyyy");
	final public static SimpleDateFormat DF_DATE = new SimpleDateFormat("yyyy-MM-dd");
	final public static SimpleDateFormat DF_DTIME = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	final public static SimpleDateFormat DF_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	final public static SimpleDateFormat DF_TIME_MESC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	final public static SimpleDateFormat DF_DATE_STR = new SimpleDateFormat("yyyyMMdd");
	final public static SimpleDateFormat DF_DTIME_STR = new SimpleDateFormat("yyyyMMddHHmm");
	final public static SimpleDateFormat DF_TIME_STR = new SimpleDateFormat("yyyyMMddHHmmss");
	final public static SimpleDateFormat DF_TIME_MESC_STR = new SimpleDateFormat("yyyyMMddHHmmssS");
	final public static SimpleDateFormat DF_DOT_DATE = new SimpleDateFormat("yyyy.MM.dd");

	/**
	 * 描述：判断是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		if ((obj != null) && !(obj instanceof JsonNull) && (!"".equals(obj)) && (!"null".equals(obj)))
			return false;
		else
			return true;
	}

	/**
	 * 判断是否非空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean notNull(Object obj) {
		if ((obj != null) && !(obj instanceof JsonNull) && (!"".equals(obj.toString())) && (!"null".equals(obj.toString())))
			return true;
		else
			return false;
	}

	/**
	 * 将字符串转为日期型返回，精确到秒，转换异常返回null
	 * 
	 * @param s
	 *            param sf
	 * @return
	 */
	public static Date toDateTime(String s) {
		try {
			if (s == null)
				return null;
			return DF_TIME.parse(s);
		} catch (Throwable e) {
			return null;
		}
	}

	/**
	 * 将字符串转为日期型返回，精确到毫秒，转换异常返回null
	 * 
	 * @param s
	 *            param sf
	 * @return
	 */
	public static Date toDateMsec(String s) {
		try {
			if (s == null)
				return null;
			return DF_TIME_MESC.parse(s);
		} catch (Throwable e) {
			return null;
		}
	}

	/**
	 * 将字符串转为日期型返回，转换异常返回null
	 * 
	 * @param s
	 *            param sf
	 * @return
	 */
	public static Date toDate(String s, SimpleDateFormat sf) {
		try {
			if (s == null)
				return null;
			return sf.parse(s);
		} catch (Throwable e) {
			return null;
		}
	}

	/**
	 * 将Date类型的对象转化为String
	 * 
	 * @param date
	 * @param sf
	 * @return
	 */
	public static String toStr(Date date, SimpleDateFormat sf) {
		if (isNull(date))
			return null;
		return sf.format(date);
	}

	/**
	 * 将Date类型的对象转化为String
	 * 
	 * @param
	 * @param sf
	 * @return
	 */
	public static String toStr(SimpleDateFormat sf) {
		Calendar d = Calendar.getInstance();
		return sf.format(d.getTime());
	}

	/**
	 * 取得当前日期，格式为2008-01-01 01:01:01
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		return Util.getCurrentDate(true);
	}

	/**
	 * 描述：取得当前日期,hasTime为true 格式为2008-01-01 01:01,hasTime为false 格式为2008-01-01
	 * 
	 * @param hasTime
	 * @return
	 */
	public static String getCurrentDate(boolean hasTime) {
		return Util.toStr(new Date(), hasTime ? Util.DF_TIME : Util.DF_DATE);
	}

	/**
	 * 描述：取得当前日期，精确到秒，格式为2008-01-01 01:01
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		return Util.getCurrentDateTime(true);
	}

	/**
	 * 描述：取得当前日期,hasTime为true 格式为2008-01-01 01:01,hasTime为false 格式为2008-01-01
	 * 
	 * @param hasTime
	 * @return
	 */
	public static String getCurrentDateTime(boolean hasTime) {
		return Util.toStr(new Date(), hasTime ? Util.DF_DTIME : Util.DF_DATE);
	}

	/**
	 * 描述：取得当前日期，精确到毫秒，格式为2008-01-01 01:01:01.111
	 * 
	 * @return
	 */
	public static String getCurrentDateMesc() {
		return Util.getCurrentDateMesc(true);
	}

	/**
	 * 描述：取得当前日期,hasTime为true 格式为2008-01-01 01:01,hasTime为false 格式为2008-01-01
	 * 
	 * @param hasTime
	 * @return
	 */
	public static String getCurrentDateMesc(boolean hasTime) {
		return Util.toStr(new Date(), hasTime ? Util.DF_TIME_MESC : Util.DF_DATE);
	}

	/**
	 * 描述：取得当前日期的后一天日期,hasTime为true 格式为2008-01-01 01:01,hasTime为false 格式为2008-01-01
	 * 
	 * @param hasTime
	 * @return
	 * @throws ParseException
	 */
	public static String getTomorrowDate(boolean hasTime) {
		// String dateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Util.toStr(cal.getTime(), hasTime ? Util.DF_TIME : Util.DF_DATE);
	}

	/**
	 * 描述：取得昨天日期,hasTime为true 格式为2008-01-01 01:01,hasTime为false 格式为2008-01-01
	 * 
	 * @param hasTime
	 * @return
	 * @throws ParseException
	 */
	public static String getYesterdayDate(boolean hasTime) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Util.toStr(cal.getTime(), hasTime ? Util.DF_TIME : Util.DF_DATE);
	}

	/**
	 * 描述：将日期毫秒数转化为年月格式（YYYYMM），例如20080607
	 * 
	 * @param time
	 *            日期毫秒数
	 * @return
	 */
	public static String toYearMonth(Long time) {
		Calendar d = Calendar.getInstance();
		d.setTimeInMillis(time);
		int year = d.get(Calendar.YEAR);
		int month = d.get(Calendar.MONTH) + 1;
		return "" + year + (month > 9 ? ("" + month) : ("0" + month));
	}

	/**
	 * 描述：将日期毫秒数转化为年月日格式（YYYYMMDD），例如2008060708
	 * 
	 * @param time
	 *            日期毫秒数
	 * @return
	 */
	public static String toDate(Long time) {
		Calendar d = Calendar.getInstance();
		d.setTimeInMillis(time);
		int year = d.get(Calendar.YEAR);
		int month = d.get(Calendar.MONTH) + 1;
		int date = d.get(Calendar.DATE);
		return "" + year + "-" + (month > 9 ? ("" + month) : ("0" + month)) + "-" + (date > 9 ? ("" + date) : ("0" + date));
	}

	/**
	 * 描述：获取当前月
	 * 
	 * @return 当前月
	 */
	public static String getCurrentMonth() {
		Calendar d = Calendar.getInstance();
		int month = d.get(Calendar.MONTH) + 1;
		return String.valueOf(month);
	}

	/**
	 * 描述：获取当前年份
	 * 
	 * @return 当前年份
	 */
	public static String getCurrentYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Calendar d = Calendar.getInstance();
		return sdf.format(d.getTime());
	}

	/**
	 * 描述：获取当前年月
	 * 
	 * @return 当前年月
	 */
	public static String getCurrentYearMonth() {
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM"); Calendar d =
		 * Calendar.getInstance(); return sdf.format(d.getTime());
		 */
		return "201803";
	}

	/**
	 * 描述：获取当前年月
	 * 
	 * @return 当前年月
	 */
	public static String getCurrentRealYearMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Calendar d = Calendar.getInstance();
		return sdf.format(d.getTime());
	}

	/**
	 * 描述：获取当前年份与上个月份
	 * 
	 * @return 当前年份与上个月份
	 */
	public static String getLastYearMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Calendar d = Calendar.getInstance();
		d.add(Calendar.MONTH, -1);
		return sdf.format(d.getTime());
	}

	/**
	 * 获取上一月
	 * 
	 * @param s
	 * @param sf
	 * @return
	 */
	public static String getLastYearMonth(String s, SimpleDateFormat sf) {
		Date date = Util.toDate(s, sf);
		if (date != null) {
			Calendar d = Calendar.getInstance();
			d.setTimeInMillis(date.getTime());
			int year = d.get(Calendar.YEAR);
			int month = d.get(Calendar.MONTH);
			return "" + year + (month > 9 ? ("" + month) : ("0" + month));
		}
		return null;
	}

	/**
	 * 获取当前季度的年月份范围
	 * 
	 * @return current quarter
	 */
	public static String getCurrentQuarter(String s, SimpleDateFormat sf) {
		Date date = Util.toDate(s, sf);
		if (date != null) {
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTimeInMillis(date.getTime());
			startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3) * 3);
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3) * 3 + 2);
			return sf.format(startCalendar.getTime()) + "," + sf.format(endCalendar.getTime());
		}
		return null;
	}

	/**
	 * 描述：获取指定日期的，数月之后的日期
	 * 
	 * @param s
	 * @param sf
	 * @param count
	 * @return
	 */
	public static String getAddYearDate(String s, SimpleDateFormat sf, int count) {
		Date date = Util.toDate(s, sf);
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date.getTime());
			calendar.add(Calendar.YEAR, count);
			return sf.format(calendar.getTime());
		}
		return null;
	}

	/**
	 * 描述：获取指定日期的，数月之后的日期
	 * 
	 * @param s
	 * @param sf
	 * @param count
	 * @return
	 */
	public static String getAddMonthDate(String s, SimpleDateFormat sf, int count) {
		Date date = Util.toDate(s, sf);
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date.getTime());
			calendar.add(Calendar.MONTH, count);
			return sf.format(calendar.getTime());
		}
		return null;
	}

	/**
	 * 描述：获取指定日期的，数月之后的日期
	 * 
	 * @param
	 * @param
	 * @param count
	 * @return
	 */
	public static Date getAddMonthDate(Date d, int count) {
		if (d != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			calendar.add(Calendar.MONTH, count);
			return calendar.getTime();
		}
		return null;
	}

	/**
	 * 描述：获取指定日期的，数日之后的日期
	 * 
	 * @param
	 * @param sf
	 * @param count
	 * @return
	 */
	public static String getAddDayDate(Date d, SimpleDateFormat sf, int count) {
		if (d != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			calendar.add(Calendar.DATE, count);
			return sf.format(calendar.getTime());
		}
		return null;
	}

	/**
	 * 描述：获取指定日期的，数日之后的日期
	 * 
	 * @param s
	 * @param sf
	 * @param count
	 * @return
	 */
	public static String getAddDayDate(String s, SimpleDateFormat sf, int count) {
		Date date = Util.toDate(s, sf);
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date.getTime());
			calendar.add(Calendar.DATE, count);
			return sf.format(calendar.getTime());
		}
		return null;
	}

	/**
	 * 描述：获取指定日期的，数日之后的日期
	 * 
	 * @param
	 * @param
	 * @param count
	 * @return
	 */
	public static Date getAddDayDate(Date d, int count) {
		if (d != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			calendar.add(Calendar.DATE, count);
			return calendar.getTime();
		}
		return null;
	}
	
	/**
	 * java判断是否周末
	 * @param theDate
	 * @return
	 */
	public static boolean isWeekend(Date theDate) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(theDate);
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
			return true;
		}
		if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
			return true;
		}
		return false;
	}

	/**
	 * java判断是否周末
	 * @param theDate
	 * @return
	 */
	public static boolean isWeekend(String theDate, SimpleDateFormat sf) {
		Date theDate2 = Util.toDate(theDate, sf);
		if(theDate2 == null) {
			return false;
		}
		Calendar cal=Calendar.getInstance();
		cal.setTime(theDate2);
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
			return true;
		}
		if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
			return true;
		}
		return false;
	}
	
	/**
	 * 描述：判断是否为字母a-z,A-Z
	 * 
	 * @param c
	 * @return
	 */
	static public boolean isCharator(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}

	/**
	 * 描述：判断是否为数字0-9
	 * 
	 * @param c
	 * @return
	 */
	static public boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	/**
	 * 描述：获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFileExtension(String filename) {
		return getFileExtension(filename, "");
	}

	/**
	 * 描述：获取文件扩展名
	 * 
	 * @param filename
	 * @param defExt
	 * @return
	 */
	public static String getFileExtension(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > 0) && (i < (filename.length() - 1))) {
				return (filename.substring(i + 1)).toLowerCase();
			}
		}
		return defExt.toLowerCase();
	}

	/**
	 * 描述：获取文件标题
	 * 
	 * @param filename
	 * @param
	 * @return
	 */
	public static String getFileTitle(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > 0) && (i < (filename.length() - 1))) {
				return filename.substring(0, i);
			}
		}
		return "";
	}

	public synchronized static long generateId() {
		long id = new Date().getTime();
		if (currentId < id) {
			Util.currentId = id;
		} else {
			currentId++;
		}
		return currentId;
	}

	/**
	 * 获取两个list1与list2的差集
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static List<String> listRemove(List<String> list1, List<String> list2) {
		List<String> tempList = null;
		if (list1 == null || list1.size() == 0) {
			return tempList;
		}
		tempList = new ArrayList<String>();
		tempList.addAll(list1);

		if (list2 == null || list2.size() == 0) {
			return tempList;
		}
		tempList.removeAll(list2);
		return tempList;
	}

	/**
	 * 判断是否为整数
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 获取当前季度的年月份范围
	 * 
	 * @return current quarter
	 */
	public static String getCurrentQuarter() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3) * 3);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3) * 3 + 2);
		return sdf.format(startCalendar.getTime()) + "," + sdf.format(endCalendar.getTime());
	}

	/**
	 * 小数点后保留两位
	 * 
	 * @param f
	 * @return
	 */
	public static String formatPoint2(Object f) {
		DecimalFormat decimalFormat = new DecimalFormat(".00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
		return decimalFormat.format(f);// format 返回的是字符串
	}

	/**
	 * 数据格式化
	 * 
	 * @param
	 * @return
	 */
	public static String divided(int a1, int a2) {
		if (a2 == 0) {
			return String.valueOf(a1);
		}
		return a1 % a2 == 0 ? String.valueOf(a1 / a2) : Util.formatPoint2(Double.valueOf(a1) / Double.valueOf(a2));
	}

	/**
	 * 功能描述：总页数计算
	 * 
	 * @param
	 * @return int
	 */
	public static int countPages(long totalNum, int pageSize) {
		if (totalNum % pageSize == 0) {
			return (int) (totalNum / pageSize);
		} else {
			return (int) (totalNum / pageSize) + 1;
		}
	}

	/**
	 * 转换为下划线
	 * 
	 * @param camelCaseName
	 * @return
	 */
	public static String underscoreName(String camelCaseName) {
		StringBuilder result = new StringBuilder();
		if (camelCaseName != null && camelCaseName.length() > 0) {
			result.append(camelCaseName.substring(0, 1).toLowerCase());
			for (int i = 1; i < camelCaseName.length(); i++) {
				char ch = camelCaseName.charAt(i);
				if (Character.isUpperCase(ch)) {
					result.append("_");
					result.append(Character.toLowerCase(ch));
				} else {
					result.append(ch);
				}
			}
		}
		return result.toString();
	}

	/**
	 * 转换为驼峰
	 * 
	 * @param underscoreName
	 * @return
	 */
	public static String camelCaseName(String underscoreName) {
		StringBuilder result = new StringBuilder();
		if (underscoreName != null && underscoreName.length() > 0) {
			boolean flag = false;
			for (int i = 0; i < underscoreName.length(); i++) {
				char ch = underscoreName.charAt(i);
				if ("_".charAt(0) == ch) {
					flag = true;
				} else {
					if (flag) {
						result.append(Character.toUpperCase(ch));
						flag = false;
					} else {
						result.append(ch);
					}
				}
			}
		}
		return result.toString();
	}

	/**
	 * 格式化string为Date
	 * 
	 * @param datestr
	 * @return date
	 */
	private static Date parseDate(String datestr) {
		if (null == datestr || "".equals(datestr)) {
			return null;
		}
		try {
			String fmtstr = null;
			if (datestr.indexOf(':') > 0) {
				fmtstr = "yyyy-MM-dd HH:mm:ss";
			} else {
				fmtstr = "yyyy-MM-dd";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
			return sdf.parse(datestr);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 功能描述：总页数计算
	 * 
	 * @param
	 * @return int
	 */
	public static int getPageCount(int totalCount, int pageSize) {
		if (totalCount % pageSize == 0) {
			return totalCount / pageSize;
		} else {
			return totalCount / pageSize + 1;
		}
	}

	/**
	 * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
	 * 
	 * @param nowTime
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
		if (nowTime == null) {
			return false;
		}
		if (startTime == null || endTime == null) {
			return false;
		}
		if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
			return true;
		}

		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断当前时间是否在给定时间之后，注意时间格式要一致
	 * 
	 * @param nowTime
	 * @param referenceTime
	 * @return
	 */
	public static boolean isAffterDate(Date nowTime, Date referenceTime) {
		if (nowTime == null) {
			return false;
		}
		if (referenceTime == null) {
			return false;
		}
		if (nowTime.getTime() == referenceTime.getTime()) {
			return true;
		}

		Calendar nowDate = Calendar.getInstance();
		nowDate.setTime(nowTime);

		Calendar tempDate = Calendar.getInstance();
		tempDate.setTime(referenceTime);

		if (nowDate.after(tempDate)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将字符串中的多个连续空格变成一个空格
	 * 
	 * @param str
	 * @return
	 */
	public static String resetBlank(String str) {
		if (str != null) {
			str = str.replaceAll("\\s{1,}", " ");
		}
		return str;
	}

	/**
	 * 以and或者or分割字符串，并将拆分结果存到map中，拆分字符为key，list为value
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, List<String>> splitByAndOr(String str) {
		Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
		if (Util.isNull(str)) {
			return null;
		}
		// 将字符串中的多个连续空格变成一个空格
		str = Util.resetBlank(str);

		// 将字符串中的and与or单词转为小写
		String[] array = str.split(" ");
		if (array != null && array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				if (array[i].trim().equalsIgnoreCase("and") || array[i].trim().equalsIgnoreCase("or")) {
					array[i] = array[i].toLowerCase();
				}
			}
			str = "";
			for (int i = 0; i < array.length; i++) {
				str += array[i] + " ";
			}
			// 字符串首尾补空格
			str = " " + str + " ";

			// 将字符串中的多个连续空格变成一个空格
			str = Util.resetBlank(str);
		}

		// 将字符串按and拆分
		List<String> andList = Util.splitByCharacter(str, "and");

		// 将字符串按or拆分
		List<String> orList = Util.splitByCharacter(str, "or");

		// 当以or拆分得到的查询条件多于以and拆分得到的查询条件时
		if (andList.size() < orList.size()) {
			map.put("or", orList);
		}
		// 否则
		else {
			map.put("and", andList);
		}

		return map;
	}

	/**
	 * @param str
	 * @param character
	 * @return
	 */
	public static List<String> splitByCharacter(String str, String character) {
		List<String> result = new ArrayList<String>();
		if (Util.isNull(str)) {
			return result;
		}
		if (character == null) {
			result.add(str);
		}
		// 将字符串拆分
		String[] array = str.split(" " + character.trim() + " ");
		if (array != null && array.length > 0) {
			for (String temp : array) {
				if (Util.notNull(temp)) {
					result.add(temp.trim());
				}
			}
		}
		return result;
	}

	/**
	 * 将排序字段格式化
	 * 
	 * @param sortName
	 * @param sortOrder
	 * @return
	 */
	public static String sortColumnFormatter(String sortName, String sortOrder) {
		String sortColumn = "";

		String[] sortNameArray = sortName != null ? sortName.split(",") : null;
		String[] sortOrderArray = sortOrder != null ? sortOrder.split(",") : null;

		if (sortNameArray == null || sortNameArray.length <= 0) {
			sortColumn = "id ASC";
		} else {
			for (int i = 0; i < sortNameArray.length; i++) {
				sortColumn += sortNameArray[i].trim() + " " + (sortOrderArray != null && Util.notNull(sortOrderArray[i]) ? sortOrderArray[i].trim() : "asc").toLowerCase() + ", ";
			}
		}
		if (Util.notNull(sortColumn)) {
			sortColumn = sortColumn.substring(0, sortColumn.lastIndexOf(","));
		}
		return sortColumn;
	}

	/**
	 * 计算两个日期相差的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long daysBetween(Date date1, Date date2) {
		if(date1 == null) {
			return 0;
		}
		if(date2 == null) {
			return date1.getTime() / (1000 * 3600 * 24);
		}
		
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		
		return (time1 - time2) / (1000 * 3600 * 24);
	}

	/**
	 * 获取自year开始，count年之内的年份列表
	 * @param year
	 * @param count
	 * @return
	 */
	public static List<String> getYearList(String year, int count) {
		List<String> yearList = null;
		if(Util.isNull(year) || !Util.isInteger(year)) {
			return null;
		}
		yearList = new ArrayList<String>();
		if(count >= 0) {
			for(int i=0; i<=count-1; i++) {
				String tempYear = Util.getAddYearDate(year, Util.DF_YEAR, i);
				yearList.add(tempYear);
			}
		} else {
			for(int i=count+1; i<=0; i++) {
				String tempYear = Util.getAddYearDate(year, Util.DF_YEAR, i);
				yearList.add(tempYear);
			}
		}
		return yearList;
	}

	/**
	 * 获取自year开始，count年之内的年份列表
	 * 
	 * @param date
	 * @param count
	 * @return
	 */
	public static List<String> getYearList(Date date, int count) {
		if(Util.isNull(date)) {
			return null;
		}
		
		return Util.getYearList(Util.toStr(date, Util.DF_YEAR), count);
	}
	
	/**
	 * desc: 字符串转数字
	 * @param str
	 * @return
	 */
	public static int string2Int(String str) {
		if(Util.notNull(str) && Util.isInteger(str)) {
			try {
				return Integer.valueOf(str);
			} catch (Exception e) {
				return 0;
			}
		}
		return 0;
	}

	/**
	 * desc: 字符串转数字
	 * @param str
	 * @return
	 */
	public static Double string2Double(String str) {
		try {
	    	if(Util.notNull(str)) {
	    		return Double.valueOf(str);
	    	}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	/**
	 * 计算两个日期相差的天数
	 * @param dateStr1
	 * @param dateStr2
	 * @param sdf
	 * @return
	 */
	public static long daysBetween(String dateStr1, String dateStr2, SimpleDateFormat sdf) {
		Date date1 = Util.toDate(dateStr1, sdf);
		Date date2 = Util.toDate(dateStr2, sdf);

		return daysBetween(date1, date2);
	}
	/**
	 * gson转对象
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> T fromJson(JsonElement json, Class<T> classOfT) {
		try {
			return gson.fromJson(json, classOfT);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * gson转对象
	 * @param string
	 * @param
	 * @return
	 */
	public static <T> T fromJson(String string, Class<T> classOfT) {
		try {
			return gson.fromJson(string, classOfT);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 计算两个日期相差的秒数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long secondsBetween(Date date1, Date date2) {
		if(date1 == null) {
			return 0;
		}
		if(date2 == null) {
			return date1.getTime() / 1000;
		}
		
		long time1 = date1.getTime();
		long time2 = date2.getTime();

		return (time1 - time2) / 1000;
	}

	/**
	 * 描述：获取指定日期的，数分钟之后的日期
	 *
	 * @param d
	 * @param sf
	 * @param count
	 * @return
	 */
	public static String getAddMinDate(Date d, SimpleDateFormat sf, int count) {
		if (d != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			calendar.add(Calendar.MINUTE, count);
			return sf.format(calendar.getTime());
		}
		return null;
	}

	public static void main(String[] args) {
		Date date1 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		
		System.out.print(date1.getTime() + " ");
		System.out.print(time1);
	}


    /**
     * 获取当天几天前或后的结束日期  yyyy:MM:dd 00:00:00  如一天的结束日期 2020:08:12 23:59:59
     * @param day
     * @return
     */
    public static Date getBeforeOrAfterDay(int day){
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH,day);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,0);
        Date dayStart = calendar.getTime();
        return dayStart;
    }


    /**
     * 获取星期
     *
     * @param date 格式  yyyy-MM-dd
     * @return  "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"
     */
    public static String getWeekDay(String date) throws Exception {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int w = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
            Calendar cal = Calendar.getInstance();
            Date time = sdf.parse(date);
            cal.setTime(time);
            w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0) {
                w = 0;
            }

        return weekDays[w];
    }

    /**
     * 获取本周7天所有的日期 按顺序
     * @param date 格式 yyyy-MM-dd
     * @return
     * @throws Exception
     */
    public  static String[] getWeekAllDay(String date) throws Exception{
        String[] days = new String[7];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        Date time = sdf.parse(date);
        cal.setTime(time);
       // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); //输出要计算日期
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        // System.out.println("所在周星期一的日期：" + sdf.format(cal.getTime()));
        for(int i = 0;i <days.length;i++){
            days[i]  = sdf.format(addDate(sdf.parse(sdf.format(cal.getTime())), i));
        }
        return days;
    }


    /**
     * 计算某天后的日期
     * @param yyyy-MM-DD
     * @return yyyy-MM-DD
     */
    public static Date addDate(Date date,long day) throws com.hp.hpl.sparta.ParseException {

        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
        time+=day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }


    /**
     * 查询某天的下个月的月初和月末
     *
     * @return  key为  startDate  endDate  类型为Date
     */
    public static Map<String, Object> getNextMonth(Date date) {
        //设置startDate
        Calendar c = Calendar.getInstance();
        //设置endDate
        Calendar c2 = Calendar.getInstance();
        c.setTime(date);
        c2.setTime(date);
        HashMap<String, Object> dateMap = new HashMap<String, Object>();
        //设置start
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //设置为0点0分0秒
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        //存入年月显示
        // dateMap.put("yearMonthStr", format.format(c.getTime()));
        //将c 设置为下个月
        c.add(Calendar.MONTH, 1);
        dateMap.put("startDate", c.getTime());

        //设置end 为当前月的月底 23时59分59秒
        c2.set(Calendar.DAY_OF_MONTH, 1);
        //设置时间 23时59分59秒
        c2.set(c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        //获得当前月最后一天
        c2.add(Calendar.MONTH, 2);
        c2.set(Calendar.DAY_OF_MONTH, 0);
        //存入map
        //获取当前时间的下一个月
        dateMap.put("endDate", c2.getTime());
        //放入集合
        return dateMap;
    }

}
