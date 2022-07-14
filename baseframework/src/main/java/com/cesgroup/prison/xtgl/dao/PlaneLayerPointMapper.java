package com.cesgroup.prison.xtgl.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xtgl.entity.PlaneLayerPoint;

public interface PlaneLayerPointMapper  extends BaseDao<PlaneLayerPoint,String>{
	//局部修改
  	public int updatePart(PlaneLayerPoint record);
  	//根据图层id删除点位
  	public int deleteByPlpLayerIdnty(String plpLayerIdnty);
  	public List<PlaneLayerPoint> findByPlpLayerIdnty(String plpLayerIdnty);
  	public List<Map<String,Object>> searchPlaneLayerPoint(String plpLayerIdnty);
}