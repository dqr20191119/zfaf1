package com.cesgroup.prison.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapWrapperUtils {

	/**
	 * 将map的key中的下划线转为驼峰
	 * @param map
	 */
	public static Map<String, Object> underlineToCamelhump(Map<String, Object> map) {
		
		Map<String, Object> newMap = new HashMap<String, Object>();
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			Object val = map.get(key);
			
			Pattern linePattern = Pattern.compile("_(\\w)");
	        Matcher matcher = linePattern.matcher(key.toLowerCase()); 	        
	        StringBuffer sb = new StringBuffer();  
	        while(matcher.find()){  
	            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());  
	        }  
	        matcher.appendTail(sb);  
	        
	        newMap.put(sb.toString(), val);
		}
		
		return newMap;
	}
	
	/**
	 * 将map的key中的驼峰转为下划线
	 * @param map
	 */
	public static Map<String, Object> camelhumpToUnderline(Map<String, Object> map) {
		
		Map<String, Object> newMap = new HashMap<String, Object>();
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			Object val = map.get(key);
			
			Pattern humpPattern = Pattern.compile("[A-Z]"); 
			Matcher matcher = humpPattern.matcher(key);  
            StringBuffer sb = new StringBuffer();  
            while(matcher.find()){  
                matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());  
            }  
            matcher.appendTail(sb);  
			
            newMap.put(sb.toString(), val);
		}
		
		return newMap;
	}
}
