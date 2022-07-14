package com.cesgroup.prison.utils;

import java.math.BigDecimal;
import java.util.Map;

public class PageUtil {

	private static String dialect;
	static {
		dialect = "oracle";
	}
	public static int getBegin(int page, int rows) {
		int begin = 0;
		if ("mysql".equals(dialect)) {
			begin = (page - 1) * rows;
		} else if ("oracle".equals(dialect)) {
			begin = (page - 1) * rows + 1;  
		}
		return begin;
	}
	
	public static int getEnd(int page, int rows) {
		int end = 0;
		if ("mysql".equals(dialect)) {
			end = rows;
		} else if ("oracle".equals(dialect)) {
			end = (page - 1) * rows + 1 + rows;
		}
		return end;
	}
	
	public static int getTotal(Map<String, Object> map) {
		int total = 0;
		Object tmp = map.get("TOTAL");
		if (tmp instanceof BigDecimal) {
			total = ((BigDecimal)tmp).intValue();
		} else if (tmp instanceof Long) {
			total = ((Long)tmp).intValue();
		} else if (tmp instanceof Integer) {
			total = (Integer)tmp;
		}
		return total;
	}
	
	public static int getValue(Object value) {
		int total = 0;
		if (value instanceof BigDecimal) {
			total = ((BigDecimal)value).intValue();
		} else if (value instanceof Long) {
			total = ((Long)value).intValue();
		} else if (value instanceof Integer) {
			total = (Integer)value;
		} else if (value instanceof Double) {
			total = ((Double)value).intValue();
		} else if (value instanceof Float) {
			total = ((Float)value).intValue();
		} else if (value instanceof String) {
			total = Integer.valueOf((String)value);
		}
		return total;
	}
	
}
