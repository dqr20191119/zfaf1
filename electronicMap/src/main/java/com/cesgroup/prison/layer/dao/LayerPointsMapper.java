package com.cesgroup.prison.layer.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.layer.entity.LayerPoints;

public interface LayerPointsMapper extends BaseDao<LayerPoints, String>{

	void deleteByLayerId(String id);
	
	List<LayerPoints> findByLayerId(String id);
	
}