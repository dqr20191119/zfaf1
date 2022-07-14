package com.cesgroup.prison.wfsb.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.wfsb.entity.PoliceDevice;

/**   
*    
* @projectName：prison   
* @ClassName：PoliceDeviceMapper   
* @Description：   警用器材
* @author：zhengke   
* @date：2017-12-13 19:58   
* @version        
*/
public interface PoliceDeviceMapper  extends BaseDao<PoliceDevice,String>{
	public Page<Map<String,String>> searchPoliceDevice(Map<String,Object> map,PageRequest pageRequest);
	
	public void updatePart(Map<String,Object> map);
}