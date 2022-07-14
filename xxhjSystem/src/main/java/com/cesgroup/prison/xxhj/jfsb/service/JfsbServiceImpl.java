package com.cesgroup.prison.xxhj.jfsb.service;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.xxhj.jfsb.dao.JfsbMapper;
import com.cesgroup.prison.xxhj.jfsb.entity.Jfsb;

@Service("jfsbService")
public class JfsbServiceImpl extends BaseDaoService<Jfsb, String, JfsbMapper> implements JfsbService {
	
	@Resource
	private JfsbMapper jfsbMapper;

	@Override
	public List<Map<String,String>>listDeviceMasterInfo (String cusNumber, String typeIndc) {
		List<Map<String,String>> list=new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dmaCusNumber", cusNumber);
		map.put("dmaTypeIndc", typeIndc);
		list= jfsbMapper.listDeviceMasterInfo (map);
		return list;
	}
	
	@Override
	public List<Map<String,Object>>listTechnicalDevicePrisonList(HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> maps=new ArrayList<>();
		
		if((request.getParameter("type")).equals("1")) {
			
			map.put("cbdSttsIndc", request.getParameter("sttsIndc"));
			maps= jfsbMapper.listTechnicalCameraInfo(map);
		} else if ((request.getParameter("type")).equals("2")) {
				
			map.put("abdSttsIndc", request.getParameter("sttsIndc"));
			maps= jfsbMapper.listTechnicalAlertorInfo(map);
		} else if ((request.getParameter("type")).equals("3")) {
				
			map.put("pnbSttsIndc", request.getParameter("sttsIndc"));
			maps= jfsbMapper.listTechnicalPowerNetworkInfo(map);
		} else if ((request.getParameter("type")).equals("4")) {
				
			map.put("swiSttsIndc", request.getParameter("sttsIndc"));
			maps= jfsbMapper.listTechnicalSnakeInfo(map);
		} else if ((request.getParameter("type")).equals("5")) {
				
			map.put("ibiDvcSttsIndc", request.getParameter("sttsIndc"));
			maps= jfsbMapper.listTechnicalInfraredInfo(map);
		} else if ((request.getParameter("type")).equals("6")) {
				
			map.put("dcbSttsIndc", request.getParameter("sttsIndc"));
			maps= jfsbMapper.listTechnicalDoorInfo(map);
		}
		
		return addPrisonName(maps);
	}
	
	public List<Map<String, Object>> addPrisonName(List<Map<String, Object>> maps) throws Exception {
			
		List<OrgEntity> orgEntityList = AuthSystemFacade.getAllJyInfo();          // 监狱接口
		List<Map<String, Object>> list = new ArrayList<>();
		
		for(int i = 0; i < orgEntityList.size(); i++) {
			int k = 0;
			
			for(int j = 0; j < maps.size(); j++) {
				Map<String, Object> m = new HashMap<>();
				if(orgEntityList.get(i).getOrgKey().equals(maps.get(j).get("CUS_NUMBER") + "")) {
					
					m.put("OBD_ORGA_IDNTY", orgEntityList.get(i).getOrgKey());
					m.put("OBD_ORGA_NAME", orgEntityList.get(i).getOrgName());
					m.put("COUNT", maps.get(j).get("COUNT"));
					list.add(m);
					break;
				} else {
					
					k++;
					if(k == maps.size()) {
						m.put("OBD_ORGA_IDNTY", orgEntityList.get(i).getOrgKey());
						m.put("OBD_ORGA_NAME", orgEntityList.get(i).getOrgName());
						m.put("COUNT", "0");
						list.add(m);
						break;
					}
				}
			}
		}
			
		return list;
	}

	@Override
	public Page<Map<String, Object>> listOnePrisonCameraInfo(HttpServletRequest request, PageRequest pageRequest) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cbdCusNumber", request.getParameter("cusNumber"));	
		paramMap.put("cbdSttsIndc", request.getParameter("sttsIndc"));								
		paramMap.put("cbdUseLimit", request.getParameter("useLimit"));
		paramMap.put("deviceName", request.getParameter("deviceName"));
		paramMap.put("deviceId", request.getParameter("deviceId"));
		Page<Map<String, Object>> pageList = jfsbMapper.listOnePrisonCameraInfo(paramMap,pageRequest);
		
		return addPriName(pageList);
	}
	
	public Page<Map<String,Object>>listOnePrisonAlertorInfo(HttpServletRequest request, PageRequest pageRequest) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("abdCusNumber", request.getParameter("cusNumber"));	
		paramMap.put("abdSttsIndc", request.getParameter("sttsIndc"));	
		paramMap.put("deviceName", request.getParameter("deviceName"));
		paramMap.put("deviceId", request.getParameter("deviceId"));
		Page<Map<String, Object>> pageList = jfsbMapper.listOnePrisonAlertorInfo(paramMap,pageRequest);
		
		return addPriName(pageList);
	}
	
	public  Page<Map<String,Object>>listOnePrisonInfraredInfo(HttpServletRequest request,PageRequest pageRequest) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ibiCusNumber", request.getParameter("cusNumber"));	
		paramMap.put("ibiDvcSttsIndc", request.getParameter("sttsIndc"));	
		paramMap.put("deviceName", request.getParameter("deviceName"));
		paramMap.put("deviceId", request.getParameter("deviceId"));
		Page<Map<String, Object>> pageList = jfsbMapper.listOnePrisonInfraredInfo (paramMap,pageRequest);
		
		return addPriName(pageList);
	}
	
	public Page<Map<String,Object>>listOnePrisonPowerNetworkInfo(HttpServletRequest request,PageRequest pageRequest) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pnbCusNumber", request.getParameter("cusNumber"));	
		paramMap.put("pnbSttsIndc", request.getParameter("sttsIndc"));	
		paramMap.put("deviceName", request.getParameter("deviceName"));
		paramMap.put("deviceId", request.getParameter("deviceId"));
		Page<Map<String, Object>> pageList = jfsbMapper.listOnePrisonPowerNetworkInfo(paramMap,pageRequest);
		
		return addPriName(pageList);
	}
	
	public Page<Map<String,Object>>listOnePrisonDoorInfo(HttpServletRequest request,PageRequest pageRequest) throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dcbCusNumber", request.getParameter("cusNumber"));	
		paramMap.put("dcbSttsIndc", request.getParameter("sttsIndc"));
		paramMap.put("deviceName", request.getParameter("deviceName"));
		paramMap.put("deviceId", request.getParameter("deviceId"));
		Page<Map<String, Object>> pageList = jfsbMapper.listOnePrisonDoorInfo(paramMap,pageRequest);
		
		return addPriName(pageList);
	}
	
	public Page<Map<String,Object>>listOnePrisonSnakeInfo(HttpServletRequest request,PageRequest pageRequest) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("swiCusNumber", request.getParameter("cusNumber"));	
		paramMap.put("swiSttsIndc", request.getParameter("sttsIndc"));
		paramMap.put("deviceName", request.getParameter("deviceName"));
		paramMap.put("deviceId", request.getParameter("deviceId"));
		Page<Map<String, Object>> pageList = jfsbMapper.listOnePrisonSnakeInfo(paramMap,pageRequest);
		
		return addPriName(pageList);
	}
	
	//显示首页20条设备数据
	
	public List<Map<String,Object>>listOnePrisonDevicePart(HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
 		List<Map<String, Object>> list =new ArrayList<>();
 		int type =Integer.valueOf(request.getParameter("type")).intValue();
 		
 		switch (type) {
		case 1:
		//摄像头
			map.put("cbdCusNumber", request.getParameter("cusNumber"));
			map.put("cbdSttsIndc", request.getParameter("sttsIndc"));
			list= jfsbMapper.listOnePrisonCameraPart(map);
			break;
		case 2:
		//网络报警
			map.put("abdCusNumber", request.getParameter("cusNumber"));
			map.put("abdSttsIndc", request.getParameter("sttsIndc"));
			list= jfsbMapper.listOnePrisonAlertorPart(map);
			break;
		case 3:
	    //数字电网
			map.put("pnbCusNumber", request.getParameter("cusNumber"));
			map.put("pnbSttsIndc", request.getParameter("sttsIndc"));
			list= jfsbMapper.listOnePrisonPowerNetworkPart(map);
			break;
		case 4:
		//网络震动报警
			map.put("swiCusNumber", request.getParameter("cusNumber"));
			map.put("swiSttsIndc", request.getParameter("sttsIndc"));
			list= jfsbMapper.listOnePrisonSnakePart(map);
			break;
		case 5:
		//周界红外报警
			map.put("ibiCusNumber", request.getParameter("cusNumber"));
			map.put("ibiDvcSttsIndc", request.getParameter("sttsIndc"));
			list= jfsbMapper.listOnePrisonInfraredPart(map);
			break;
		case 6:
		//门禁
			map.put("dcbCusNumber", request.getParameter("cusNumber"));
			map.put("dcbSttsIndc", request.getParameter("sttsIndc"));
			list= jfsbMapper.listOnePrisonDoorPart(map);
			break;
 		}
 		return list;
	}

 	public List<Map<String,Object>>listSysDeviceInfo(HttpServletRequest request) {
		
 		Map<String, Object> paramMap = new HashMap<String, Object>();
 		List<Map<String, Object>> list =new ArrayList<>();
 		int type =Integer.valueOf(request.getParameter("type")).intValue();
 		
 		switch (type) {
		case 1:
			paramMap.put("cbdCusNumber", request.getParameter("cusNumber"));	
			paramMap.put("id", request.getParameter("id"));	
			list= jfsbMapper.listSysCameraInfo(paramMap);
			break;
		case 2:
			paramMap.put("abdCusNumber", request.getParameter("cusNumber"));	
			paramMap.put("id", request.getParameter("id"));	
			list= jfsbMapper.listSysAlertorBaseInfo(paramMap);
			break;
		case 3:
			paramMap.put("pnbCusNumber", request.getParameter("cusNumber"));	
			paramMap.put("id", request.getParameter("id"));	
			list= jfsbMapper.listSysPowerNetworkInfo(paramMap);
			break;
		case 4:
			paramMap.put("swiCusNumber", request.getParameter("cusNumber"));	
			paramMap.put("id", request.getParameter("id"));	
			list = jfsbMapper.listSnakeWireInfo(paramMap);
			break;
		case 5:
			paramMap.put("ibiCusNumber", request.getParameter("cusNumber"));	
			paramMap.put("id", request.getParameter("id"));	
			list= jfsbMapper.listSysInfraredBaseInfo(paramMap);
			break;
		case 6:
			paramMap.put("dcbCusNumber", request.getParameter("cusNumber"));	
			paramMap.put("id", request.getParameter("id"));	
			list= jfsbMapper.listSysDoorInfo(paramMap);
			break;
 		}

		return list;
	}
	
	public Page<Map<String,Object>> listdeviceMaintainRecord(String dmrCusNumber,String dmrDeviceType,String dmrDeviceIdnty,Pageable page) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dmrCusNumber", dmrCusNumber);
		map.put("dmrDeviceType", dmrDeviceType);
		map.put("dmrDeviceIdnty", dmrDeviceIdnty);
		map.put("dmrDeviceType", dmrDeviceType);
		
		return jfsbMapper.listdeviceMaintainRecord(map,page);
	}
	
	public Page<Map<String, Object>> addPriName(Page<Map<String, Object>> pageList) throws Exception {
		
		List<OrgEntity> orgEntityList = AuthSystemFacade.getAllJyInfo();
		for (int i = 0; i < pageList.getContent().size(); i++) {
			Map<String, Object> m=pageList.getContent().get(i);
		    for (int j = 0; j < orgEntityList.size(); j++) {
		    	if (orgEntityList.get(j).getOrgKey().equals(pageList.getContent().get(i).get("CUS_NUMBER") + "")) {
		    		m.put("OBD_ORGA_NAME", orgEntityList.get(j).getOrgName());
		    	}
		    }
		}
		
		return pageList;
	}
}