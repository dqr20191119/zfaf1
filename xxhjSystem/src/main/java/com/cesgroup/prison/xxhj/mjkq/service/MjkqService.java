package com.cesgroup.prison.xxhj.mjkq.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.mjkq.entity.MjkqEntity;

public interface MjkqService extends IBaseCRUDService<MjkqEntity, String> {
	
	Page<Map<String, Object>> queryWithPage(Map<String, Object> param, Pageable pageable);

	/**
	 * 同步岳阳监狱考勤系统组织机构
	 * @param map
	 */
	void synchroYyjyZzjg(Map<String, Object> map);
	
	/**
	 * 同步岳阳监狱当日考勤记录
	 * 先删除当日考勤历史记录
	 * 然后全量同步当日考勤记录
	 * @param map
	 */
	void synchroYyjyDrKqjl(Map<String, Object> map);
	
	/**
	 * 同步岳阳监狱考勤记录定时任务
	 */
	void synchroJob();
	
}