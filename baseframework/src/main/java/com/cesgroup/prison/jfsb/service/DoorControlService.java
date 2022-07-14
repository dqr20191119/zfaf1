package com.cesgroup.prison.jfsb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.jfsb.entity.DoorControlEntity;

/**      
* @projectName：prison   
* @ClassName：DoorControlService   
* @Description：门禁控制器   
* @author：Tao.xu   
* @date：2017年12月13日 下午5:51:29   
* @version        
*/
public interface DoorControlService extends IBaseCRUDService<DoorControlEntity, String> {

	public Page<Map<String, Object>> listAll(DoorControlEntity entity, Pageable pageable);

	public void addInfo(DoorControlEntity entity) throws Exception;

	public void updateInfo(DoorControlEntity entity) throws Exception;

	public void deleteByIds(List<String> list);

	public DoorControlEntity findById(String id);

	/**
	* @methodName: searchCombData
	* @Description: 下拉数据
	* @param cusNumber
	* @return Map<String,Object>    
	* @throws
	*/
	public List<Map<String, Object>> searchCombData(String cusNumber);

}
