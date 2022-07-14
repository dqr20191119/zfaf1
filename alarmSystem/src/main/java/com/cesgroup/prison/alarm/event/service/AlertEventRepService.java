package com.cesgroup.prison.alarm.event.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.alarm.event.entity.AlertEventRepEntity;
import com.cesgroup.prison.alarm.event.entity.AlertEvidenceRelationEntity;

public interface AlertEventRepService extends IBaseCRUDService<AlertEventRepEntity, String> {

	/**
	 * @throws Exception 
	* @methodName: addEvidenceRelation
	* @Description: add 报警事件
	* @param entity void
	* @throws  
	*/
	void addEventRep(AlertEventRepEntity entity) throws Exception;

	/**
	* @methodName: findEventRepByEntity
	* @Description: 报警事件
	* @param entity
	* @return AlertEventRepEntity
	* @throws  
	*/
	AlertEventRepEntity findEventRepByEntity(AlertEventRepEntity entity);

	/**
	 * @throws Exception 
	* @methodName: addEvidenceRelation
	* @Description: add 报警证据信息
	* @param entity void
	* @throws  
	*/
	void addEvidenceRelation(AlertEvidenceRelationEntity entity) throws Exception;

	/**
	* @methodName: findEvidenceRelationByEntity
	* @Description: 报警证据信息
	* @param entity
	* @return AlertEvidenceRelationEntity
	* @throws  
	*/
	AlertEvidenceRelationEntity findEvidenceRelationByEntity(AlertEvidenceRelationEntity entity);
}
