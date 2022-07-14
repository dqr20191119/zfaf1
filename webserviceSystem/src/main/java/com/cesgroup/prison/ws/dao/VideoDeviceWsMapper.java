package com.cesgroup.prison.ws.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.ws.entity.VideoDeviceWs;

public interface VideoDeviceWsMapper extends BaseDao<VideoDeviceWs, String> {

	public List<Map<String, Object>> getVideoDeviceList(Map<String, Object> paramMap);
}