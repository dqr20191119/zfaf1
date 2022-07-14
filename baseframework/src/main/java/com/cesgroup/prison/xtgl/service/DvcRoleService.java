package com.cesgroup.prison.xtgl.service;

import java.util.List;
import java.util.Map;

public interface DvcRoleService {
	//查询已关联设备的部门 
	public List<Map<String,Object>> searchExistDprtmnt(String ddrCusNumber,String ddrDvcTypeIndc);
	
	public int batchInsert(List<Map<String,Object>> list);

	public int batchDelete(List<String> list);
	
	public List<Map<String,Object>> simpleCameraTreeByXML(String wid,String cusNumber,String dprtmntIdnty,String useLimit);
	
	public List<Map<String,Object>> simpleAreaCameraTreeByXML(String wid,String cusNumber,String dprtmntIdnty,String cbdSttsIndc_except);

	public List<Map<String,Object>> simpleDepartmentTree(String cusNumber) throws Exception;
}
