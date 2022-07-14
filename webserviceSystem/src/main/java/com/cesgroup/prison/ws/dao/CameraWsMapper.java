package com.cesgroup.prison.ws.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.ws.entity.CameraWs;

public interface CameraWsMapper extends BaseDao<CameraWs, String> {

	public List<Map<String, Object>> getCameraList(Map<String, Object> paramMap);
}