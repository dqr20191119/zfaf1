package com.cesgroup.prison.callNames.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.prison.callNames.entity.CallNamesDoneEntity;
import com.cesgroup.prison.callNames.entity.CallNamesRecordEntity;
import com.cesgroup.prison.callNames.entity.CallNamesUndoneEntity;

public interface CallNamesService extends IBaseCRUDService<CallNamesRecordEntity, String> {
	public List<Map<String, Object>> findPrisonerNumForCallNames(Map<String, Object> map);

	public List<Map<String, Object>> findForTree(HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	public Page<Map<String, Object>> listAll(CallNamesRecordEntity callNamesRecordEntity, Pageable pageable);

	public Page<Map<String, Object>> listAllForDone(CallNamesDoneEntity callNamesDoneEntity, Pageable pageable);

	public Page<Map<String, Object>> listAllForUndone(CallNamesUndoneEntity callNamesUndoneEntity, Pageable pageable);

	public void updateInfo(CallNamesRecordEntity callNamesRecordEntity) throws Exception;

	public void addInfo(CallNamesRecordEntity callNamesRecordEntity) throws Exception;

	/**
	 * @throws Exception 
	* @methodName: beginRollcall
	* @Description: 发起点名
	* @throws  
	*/
	public Map<String, Object> beginRollcall(CallNamesRecordEntity callNamesRecordEntity) throws Exception;

	/**
	* @methodName: endIngRollcall
	* @Description:  结束点名
	* @param callNamesRecordEntity
	* @return Map<String,Object>
	* @throws  
	*/
	public Map<String, Object> endIngRollcall(CallNamesRecordEntity callNamesRecordEntity);

	/**
	* @methodName: getEndRollcallList
	* @Description: 点名中查询
	* @param rollcallId 点名id
	* @param demptId 部门id
	* @return Map<String,Object>
	* @throws  
	*/
	public Map<String, Object> getNumber(String rollcallId, String demptId);

	/**
	* @methodName: getEndRollcallList
	* @Description: 点名详情
	* @param rollcallId 点名id
	* @param demptId 部门id
	* @param floorId 楼层id 即区域编码
	* @param cellId 监舍号
	* @return Map<String,Object>
	* @throws  
	*/
	public Map<String, Object> getEndRollcallList(String rollcallId, String demptId, String floorId, String cellId);

	/**
	* @methodName: getPersonDetail
	* @Description: 查询所罪犯信息
	* @param rollcallId 点名id
	* @return Map<String,Object>
	* @throws  
	*/
	public Map<String, Object> getPersonDetail(String rollcallId);

	/**
	 * @throws Exception 
	* @methodName: saveEndRollcallList
	* @Description: 保存点名记录
	* @param rollcallId
	* @throws  
	*/
	public AjaxMessage saveEndRollcallList(String rollcallId);

	public List<Map<String, Object>> getJSPrisonerInfo(HttpServletRequest request, HttpServletResponse response)
			throws Exception;
}
