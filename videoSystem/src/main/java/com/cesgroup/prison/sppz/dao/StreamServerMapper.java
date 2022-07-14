package com.cesgroup.prison.sppz.dao;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.sppz.entity.StreamServer;

/**   
*    
* @projectName：prison   
* @ClassName：StreamServerMapper   
* @Description：   
* @author：zhengke   
* @date：2017-12-10 21:05   
* @version        
*/
public interface StreamServerMapper extends BaseDao<StreamServer,String>{
	public Page<Map<String,String>> searchStreamServer(Map<String,Object> map,PageRequest pageRequest);
	
	/**
	* @methodName: simpleStreamServerList
	* @Description: 简单流媒体服务列表（供combobox使用）
	* @param ssiCusNumber
	* @return List<Map<String,Object>>    
	* @throws
	*/
	public List<Map<String,Object>> simpleStreamServerList(@Param(value="ssiCusNumber")String ssiCusNumber);

	public void deleteByIds(List<String> id);
}
