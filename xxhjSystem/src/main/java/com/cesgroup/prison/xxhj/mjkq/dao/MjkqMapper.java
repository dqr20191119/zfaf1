package com.cesgroup.prison.xxhj.mjkq.dao;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.mjkq.entity.MjkqEntity;

public interface MjkqMapper extends BaseDao<MjkqEntity, String> {
	/**
     * 分页 民警考勤查询
     * @param map
     * @param pageable
     * @return
     */
	Page<Map<String, Object>> findWithPage(Map<String, Object> map, Pageable pageable);

	void deleteYyjyZzjgtb();
	
	void insertYyjyZzjgtb(List<MjkqEntity> list);
	
	List<String> selectYyjyZzjg();
	
	void deleteByKqrqAndJyIdAndJqId(Map<String, Object> map);
	
	void insertBatch(List<MjkqEntity> list);
	
}