package com.cesgroup.prison.xtgl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.xtgl.dao.DeviceDprtmntMapper;
import com.cesgroup.prison.xtgl.entity.DeviceDprtmnt;
import com.cesgroup.prison.xtgl.service.DvcRoleService;
import com.google.common.collect.Lists;


@Service
public class DvcRoleServiceImpl extends BaseService<DeviceDprtmnt,String>  implements DvcRoleService{
	
	@Autowired
	private DeviceDprtmntMapper deviceDprtmntMapper;

	@Override
	public List<Map<String, Object>> searchExistDprtmnt(String ddrCusNumber, String ddrDvcTypeIndc) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("ddrCusNumber", ddrCusNumber);
		paramMap.put("ddrDvcTypeIndc",ddrDvcTypeIndc);
		return deviceDprtmntMapper.searchExistDprtmnt(paramMap);
	}

	@Override
	@Transactional
	public int batchInsert(List<Map<String, Object>> list) {
		if(list.size()>0) {
			List<List<Map<String, Object>>> assignList =Lists.partition(list, 99);
			for (int index = 0; index < assignList.size(); index++) {
				return deviceDprtmntMapper.batchInsert(assignList.get(index));
			}
		}	
		return 0;
	}

	@Override
	@Transactional
	public int batchDelete(List<String> list) {
		return deviceDprtmntMapper.batchDelete(list);
	}
	
	public List<Map<String,Object>> simpleAreaTreeByXML(String wid, String abdCusNumber, String dprtmntIdnty){
		Map<String, Object> map_param = new HashMap<String, Object>();
		map_param.put("wid", wid);
		map_param.put("abdCusNumber", abdCusNumber);
		map_param.put("dprtmntIdnty", dprtmntIdnty);
		return deviceDprtmntMapper.simpleAreaTreeByXML(map_param);
	}
	
	@Override
	public List<Map<String,Object>> simpleCameraTreeByXML(String wid,String cusNumber,String dprtmntIdnty,String cbdSttsIndc_except){
		Map<String, Object> map_param = new HashMap<String, Object>();
		map_param.put("wid", wid);
		map_param.put("cbdSttsIndc_except", cbdSttsIndc_except);
		//??????
		if("0".equals(wid)) {	
			map_param.put("ddrDvcTypeIndc", 1);
			map_param.put("ddrDprtmntIdnty", dprtmntIdnty);
			map_param.put("cbdCusNumber", cusNumber);
		}
		//??????
		else if("1".equals(wid)) {
			map_param.put("cbdCusNumber", cusNumber);
		}
		//??????
		else if("2".equals(wid)){
			map_param.put("cbdUseLimit", cusNumber);
		}
		//??????????????????????????????????????????
		else if("-1".equals(wid)){
			map_param.put("ddrDvcTypeIndc", 1);
			map_param.put("ddrDprtmntIdnty", dprtmntIdnty);
			map_param.put("cbdCusNumber", cusNumber);
		}
		return deviceDprtmntMapper.simpleCameraTreeByXML(map_param);
	}

	@Override
	public List<Map<String, Object>> simpleAreaCameraTreeByXML(String wid, String cusNumber, String dprtmntIdnty, String cbdSttsIndc_except) {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		
		List<Map<String,Object>> list=simpleAreaTreeByXML(wid,cusNumber,dprtmntIdnty);
		
		List<Map<String,Object>> cameraList=simpleCameraTreeByXML(wid, cusNumber, dprtmntIdnty, cbdSttsIndc_except);
		
		// ?????????????????????????????????????????????????????????????????????(1105110)?????????????????????(1105115)????????????????????????
		if(user != null && "110537".equals(user.getDprtmntCode())) {
			// ??????list???????????????????????????????????????????????????????????????
			String[] areaIdArray = {"1105110"};
			
			list = this.queryChildrenAreaListByParentAreaIdArray(areaIdArray, list);
		}
		
		// ??????list???cameraList??????camera???pId???list???id?????????camera??????list
		list = this.packageAreaListAndCameraList(list, cameraList);
		// list.addAll(cameraList);
		return list;
	}

	@Override
	public List<Map<String, Object>> simpleDepartmentTree(String cusNumber) throws Exception {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		List<OrgEntity> orgList = AuthSystemFacade.getAllChildrenOrgInfoByOrgKey(cusNumber);
		
		for(OrgEntity org : orgList) {
			System.out.println(org.isVirtualOrg());
			if(!org.isVirtualOrg()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", org.getOrgKey());
				map.put("name", org.getOrgName());	
				resultList.add(map);
			}
			
		}
		
		return resultList;
	}
	
	/**
	 * ??????????????????ID????????????????????????????????????
	 * 
	 * @param parentAreaIdArray
	 * @param totalAreaList
	 * @return
	 */
	private List<Map<String, Object>> queryChildrenAreaListByParentAreaIdArray(String[] parentAreaIdArray, List<Map<String, Object>> totalAreaList) {
		if(parentAreaIdArray == null || parentAreaIdArray.length <= 0) {
			return null;
		}
		// ??????totalAreaList????????????
		if(totalAreaList == null || totalAreaList.size() <= 0) {
			return null;
		}
		// ????????????ID???????????????????????????
		List<Map<String, Object>> parentAreaList = null;
		for(int i=0; i<parentAreaIdArray.length; i++) {
			Map<String, Object> tempParentArea = this.queryAreaFromAreaListByAreaId(parentAreaIdArray[i], totalAreaList);
			if(parentAreaList == null) {
				parentAreaList = new ArrayList<Map<String, Object>>();
			}
			parentAreaList.add(tempParentArea);
		}
		// ??????????????????????????????????????????????????????????????????
		return this.recursiveQueryChildrenAreaList(parentAreaList, totalAreaList, null);
	}

	/**
	 * ????????????????????????????????????
	 * 
	 * @param areaId
	 * @param areaList
	 * @return
	 */
	private Map<String, Object> queryAreaFromAreaListByAreaId(String areaId, List<Map<String, Object>> areaList) {
		if(areaId == null || "".equals(areaId)) {
			return null;
		}
		if(areaList == null || areaList.size() <= 0) {
			return null;
		}
		for(int i=0; i<areaList.size(); i++) {
			Map<String, Object> currentArea = areaList.get(i);
			String currentAreaId = currentArea.get("id") != null ? currentArea.get("id").toString() : null;
			if(currentAreaId != null && areaId.equals(currentAreaId)) {
				return currentArea;
			}
		}
		return null;
	}

	/**
	 * ??????????????????????????????????????????????????????????????????????????????
	 * 
	 * @param parentAreaList
	 * @param totalAreaList
	 * @param resultAreaList
	 * @return
	 */
	private List<Map<String, Object>> recursiveQueryChildrenAreaList(List<Map<String, Object>> parentAreaList, List<Map<String, Object>> totalAreaList, List<Map<String, Object>> resultAreaList) {
		// ??????parentAreaList????????????
		if(parentAreaList == null || parentAreaList.size() <= 0) {
			return resultAreaList;
		}
		// ??????totalAreaList????????????
		if(totalAreaList == null || totalAreaList.size() <= 0) {
			return resultAreaList;
		}
		// ???????????????list
		if(resultAreaList == null) {
			resultAreaList = new ArrayList<Map<String, Object>>();
		}
		List<Map<String, Object>> childrenAreaList = new ArrayList<Map<String, Object>>();
		// ????????????????????????????????????????????????????????????????????????
		for(int i=0; i<parentAreaList.size(); i++) {
			Map<String, Object> tempArea = parentAreaList.get(i);
			
			String tempAreaId = tempArea.get("id") != null ? tempArea.get("id").toString() : null;
			
			// ????????????ID??????totalAreaList????????????????????????????????????
			List<Map<String, Object>> tempChildrenAreaList = queryChildrenAreaListFromTotalAreaListByParentAreaId(tempAreaId, totalAreaList);
			
			if(tempChildrenAreaList != null && tempChildrenAreaList.size() > 0) {
				childrenAreaList.addAll(tempChildrenAreaList);
			}
		}
		
		// ???parentAreaList??????resultAreaList
		resultAreaList.addAll(parentAreaList);
		
		// ???parentAreaList??????
		parentAreaList.clear();
		
		// ???childrenAreaList????????????parentArea??????parentAreaList?????????????????????
		if(childrenAreaList != null && childrenAreaList.size() > 0) {
			parentAreaList.addAll(childrenAreaList);
		}
		this.recursiveQueryChildrenAreaList(parentAreaList, totalAreaList, resultAreaList);
		
		return resultAreaList;
	}

	/**
	 * ???????????????????????????????????????????????????
	 * 
	 * @param parentAreaId
	 * @param totalAreaList
	 * @return
	 */
	private List<Map<String, Object>> queryChildrenAreaListFromTotalAreaListByParentAreaId(String parentAreaId, List<Map<String, Object>> totalAreaList) {
		List<Map<String, Object>> areaList = null;
		if(parentAreaId == null || "".equals(parentAreaId)) {
			return null;
		}
		if(totalAreaList == null || totalAreaList.size() <= 0) {
			return null;
		}
		for(int i=0; i<totalAreaList.size(); i++) {
			Map<String, Object> currentArea = totalAreaList.get(i);
			String currentAreaPId = currentArea.get("pId") != null ? currentArea.get("pId").toString() : null;
			if(currentAreaPId != null && parentAreaId.equals(currentAreaPId)) {
				if(areaList == null) {
					areaList = new ArrayList<Map<String, Object>>();
				}
				areaList.add(currentArea);
			}
		}
		return areaList;
	}

	/**
	 * ???cameraList??????????????????camera??????areaList
	 * 
	 * @param areaList
	 * @param cameraList
	 * @return
	 */
	private List<Map<String, Object>> packageAreaListAndCameraList(List<Map<String, Object>> areaList, List<Map<String, Object>> cameraList) {
		if(areaList == null || areaList.size() <= 0) {
			return areaList;
		}
		if(cameraList == null || cameraList.size() <= 0) {
			return areaList;
		}
		// ??????areaList??????areaIdSet
		Set<String> areaIdSet = new HashSet<String>();
		for(int i=0; i<areaList.size(); i++) {
			Map<String, Object> currentArea = areaList.get(i);
			String currentAreaId = currentArea.get("id") != null ? currentArea.get("id").toString() : null;
			if(currentAreaId != null && !"".equals(currentAreaId)) {
				areaIdSet.add(currentAreaId);
			}
		}
		
		// ??????cameraList?????????????????????camera??????areaList
		for(int i=0; i<cameraList.size(); i++) {
			Map<String, Object> currentCamera = cameraList.get(i);
			String currentCameraPId = currentCamera.get("pId") != null ? currentCamera.get("pId").toString() : null;
			if(currentCameraPId != null && !"".equals(currentCameraPId) && areaIdSet.contains(currentCameraPId)) {
				areaList.add(currentCamera);
			}
		}
		return areaList;
	}
}
