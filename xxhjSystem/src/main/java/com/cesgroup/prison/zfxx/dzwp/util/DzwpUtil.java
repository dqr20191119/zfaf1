package com.cesgroup.prison.zfxx.dzwp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DzwpUtil {
	
	private static Map<String, String> MAP = new HashMap<String, String>();
	private static List<String> jyList = new ArrayList<String>();
	
	static {
		MAP.put("1101", "235");
		MAP.put("1102", "236");
		MAP.put("1103", "232");
		MAP.put("1104", "230");
		MAP.put("1105", "233");
		MAP.put("1120", "231");
		MAP.put("1121", "234");
		MAP.put("1142", "134");
		MAP.put("1145", "131");
		MAP.put("1146", "130");
		MAP.put("1147", "133");
		MAP.put("1149", "132");
		
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
