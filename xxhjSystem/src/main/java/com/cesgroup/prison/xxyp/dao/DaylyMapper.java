package com.cesgroup.prison.xxyp.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxyp.entity.Dayly;

public interface DaylyMapper extends BaseDao<Dayly,String>{
	public List<Map<String,Object>> getDaylyData(Map<String, Object> map);
	//删除当天的日报
	public int deleteSameDay(String cusNumber);
}
