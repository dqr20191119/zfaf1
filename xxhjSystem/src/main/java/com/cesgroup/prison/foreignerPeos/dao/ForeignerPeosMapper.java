package com.cesgroup.prison.foreignerPeos.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.foreignerPeos.entity.ForeignerPeosEntity;

public interface ForeignerPeosMapper extends BaseDao<ForeignerPeosEntity, String> {
	public Integer searchCounts(Map<String, Object> paramMap);//查询当日总人数(显示在首页的数量)
	
	public Page<ForeignerPeosEntity> findList(Map<String, Object> paramMap, PageRequest pageRequest);//初始查询结果
}