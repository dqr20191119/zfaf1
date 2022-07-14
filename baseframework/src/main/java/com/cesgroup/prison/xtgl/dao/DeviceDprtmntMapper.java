package com.cesgroup.prison.xtgl.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xtgl.entity.DeviceDprtmnt;

public interface DeviceDprtmntMapper  extends BaseDao<DeviceDprtmnt,String>{
	//查询已关联设备的部门 
	public List<Map<String,Object>> searchExistDprtmnt(Map<String, Object> map);
	
	public int batchInsert(List<Map<String,Object>> list);

	public int batchDelete(List<String> list);
	
	public List<Map<String,Object>> simpleCameraTreeByXML(Map<String,Object> map);
	
	public List<Map<String,Object>> simpleAreaTreeByXML(Map<String,Object> map);
}
