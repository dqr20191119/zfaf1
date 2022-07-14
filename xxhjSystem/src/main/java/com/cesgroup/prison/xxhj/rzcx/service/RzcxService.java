package com.cesgroup.prison.xxhj.rzcx.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.rzcx.entity.RzcxEntity;

public interface RzcxService extends IBaseCRUDService<RzcxEntity, String> {

	public Page<RzcxEntity> findList(RzcxEntity RzcxEntity,PageRequest pageRequest);

	public Page<RzcxEntity> searchSwdbPage(RzcxEntity RzcxEntity,PageRequest pageRequest,String startTime ,String endTime,String SearchDate);
	
	public Page<RzcxEntity> searchSwdbPage1(RzcxEntity RzcxEntity,PageRequest pageRequest,String startTime ,String endTime,String SearchDate);
	
	public List<Map<String, Object>> sjChart(RzcxEntity RzcxEntity, PageRequest pageRequest,String startTime ,String endTime,String SearchDate) ;
	
	public List<Map<String, Object>> czChart(RzcxEntity RzcxEntity, PageRequest pageRequest,String startTime ,String endTime,String SearchDate);
}
