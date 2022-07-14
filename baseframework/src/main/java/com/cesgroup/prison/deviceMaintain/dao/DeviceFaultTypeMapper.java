package com.cesgroup.prison.deviceMaintain.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.deviceMaintain.entity.DeviceFaultTypeEntity;

/**      
* @projectName：prison   
* @ClassName：DeviceFaultTypeMapper   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月17日 下午9:55:24   
* @version        
*/
public interface DeviceFaultTypeMapper extends BaseDao<DeviceFaultTypeEntity, String> {

	/**
	* @methodName: listAll
	* @Description:  分页查询类型
	* @param map
	* @param pageable
	* @return Page<Map<String,Object>>    
	* @throws
	*/
	Page<Map<String, Object>> listAllForType(Map<String, Object> map, Pageable pageable);

	/**
	* @methodName: listAll
	* @Description:  分页查询内容
	* @param map
	* @param pageable
	* @return Page<Map<String,Object>>    
	* @throws
	*/
	Page<Map<String, Object>> listAllForContent(Map<String, Object> map, Pageable pageable);

	Map<String, Object> findContentById(Map<String, Object> map);

	/**
	* @methodName: updateDeviceFaultTypeInfo
	* @Description: 更新 
	* @param map void    
	* @throws
	*/
	void updateDeviceFaultTypeInfo(Map<String, Object> map);

	/**
	* @methodName: deleteById
	* @Description:根据id删记录
	* @param list void    
	* @throws
	*/
	void deleteById(Map<String, Object> map);

	/**
	* @methodName: deleRelationDepartment
	* @Description: 逻辑删内容时物理删除部门关联
	* @param map void
	* @throws
	* @author：Tao.xu 
	* @date：2017年12月19日 上午11:33:20      
	*/
	void deleRelationDepartment(Map<String, Object> map);

	/**
	* @methodName: searchCombData
	* @Description: 下拉数据
	* @param map
	* @return Map<String,Object>    
	* @throws
	*/
	List<Map<String, Object>> searchCombData(Map<String, Object> map);

	/**
	* @methodName: updateRelationInfo
	* @Description: 更新部门关联 
	* @param map void    
	* @throws
	*/
	void updateRelationInfo(Map<String, Object> map);
	
	/**
	* @methodName: findDprtmntByCusNumberAndFaultType
	* @Description: 根据监狱号和故障类型查询对应维修部门和协助部门
	* @param map
	* @return List<Map<String,String>>    
	* @throws
	*/
	Map<String, Object> findDprtmntByCusNumberAndFaultType(Map<String, Object> map);

}
