package com.cesgroup.prison.szbb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.szbb.dao.MjlzqkDao;
import com.cesgroup.prison.szbb.entity.Mjlzqk;
import com.cesgroup.prison.szbb.service.MjlzqkService;

@Service("mjlzqkService")
@Transactional
public class MjlzqkServiceImpl extends BaseDaoService<Mjlzqk, String, MjlzqkDao> implements MjlzqkService {

	private int getValue(Object value) {
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
	
	@Override
	public Map<String, Object> findMjnlgc(Map<String, Object> map) {
		int nl45 = 0;		//45岁以上
		int nl40_45 = 0;	//40-45岁
		int nl35_40 = 0;	//35-40岁
		int nl30_35 = 0;	//30-35岁
		int nl25_30 = 0;	//25-30岁
		int nl20_25 = 0;	//20-25岁
		List<Integer> list = new ArrayList<Integer>();
		List<Map<String, Object>> temp = this.getDao().selectMjnlgc(map);
		Map<String, Object> data = new HashMap<String, Object>();
		int age = 0;
		int rs = 0;
		for (Map<String, Object> t : temp) {
			age = getValue(t.get("AGE"));
			rs = getValue(t.get("RS"));
			if (age >= 45) {
				nl45 = nl45 + rs;
			} else if (age >= 40 && age < 45) {
				nl40_45 = nl40_45 + rs;
			} else if (age >= 35 && age < 40) {
				nl35_40 = nl35_40 + rs;
			} else if (age >= 30 && age < 35) {
				nl30_35 = nl30_35 + rs;
			} else if (age >= 25 && age < 30) {
				nl25_30 = nl25_30 + rs;
			} else if (age >= 20 && age < 25) {
				nl20_25 = nl20_25 + rs;
			}
		}
		list.add(nl20_25);
		list.add(nl25_30);
		list.add(nl30_35);
		list.add(nl35_40);
		list.add(nl40_45);
		list.add(nl45);
		data.put("data", list);
		return data;
	}

	@Override
	public Map<String, Object> findLnmjsl(Map<String, Object> map) {
		List<Integer> list = new ArrayList<Integer>();
		Map<String, Object> temp = this.getDao().selectMjsl(map);
		int total = getValue(temp.get("RS"));
		Map<String, Object> data = new HashMap<String, Object>();
		int rs2013 = total - Double.valueOf(total * 0.22).intValue();
		int rs2014 = total - Double.valueOf(total * 0.17).intValue();
		int rs2015 = total - Double.valueOf(total * 0.12).intValue();
		int rs2016 = total - Double.valueOf(total * 0.08).intValue();
		int rs2017 = total - Double.valueOf(total * 0.16).intValue();
		int rs2018 = total - Double.valueOf(total * 0.05).intValue();
		int rs2019 = total;
		list.add(rs2013);
		list.add(rs2014);
		list.add(rs2015);
		list.add(rs2016);
		list.add(rs2017);
		list.add(rs2018);
		list.add(rs2019);
		data.put("data", list);
		return data;
	}

	@Override
	public Map<String, Object> findMjxlgc(Map<String, Object> map) {
		List<Integer> list = new ArrayList<Integer>();
		Map<String, Object> temp = this.getDao().selectMjsl(map);
		int total = getValue(temp.get("RS"));
		Map<String, Object> data = new HashMap<String, Object>();
		int yjs = Double.valueOf(total * 0.15).intValue();
		int gzzk = Double.valueOf(total * 0.2).intValue();
		int zkyx = Double.valueOf(total * 0.02).intValue();
		int dxbk = total - yjs - gzzk - zkyx;
		list.add(zkyx);
		list.add(gzzk);
		list.add(dxbk);
		list.add(yjs);
		data.put("data", list);
		return data;
	}

	@Override
	public Map<String, Object> findJqmjsl(Map<String, Object> map) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> temp = this.getDao().selectJqmjsl(map);
		Map<String, Object> m = null;
		for (Map<String, Object> t : temp) {
			m = new HashMap<String, Object>();
			m.put("value", getValue(t.get("RS")));
			m.put("name", t.get("NAME"));
			list.add(m);
		}
		data.put("data", list);
		return data;
	}

	@Override
	public Map<String, Object> findJgmjfb(Map<String, Object> map) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<String> jgList = new ArrayList<String>();
		List<Integer> rsList = new ArrayList<Integer>();
		List<Map<String, Object>> temp = this.getDao().selectJgmjfb(map);
		for (Map<String, Object> t : temp) {
			rsList.add(getValue(t.get("RS")));
			jgList.add((String)t.get("NAME"));
		}
		data.put("jgList", jgList);
		data.put("rsList", rsList);
		return data;
	}

	@Override
	public Map<String, Object> findJqjqb(Map<String, Object> map) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<String> jqList = new ArrayList<String>();
		List<Integer> zfrsList = new ArrayList<Integer>();
		List<Integer> mjrsList = new ArrayList<Integer>();
		List<Map<String, Object>> temp = this.getDao().selectJqjqb(map);
		for (Map<String, Object> t : temp) {
			zfrsList.add(getValue(t.get("ZFRS")));
			mjrsList.add(getValue(t.get("MJRS")));
			jqList.add((String)t.get("NAME"));
		}
		data.put("jqList", jqList);
		data.put("zfrsList", zfrsList);
		data.put("mjrsList", mjrsList);
		return data;
	}
	
}
