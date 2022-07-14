package com.cesgroup.prison.wfsb.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.wfsb.entity.PhysicalDevice;

/**   
*    
* @projectName：prison   
* @ClassName：PhysicalDeviceMapper   
* @Description： 物防设备  
* @author：zhengke   
* @date：2017-12-13 19:57   
* @version        
*/
public interface PhysicalDeviceMapper  extends BaseDao<PhysicalDevice,String>{
   
	public Page<Map<String,String>> searchPhysicalDevice(Map<String,Object> map,PageRequest pageRequest);
	public void updatePart(Map<String,Object> map);
}