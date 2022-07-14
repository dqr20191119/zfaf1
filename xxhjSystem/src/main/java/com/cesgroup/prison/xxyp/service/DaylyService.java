package com.cesgroup.prison.xxyp.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.prison.xxyp.entity.Dayly;

public interface DaylyService {
	public void initDaylyData(String cusNumber);
	public List<Map<String,Object>> getDaylyData(Dayly dayly);
}
