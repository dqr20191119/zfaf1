package com.cesgroup.prison.broadcastPlay.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.broadcastPlay.entity.BroadcastPlay;

public interface BroadcastPlayDao extends BaseDao<BroadcastPlay, String> {
	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	void updateInfo(Map<String, Object> map);

	void deleteByIds(List<String> ids);
	
	public List<Map<String, Object>> getXgnr(String sxsj);
}