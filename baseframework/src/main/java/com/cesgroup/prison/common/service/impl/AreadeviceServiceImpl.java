package com.cesgroup.prison.common.service.impl;
 
import java.util.*;

import javax.annotation.Resource;

import com.cesgroup.framework.exception.BusinessLayerException;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.dao.AreadeviceMapper;
import com.cesgroup.prison.common.entity.AreadeviceEntity;
import com.cesgroup.prison.common.service.AreadeviceService;

@Service("areadeviceService")
public class AreadeviceServiceImpl extends BaseDaoService<AreadeviceEntity, String, AreadeviceMapper> implements AreadeviceService {
	
	@Resource
	private AreadeviceMapper areadeviceMapper;

	@Override
	public List<Map<String, Object>> findForCombotree(Map<String, Object> paramMap) {
		
		String id = String.valueOf(paramMap.get("id"));		
		String deviceType = String.valueOf(paramMap.get("deviceType"));	
		
		List<Map<String, Object>> areaList = areadeviceMapper.findAllArea(paramMap);
		
		if(id != null && !"".equals(id) && (areaList == null || areaList.size() == 0)) {
			
			// 按设备类型加载对应的设备信息
			if(CommonConstant.CAMERA_DEVICE_TYPE.equals(deviceType)) {
				// 加载摄像头
				List<Map<String, Object>> cameraList = areadeviceMapper.findAllCamera(paramMap);
				return cameraList;
			} else if(CommonConstant.TALK_DEVICE_TYPE.equals(deviceType)) {
				// 加载对讲分机
				List<Map<String, Object>> talkList = areadeviceMapper.findAllTalk(paramMap);
				return talkList;
			} else if(CommonConstant.ALERTOR_DEVICE_TYPE.equals(deviceType)) {
				// 加载报警器
				List<Map<String, Object>> alertorList = areadeviceMapper.findAllAlertor(paramMap);
				return alertorList;
			} else if(CommonConstant.DOOR_DEVICE_TYPE.equals(deviceType)) {
				// 加载门禁
				List<Map<String, Object>> doorList = areadeviceMapper.findAllDoor(paramMap);
				return doorList;
			} else if(CommonConstant.BROADCAST_DEVICE_TYPE.equals(deviceType)) {
				// 加载广播
				List<Map<String, Object>> broadcastList = areadeviceMapper.findAllBroadcast(paramMap);
				return broadcastList;
			} else if(CommonConstant.TALK_SERVER_DEVICE_TYPE.equals(deviceType)) {
				// 加载对讲主机
				List<Map<String, Object>> talkServerList = areadeviceMapper.findAllTalkServer(paramMap);
				return talkServerList;
			} else if(CommonConstant.LABEL_DEVICE_TYPE.equals(deviceType)) {
				// 加载地图标签
				List<Map<String, Object>> labelList = areadeviceMapper.findAllLabel(paramMap);
				return labelList;
			}
		}
		
		for(Map<String, Object> areaMap : areaList) {
			areaMap.put("isParent", true);
			areaMap.put("open", false);
		}
		
		// 只加载区域信息
		if("0".equals(deviceType)) {
			for(Map<String, Object> areaMap : areaList) {
				int childrenNum = Integer.parseInt(areaMap.get("childrenNum") + "");
				if(childrenNum == 0) {					 
					areaMap.put("isParent", false);
					areaMap.put("open", true);
				}
				
				areaMap.put("pid", id);
			}
		}
		
		return areaList;
	}

	@Override
	public List<Map<String, Object>> findDeviceList(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		String deviceType = String.valueOf(paramMap.get("deviceType"));	
					
		// 按设备类型加载对应的设备信息
		if(CommonConstant.CAMERA_DEVICE_TYPE.equals(deviceType)) {
			
			// 加载摄像头
			resultList = areadeviceMapper.findAllCamera(paramMap);
		} else if(CommonConstant.TALK_DEVICE_TYPE.equals(deviceType)) {
			
			// 加载对讲分机
			resultList = areadeviceMapper.findAllTalk(paramMap);
		} else if(CommonConstant.ALERTOR_DEVICE_TYPE.equals(deviceType)) {
			
			// 加载报警器
			resultList = areadeviceMapper.findAllAlertor(paramMap);
		} else if(CommonConstant.DOOR_DEVICE_TYPE.equals(deviceType)) {
			
			// 加载门禁
			resultList = areadeviceMapper.findAllDoor(paramMap);
		} else if(CommonConstant.BROADCAST_DEVICE_TYPE.equals(deviceType)) {
			
			// 加载广播
			resultList = areadeviceMapper.findAllBroadcast(paramMap);
		} else if(CommonConstant.TALK_SERVER_DEVICE_TYPE.equals(deviceType)) {
			
			// 加载对讲主机
			resultList = areadeviceMapper.findAllTalkServer(paramMap);
		}
		
		if(resultList != null) {
			for(int i = 0; i < resultList.size(); i++) {
				Map<String, Object> resultMap = resultList.get(i);
				resultMap.put("value", resultMap.get("id"));
				resultMap.put("text", resultMap.get("name"));
			}
		}
		
		return resultList;
	}

	@Override
	public List<Map<String, Object>> findForDepCombotree(Map<String, Object> paramMap) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		List<Map<String, Object>> parentAreaList = areadeviceMapper.findParenAreaIdAndName(paramMap);
		List<Map<String, Object>> childAreaList = areadeviceMapper.findChildAreaIdAndName(paramMap);

		for (Map<String, Object> parentMap : parentAreaList) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> childlist = new ArrayList<>();
			map.put("id", parentMap.get("id"));
			map.put("name", parentMap.get("name"));
			map.put("isParent", true);
			map.put("open", true);
			for (Map<String, Object> childMap : childAreaList) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("id", childMap.get("id"));
				map1.put("name", childMap.get("name"));
				map1.put("isParent", false);
				map1.put("open", true);
				if(childMap.get("id").toString().contains(parentMap.get("id").toString())){
					childlist.add(map1);
				}
			}
			map.put("children", childlist);
			resultList.add(map);
		}
		return resultList;
	}

	@Override
	public List<Map<String, Object>> queryAreaBoradcastTree(Map<String, Object> paramMap) throws BusinessLayerException {
		try {
			// 查询区域树
			List<Map<String, Object>> areaList = this.getDao().findAreaListByQueryMap(paramMap);

			// 查询区域关联的广播设备
			List<Map<String, Object>> broadcastList = new ArrayList<Map<String, Object>>();
			if (areaList != null && areaList.size() > 0) {
				for (Map<String, Object> areaMap : areaList) {
					String abdCusNumber = areaMap.get("ABD_CUS_NUMBER") != null ? areaMap.get("ABD_CUS_NUMBER").toString() : "";
					String abdAreaId = areaMap.get("ABD_AREA_ID") != null ? areaMap.get("ABD_AREA_ID").toString() : "";

					// 查询Map
					Map<String, Object> broadcastQueryMap = new HashMap<String, Object>();
					broadcastQueryMap.put("cusNumber", abdCusNumber);
					broadcastQueryMap.put("areaId", abdAreaId);

					// 根据监狱编号、区域ID，查询区域内的广播设备
					List<Map<String, Object>> tempBroadcastList = this.getDao().findBroadcastListByQueryMap(broadcastQueryMap);

					if (tempBroadcastList != null && tempBroadcastList.size() > 0) {
						broadcastList.addAll(tempBroadcastList);
					}
				}
			}

			// 遍历
			List<Map<String, Object>> treeList = this.packageAreaListAndBroadcastList(areaList, broadcastList);
			return treeList;
		} catch (Exception e) {
			throw new BusinessLayerException("生成广播区域树形数据发生异常" + e);
		}
	}

	/**
	 * 生成区域树，并将广播设备挂载到区域树
	 *
	 * @param areaList
	 * @param broadcastList
	 * @return
	 */
	private List<Map<String, Object>> packageAreaListAndBroadcastList(List<Map<String, Object>> areaList, List<Map<String, Object>> broadcastList) {
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		if(areaList == null || areaList.size() <= 0) {
			return treeList;
		}
		// 循环areaList获取区域树，并获取areaIdSet
		Set<String> areaIdSet = new HashSet<String>();
		for(Map<String, Object> area : areaList) {
			String cusNumber = area.get("ABD_CUS_NUMBER") != null ? area.get("ABD_CUS_NUMBER").toString() : "";
			String areaId = area.get("ABD_AREA_ID") != null ? area.get("ABD_AREA_ID").toString() : "";
			String parentAreaId = area.get("ABD_PARENT_AREA_ID") != null ? area.get("ABD_PARENT_AREA_ID").toString() : "";
			String areaName = area.get("ABD_AREA_NAME") != null ? area.get("ABD_AREA_NAME").toString() : "";

			if(areaId != null && !"".equals(areaId)) {
				areaIdSet.add(areaId);
			}
			//
			Map<String, Object> treeMap = new HashMap<String, Object>();
			treeMap.put("id", areaId);
			treeMap.put("name", areaName);
			treeMap.put("pId", parentAreaId);
			treeMap.put("nodeType", "area");
			treeMap.put("cusNumber", cusNumber);
			treeList.add(treeMap);
		}

		if(broadcastList == null || broadcastList.size() <= 0) {
			return treeList;
		}
		// 循环broadcastList，将符合条件的broadcast放入treeList
		for(Map<String, Object> broadcast : broadcastList) {
			String cusNumber = broadcast.get("BBD_CUS_NUMBER") != null ? broadcast.get("BBD_CUS_NUMBER").toString() : "";
			String areaId = broadcast.get("BBD_AREA_ID") != null ? broadcast.get("BBD_AREA_ID").toString() : "";
			String broadcastId = broadcast.get("ID") != null ? broadcast.get("ID").toString() : "";
			String broadcastName = broadcast.get("BBD_NAME") != null ? broadcast.get("BBD_NAME").toString() : "";
			String broadcastStatus = broadcast.get("BBD_STTS_INDC") != null ? broadcast.get("BBD_STTS_INDC").toString() : "";

			if(areaId != null && !"".equals(areaId) && areaIdSet.contains(areaId)) {
				//
				Map<String, Object> treeMap = new HashMap<String, Object>();
				treeMap.put("id", broadcastId);
				treeMap.put("name", broadcastName);
				treeMap.put("pId", areaId);
				treeMap.put("nodeType", "broadcast");
				treeMap.put("cusNumber", cusNumber);
				treeMap.put("status", broadcastStatus);
				treeList.add(treeMap);
			}
		}
		return treeList;
	}
}
