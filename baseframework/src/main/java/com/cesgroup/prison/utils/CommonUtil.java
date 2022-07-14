package com.cesgroup.prison.utils;

import com.cesgroup.framework.util.IpUtil;
import com.hp.hpl.sparta.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


public class CommonUtil {

    /**
     * 将Object转换成String,如果Object为null则返回空字符�?
     *
     * @param obj
     * @return
     * @date 2013-11-6 下午7:54:39
     */
    public static String toStr(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }

    /**
     * 创建唯一ID
     *
     * @return
     */
    public static String createUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 删除数组中重复元�?
     */
    public static String[] toDiffArray(String[] s) {
        Set<String> set = new HashSet<String>();
        for (String sa : s) {
            set.add(sa);
        }
        return set.toArray(new String[]{});
    }

    
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpSession getSession() {
        return getHttpServletRequest().getSession();
    }

    public static Object getSessionAttribute(String name) {
        return getSession().getAttribute(name);
    }

    public static String getIpAddress() {
    	
    	return IpUtil.getIpAddress();
    }

    public static void mapClean(final Map<String, Object> map) {
        for (Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Object> item = it.next();
            Object val = item.getValue();
            if (val instanceof String && val.toString().length() == 0) {
                it.remove();
            }
        }
    }
    
    /**
     * 生成唯一时间序列号，用于消息
     * @return
     */
    public static String genTimeSequence(){
		String sequence =  com.cesgroup.prison.code.tool.DateUtils.formatDateTimesss(new Date());//当前序号
		//String x =  new java.util.Random().netInt(max - min) + min;//随机 min  与max  之间的数
		String recordId =  String.valueOf(new java.util.Random().nextInt(1000));//设置随机数
		sequence += recordId;
		return sequence;
		
	}
    
    /**
     * 计算两日期之间相隔天数
     * @param yyyy-mm-dd
     * @return 
     */
    public static String getDateDiff(Date startDate,Date endDate){
		 
		String sDate = String.valueOf(startDate);
		String eDate = String.valueOf(endDate);
		long startTime = new Date(Date.parse(sDate.replace("-","/"))).getTime();
		long endTime = new Date(Date.parse(eDate.replace("-","/"))).getTime();
		String dates = String.valueOf(Math.abs(startTime - endTime)/(1000*60*60*24));

		return dates;
	}
    
    /**
     * 计算某天后的日期
     * @param yyyy-MM-DD
     * @return yyyy-MM-DD
     */
    public static Date addDate(Date date,long day) throws ParseException {
		
		 long time = date.getTime(); // 得到指定日期的毫秒数
		 day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
		 time+=day; // 相加得到新的毫秒数
		 return new Date(time); // 将毫秒数转换成日期
		}
    /**
     * 规范日期格式
     * @param yyyy-MM-DD
     * @return yyyy-MM-DD
     */
    
    public static String formatterDate(Date date,String type) {
    	
    	String day = String.valueOf(date.getDate() > 9 ? date.getDate() : "0" + date.getDate());
    	String month = String.valueOf((date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
    			+ (date.getMonth() + 1));
    	String hours = String.valueOf(date.getHours() > 9 ? date.getHours() : "0" + date.getHours());
    	String seconds = String.valueOf(date.getSeconds() > 9 ? date.getSeconds() : "0" + date.getSeconds());
    	String minutes = String.valueOf(date.getMinutes() > 9 ? date.getMinutes() : "0" + date.getMinutes());
    	
    	if(type == "ymd") {
    		return date.getYear() + '-' + month + '-' + day;
    	}else if(type == "hms") {
    		return hours + ":" + minutes + ":" + seconds;
    	}else if(type == "hm") {
    		return hours + ":" + minutes;
    	}else if(type == "ymdhms") {
    		return date.getYear() + '-' + month + '-' + day + " " + hours + ":" + minutes + ":" + seconds;
    	}else if(type == "ymdhm") {
    		return date.getYear() + '-' + month + '-' + day + " " + hours + ":" + minutes;
    	}else {
    		return date.getYear() + '-' + month + '-' + day;
    	}
    }

    /**
     * 判断数组中是否有重复的值
     */
    public static boolean checkArrayIsRepeat(String[] array) {
        HashSet<String> hashSet = new HashSet<String>();
        for (int i = 0; i < array.length; i++) {
            hashSet.add(array[i]);
        }
        if (hashSet.size() == array.length) {
            return true;
        } else {
            return false;
        }
    }


    public static String getRepeatString(List<String> listAll) {

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        StringBuffer repeatIps = new StringBuffer();
        for (String str : listAll) {
            if (hashMap.get(str) != null) {
                Integer value = hashMap.get(str);
                hashMap.put(str, value+1);
                repeatIps.append(str+",");
            } else {
                hashMap.put(str, 1);
            }
        }
        if(StringUtils.isNotBlank(repeatIps.toString())){
            repeatIps.deleteCharAt(repeatIps.length() - 1);
        }
        return repeatIps.toString();
    }

    public static String[] replaceArrayNull(String[] str){
        //用StringBuffer来存放数组中的非空元素，用“;”分隔
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<str.length; i++) {
            if("".equals(str[i])) {
                continue;
            }
            sb.append(str[i]);
            if(i != str.length - 1) {
                sb.append(",");
            }
        }
        //用String的split方法分割，得到数组
        str = sb.toString().split(",");
        return str;
    }

    public static List<String> replaceListNull(List<String> l){
        Iterator<String> it = l.iterator();
        while ( it.hasNext()){
            if(!StringUtils.isNotBlank(it.next())){
                it.remove();
            }
        }
        return l;
    }


}