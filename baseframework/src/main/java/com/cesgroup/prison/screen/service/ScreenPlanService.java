package com.cesgroup.prison.screen.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.screen.dao.ScreenPlanWindowCameraMapper;
import com.cesgroup.prison.screen.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.bean.AjaxMessage;

public interface ScreenPlanService extends IBaseCRUDService<ScreenPlanEntity, String> {

	public Page<Map<String, Object>> listAll(ScreenPlanEntity entity, Pageable pageable);

	public AjaxMessage addInfo(ScreenPlanEntity entity);

	public void updateInfo(ScreenPlanEntity entity) throws Exception;

	public ScreenPlanEntity findById(String id);

	public void deleById(String id);

	/**
	* @methodName: searchCombDataForPlan
	* @Description: 获取大屏预案下拉
	* @param cusNumber 监狱号
	* @param isDynamic 是否动态大屏预案（摄像头信号源动态）  0、否  1、是
	* @return List<Map<String,Object>>
	* @throws  
	*/
	public List<Map<String, Object>> searchCombDataForPlan(String cusNumber, String isDynamic);

	/**
	* @methodName: searchCombData
	* @Description:  获取大屏区域下拉
	* @param cusNumber 监狱号
	* @param planId 大屏预案id
	* @return List<Map<String,Object>>
	* @throws  
	*/
	public List<Map<String, Object>> searchCombData(String cusNumber, String planId);

	/**
	* @methodName: listAll
	* @Description: 信号分组查询
	* @param cusNumber 监狱号
	* @param screenPlanId 大屏预案id
	* @param screenAreaId 大屏区域id
	* @param type 1、查询大屏窗口  2、查询信号源
	* @return Map<String,Object>
	* @throws  
	*/
	public Map<String, Object> listAll(String cusNumber, String screenPlanId, String screenAreaId, String type);

	public AjaxMessage saveXhfz(Object xhfzMap);

	public ScreenPlanAreaRltnEntity findScreenPlanAreaRltnById(String id);

	/**
	* @methodName: listAllForSx
	* @Description: 待关联数据源查询（摄像头）
	* @param cusNumber
	* @param areaId
	* @param screenAreaId
	* @param screenPlanId
	* @return List<Map<String,Object>>
	* @throws  
	*/
	public List<Map<String, Object>> listAllForSx(String cusNumber, String areaId, String screenPlanId,
			String cameraName);

	/**
	* @methodName: deleteXhfzById
	* @Description: 删除大屏区域及关联的窗口和信号源
	* @param screenAreaId 大屏区域id
	* @throws  
	*/
	public void deleteXhfzById(String screenAreaId);

	/**
	* @methodName: deleteXhfzByIds
	* @Description: 删除信号分组
	* @param ids
	* @param type 1、批量删除大屏窗口 2、批量删除大屏信号源
	* @throws  
	*/
	public void deleteXhfzByIds(List<String> ids, String type);

	/**
	* @methodName: isRound
	* @Description: 判断是否需要轮询
	* @param screenPlanId
	* @return int 0、否 1、是
	* @throws  
	*/
	public int isRound(String screenPlanId);

    /**
     * 查询所有预案
     * @param screenPlanNewEntity
     * @param pageable
     * @return
     */
	public Page<ScreenPlanNewEntity> pageSelectAll(ScreenPlanNewEntity screenPlanNewEntity, Pageable pageable);

    /**
     * 查询电视墙预案关联的所有窗口
     * @param ScreenPlanId
     * @return
     */
    public List<ScreenPlanWindowNewEntity> selectWindowByScreenPlanId(String ScreenPlanId, String cusNumber);


    /**
     * @methodName: listAllForSxnew
     * @Description: 待关联数据源查询（摄像头）现在用的
     * @param cusNumber
     * @param areaId
     * @param screenAreaId
     * @param screenPlanId
     * @return List<Map<String,Object>>
     * @throws
     */
    public List<Map<String,Object>> listAllForSxNew(String cusNumber, String areaId, String screenPlanId,
                                                    String windowId);

    /**
     * 查询已经关联的摄像头
     * @param screenPlanWindowCameraEntity
     * @return
     */
    public List<ScreenPlanWindowCameraEntity>  selectListByScreenPlanWindowCameraEntity(ScreenPlanWindowCameraEntity screenPlanWindowCameraEntity);

    AjaxResult saveNew(Object xhfzMap);

    /**
     * 电视墙预案切换
     * @param id
     * @param tywallId
     */
    void screenPlanQh(String id, String tywallId,String cusNumber);

    /**
     * 上墙
     * @param id
     * @param tywallId
     * @param cusNumber
     */
    void screenPlanSq(String id, String tywallId, String cusNumber);
}
