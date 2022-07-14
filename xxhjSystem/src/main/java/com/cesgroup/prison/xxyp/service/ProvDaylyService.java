package com.cesgroup.prison.xxyp.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.prison.xxyp.entity.ProvDayly;

public interface ProvDaylyService {
	public void initProvDaylyData();
	public List<Map<String,Object>> getProvDaylyData(ProvDayly provDayly);
}
