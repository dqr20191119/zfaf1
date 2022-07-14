package com.cesgroup.prison.sppz.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.sppz.entity.VideoClient;

public interface VideoClientMapper extends BaseDao<VideoClient,String>{
    
	public Page<Map<String,String>> searchVideoClient(Map<String,Object> map,PageRequest pageRequest);
	
	public void updatePart(Map<String,Object> map);
}