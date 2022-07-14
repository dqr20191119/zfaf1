package com.cesgroup.prison.wfsb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.wfsb.entity.PhysicalDeviceName;

/**   
*    
* @projectName：prison   
* @ClassName：PhysicalDeviceNameMapper   
* @Description：  物防设备名称 
* @author：zhengke   
* @date：2017-12-13 19:58   
* @version        
*/
public interface PhysicalDeviceNameMapper extends BaseDao<PhysicalDeviceName,String>{
	public Page<Map<String,String>> searchPhysicalDeviceName(Map<String,Object> map,PageRequest pageRequest);
	
	public List<Map<String,Object>> simplePhysicalDeviceName(@Param(value="pdnCusNumber")String pdnCusNumber);
	
	public void updatePart(Map<String,Object> map);
}