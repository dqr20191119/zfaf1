package com.cesgroup.prison.xtgl.service;

import java.util.List;

import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.xtgl.entity.PlaneLayer;

public interface PlaneLayerService {
	//局部修改
	public int updatePart(PlaneLayer planeLayer);
	public List<PlaneLayer> findByPliAreaId(String pliAreaId);
	
	/**
	* @methodName: 	saveFile
	* @Description: 保存图层背景信息
	* @param id 	记录id
	* @param files  文件ids
	* @throws  
	*/
	public void saveFile(String id, String files);
	
	/**
	* @methodName: 	findFile
	* @Description: 查找图层背景信息
	* @param id 	记录id
	* @return List<AffixEntity>
	* @throws  
	*/
	public List<AffixEntity> findFile(String id);
	/**
	* @methodName: 	deleteFile
	* @Description: 删除图层背景信息
	* @param id 	业务id
	* @return 	void
	* @throws  
	*/
	public void deleteFile(String ywId);
}
