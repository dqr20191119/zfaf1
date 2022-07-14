package com.cesgroup.prison.httpclient.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cesgroup.framework.utils.PropertiesUtil;

public class SzbbUtil {

	private static Map<String, String> MAP = new HashMap<String, String>();
	private static List<String> jyList = new ArrayList<String>();
	
	static {
		MAP.put("1100", "73878031185de329fa6876725c100000");
		MAP.put("1101", "73878031185de329fa6876725c100001");
		MAP.put("1102", "73878031185de329fa6876725c100002");
		MAP.put("1103", "73878031185de329fa6876725c100003");
		MAP.put("1104", "73878031185de329fa6876725c100004");
		MAP.put("1105", "73878031185de329fa6876725c100006");
		MAP.put("1120", "73878031185de329fa6876725c100005");
		MAP.put("1121", "73878031185de329fa6876725c100007");
		MAP.put("1142", "73878031185de329fa6876725c100104");
		MAP.put("1145", "73878031185de329fa6876725c100101");
		MAP.put("1146", "73878031185de329fa6876725c100105");
		MAP.put("1147", "73878031185de329fa6876725c100103");
		MAP.put("1149", "73878031185de329fa6876725c100107");
		
		String list = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.szbb.jy");
		for (String jyCode : list.split("#")) {
			jyList.add(jyCode);
		}
	}
	
	public static String getCorpByJyCode(String jyCode) {
		return MAP.get(jyCode);
	}
	
	public static List<String> initSzbb() {
		return jyList;
	}
	
	public static String getUrlByJyCode(String jyCode) {
		return PropertiesUtil.getValueByKeyUnchanged("application", "synchro.szbb.url." + jyCode);
	}
	
}
