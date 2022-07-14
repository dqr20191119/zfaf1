package com.cesgroup.prison.xtgl.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xtgl.entity.PlaneLayer;

public interface PlaneLayerMapper extends BaseDao<PlaneLayer,String>{ 
    //局部修改
  	public int updatePart(PlaneLayer record);
  	public List<PlaneLayer> findByPliAreaId(String pliAreaId);
}