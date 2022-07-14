package com.cesgroup.prison.inspect.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.inspect.entity.InspectLocalRelation;

public interface InspectLocalRelationMapper extends BaseDao<InspectLocalRelation, String> {
	
	Page<InspectLocalRelation> inspectLocalRelationListPage(Map<String, Object> paramMap, PageRequest pageRequest);
}