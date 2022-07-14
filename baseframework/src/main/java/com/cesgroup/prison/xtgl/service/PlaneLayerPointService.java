package com.cesgroup.prison.xtgl.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.prison.xtgl.entity.PlaneLayerPoint;

public interface PlaneLayerPointService {
	//局部修改
  	public int updatePart(PlaneLayerPoint record);
  	//根据图层id删除点位
  	public int deleteByPlpLayerIdnty(String plpLayerIdnty);
  	public List<PlaneLayerPoint> findByPlpLayerIdnty(String plpLayerIdnty);
  	public List<Map<String,Object>> searchPlaneLayerPoint(String plpLayerIdnty);
  	
  	public void refreshPoint(String plpLayerIdnty,String cusNumber);
}
