package com.cesgroup.prison.xxhj.rzcx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.rzcx.entity.RzcxEntity;

public interface RzcxMapper  extends BaseDao<RzcxEntity, String>{
	public Page<RzcxEntity> findList(RzcxEntity RzcxEntity, PageRequest pageRequest);
	public Page<RzcxEntity> searchSwdbPage(RzcxEntity RzcxEntity,PageRequest pageRequest,@Param("startTime")String startTime ,@Param("endTime")String endTime,@Param("SearchDate")String SearchDate);

	public List<Map<String, Object>> sjChart(RzcxEntity RzcxEntity,PageRequest pageRequest,@Param("startTime")String startTime ,@Param("endTime")String endTime,@Param("SearchDate")String SearchDate);
	
	public List<Map<String, Object>> czChart(RzcxEntity RzcxEntity,PageRequest pageRequest,@Param("startTime")String startTime ,@Param("endTime")String endTime,@Param("SearchDate")String SearchDate);
	
	public Page<RzcxEntity> searchSwdbPage1(RzcxEntity RzcxEntity,PageRequest pageRequest,@Param("startTime")String startTime ,@Param("endTime")String endTime,@Param("SearchDate")String SearchDate);

}