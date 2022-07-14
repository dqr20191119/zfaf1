package com.cesgroup.prison.deviceMaintain.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.deviceMaintain.entity.DeviceFaultTypeEntity;
import com.cesgroup.prison.deviceMaintain.entity.FaultDepmtReltEntity;

/**      
* @projectName：prison   
* @ClassName：DeviceFaultTypeService   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月17日 下午9:57:52   
* @version        
*/
public interface DeviceFaultTypeService extends IBaseCRUDService<DeviceFaultTypeEntity, String> {

	/**
	* @methodName: listAll
	* @Description:  
	* @param entity
	* @param type 1、类别  2、内容
	* @param pageable
	* @return Page<Map<String,Object>>
	* @throws  
	*/
	public Page<Map<String, Object>> listAll(DeviceFaultTypeEntity entity, String type, Pageable pageable);

	/**
	* @methodName: findContentById
	* @Description: 根据id查维修内容
	* @param id
	* @return Map<String,Object>
	*/
	public Map<String, Object> findContentById(String id);

	public void addDeviceFaultTypeInfo(DeviceFaultTypeEntity entity) throws Exception;

	/**
	 * @throws Exception 
	* @methodName: updateDoorInfo
	* @Description: 更新
	* @param entity void    
	*/
	public void updateDeviceFaultTypeInfo(DeviceFaultTypeEntity entity) throws Exception;

	/**
	* @methodName: deleteById
	* @Description: 根据 id 逻辑删记录
	* @throws
	*/
	public void deleteById(DeviceFaultTypeEntity entity);

	/**
	* @methodName: deleRelationDepartment
	* @Description:  根据 id 物理删部门关联
	* @param id void
	* @throws
	*/
	public void deleRelationDepartment(String id);

	/**
	 * @return 
	* @methodName: findById
	* @Description: 通过id查纪录
	* @param id void    
	* @throws
	*/
	public DeviceFaultTypeEntity findById(String id);

	/**
	* @methodName: searchCombData
	* @Description: 下拉数据
	* @param cusNumber
	* @return Map<String,Object>    
	* @throws
	*/
	public List<Map<String, Object>> searchCombData(String cusNumber, String typeClassify, String sttsIndc,
			String faultId);

	/**
	* @methodName: addRelationInfo
	* @Description: 添加部门关联
	* @param entity void
	 * @throws Exception 
	*/
	public void addRelationInfo(FaultDepmtReltEntity entity) throws Exception;

	/**
	 * @throws Exception 
	* @methodName: updateRelationInfo
	* @Description: 更新部门关联 
	* @param map void    
	*/
	public void updateRelationInfo(FaultDepmtReltEntity entity) throws Exception;
	
	/**
	* @methodName: findDprtmntByCusNumberAndFaultType
	* @Description:  查询维修部门和协助部门
	* @param cusNumber 监狱号
	* @param faultId 维修类型id
	* @return Map<String,Object>
	* @throws  
	*/
	public Map<String, Object> findDprtmntByCusNumberAndFaultType(String cusNumber, String faultId);


}
