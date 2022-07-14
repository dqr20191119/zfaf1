package com.cesgroup.scrap.db.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义SQL条件语句<br/>
 * <b>注意</b>
 * <p><blockquote><pre>
 * 1. 每一个通配符都必须设置其对应的值（值可以为""）
 * 2. 设置自定义条件时必须按SQL语句中统配符定义的顺序来设置
 * </pre></blockquote></p>
 * 
 * <b>事例</b>
 * <p><blockquote><pre>
 * // SQL : "select * from dual @where@ @and@"
 * // 参数设置
 * CustomWheres cWheres = new CustomWheres();
 * cWheres.put("where", "where 1=1");
 * cWheres.put("and","and 1 > 0");
 * 或
 * cWheres.put("where", "1=1");
 * cWheres.put("and","1 > 0");
 * </pre></blockquote></p>
 * 
 *
 */
public class CustomWheres {
	private List<Map<String, String>> whereList = new ArrayList<Map<String,String>>();
	private int index = 0;
	
	
	/**
	 * 设置自定义SQL条件语句
	 * @param key 通配符标识（where|and|or|group by|having|between|left join|inner join|right join|on|group by）
	 * @param value 自定义SQL语句
	 */
	public void put(String key, String value){
		if (value == null) value = "";
		whereList.add(new LinkedHashMap<String, String>());
		whereList.get(index++).put(key, value);
	}
	
	/**
	 * 获取
	 * @return
	 */
	public List<Map<String, String>> get(){
		return whereList;
	}
}
