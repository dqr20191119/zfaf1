package com.cesgroup.prison.xxhj.rzcx.service.impl;
 

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.xxhj.rzcx.entity.RzcxEntity;
import com.cesgroup.prison.xxhj.rzcx.dao.RzcxMapper;
import com.cesgroup.prison.xxhj.rzcx.service.RzcxService;
@Service("RzcxService")
public class RzcxServiceImpl extends BaseDaoService<RzcxEntity, String, RzcxMapper> implements RzcxService {
	
	@Resource
	private RzcxMapper RzcxMapper;

	@Override
	public Page<RzcxEntity> findList(RzcxEntity RzcxEntity, PageRequest pageRequest) {
		return RzcxMapper.findList(RzcxEntity,pageRequest);
	}


	@Override
	public Page<RzcxEntity> searchSwdbPage(RzcxEntity RzcxEntity, PageRequest pageRequest,String startTime ,String endTime,String SearchDate) {
		return RzcxMapper.searchSwdbPage(RzcxEntity,pageRequest,startTime , endTime,SearchDate);
	}
	
	/**
	 * 日志图表 时间图
	 * 
	 * */
	@Override
	public List<Map<String, Object>> sjChart(RzcxEntity RzcxEntity, PageRequest pageRequest,String startTime ,String endTime,String SearchDate) {
		return RzcxMapper.sjChart(RzcxEntity,pageRequest,startTime , endTime,SearchDate);
	}
	
	/**
	 * 日志图表 操作类型图
	 * 
	 * */
	@Override
	public List<Map<String, Object>> czChart(RzcxEntity RzcxEntity, PageRequest pageRequest,String startTime ,String endTime,String SearchDate) {
		return RzcxMapper.czChart(RzcxEntity,pageRequest,startTime , endTime,SearchDate);
	}
	
	/**
	 * 日志统计列表
	 * 
	 * */
	@Override
	public Page<RzcxEntity> searchSwdbPage1(RzcxEntity RzcxEntity, PageRequest pageRequest,String startTime ,String endTime,String SearchDate) {
		return RzcxMapper.searchSwdbPage1(RzcxEntity,pageRequest,startTime , endTime,SearchDate);
	}
	




}
