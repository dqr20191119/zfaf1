package com.cesgroup.prison.alarm.flow.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.alarm.flow.entity.FlowMasterEntity;

@Service
public interface FlowMasterService extends IBaseCRUDService<FlowMasterEntity, String> {

	/**
	 * 保存流程
	 * @param flowMaster
	 * @return
	 * @throws Exception
	 */
	public FlowMasterEntity saveFlow(FlowMasterEntity flowMaster) throws Exception;

	/**
	 * 根据id删除
	 * @param ids
	 */
	public int deleteByIds(List<String> ids);

	/**
	 * @param id
	 * @return
	 */
	public FlowMasterEntity findById(String id);

	public Page<Map<String, String>> findByPage(FlowMasterEntity flowMaster, PageRequest pageRequest) throws Exception;

	/**
	* @methodName: findMaster
	* @Description: 查流程主表信息
	* @param cusNumber 当前登陆用户监狱号
	* @param cusNum 省局监狱号
	* @param id 流程id
	* @return List<Map<String,String>>
	* @throws  
	*/
	public List<Map<String, Object>> findMaster(String cusNumber, String cusNum, String id);

	/**
	* @methodName: findFlowDtls
	* @Description: 查询流程详情
	* @param cusNumber 当前登陆用户监狱号
	* @param cusNum 省局监狱号
	* @param flowId 流程主表id
	* @return List<Map<String,Object>>
	* @throws  
	*/
	public List<Map<String, Object>> findFlowDtls(String cusNumber, String cusNum, String flowId);
	
	
	/**
	* @methodName: searchCombData
	* @Description: 下拉数据
	* @param cusNumber 监狱号
	* @return List<Map<String,Object>>
	* @throws  
	*/
	public List<Map<String, Object>> searchCombData(String cusNumber);

}
