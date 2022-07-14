package com.cesgroup.prison.xxyp.dao;

import java.util.List;
import java.util.Map;
import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxyp.entity.ProvDayly;

public interface ProvDaylyMapper extends BaseDao<ProvDayly,String>{
	public List<Map<String,Object>> getProvDaylyData(Map<String, Object> map);
	//删除当天的日报
	public int deleteSameDay();
}
