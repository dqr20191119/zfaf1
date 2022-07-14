package com.cesgroup.prison.jfsb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.prison.jfsb.entity.AlertorEntity;

public interface AlertorService extends IBaseCRUDService<AlertorEntity, String> {

	public Page<Map<String, Object>> listAll(AlertorEntity entity, Pageable pageable);

	Map<String, Object> findAlertorSum(AlertorEntity entity);

	public AjaxMessage addInfo(AlertorEntity entity);

	public void updateInfo(AlertorEntity entity) throws Exception;

	public void deleteByIds(List<String> list);

	public AlertorEntity findById(String id);

	/**
	* @methodName: searchCombData
	* @Description: 下拉数据
	* @param cusNumber 监狱号
	* @param areaId 区域编号
	* @return List<Map<String,Object>>
	* @throws  
	*/
	public List<Map<String, Object>> searchCombData(String cusNumber, String areaId);

}
