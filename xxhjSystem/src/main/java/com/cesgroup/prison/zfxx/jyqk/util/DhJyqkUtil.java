package com.cesgroup.prison.zfxx.jyqk.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DhJyqkUtil {
	
	private static Map<String, String> MAP = new HashMap<String, String>();
	private static List<String> jyList = new ArrayList<String>();
	
	static {
		MAP.put("1101", "1");
		MAP.put("1102", "2");
		MAP.put("1103", "3");
		MAP.put("1104", "4");
		MAP.put("1105", "6");
		MAP.put("1120", "5");
		MAP.put("1121", "7");
		MAP.put("1142", "8");
		MAP.put("1145", "9");
		MAP.put("1146", "10");
		MAP.put("1147", "12");
		MAP.put("1149", "11");
		
		jyList.add("1101");
		jyList.add("1102");
		jyList.add("1103");
		jyList.add("1104");
		jyList.add("1105");
		jyList.add("1120");
		jyList.add("1121");
		jyList.add("1142");
		jyList.add("1145");
		jyList.add("1146");
		jyList.add("1147");
		jyList.add("1149");
	}
	
	public static List<String> getJyList() {
		return jyList;
	}
	
	public static String getPrisonAreaId(String jyCode) {
		return MAP.get(jyCode);
	}
	
}
