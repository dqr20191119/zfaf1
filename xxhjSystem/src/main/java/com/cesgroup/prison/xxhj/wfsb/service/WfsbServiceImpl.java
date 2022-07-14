package com.cesgroup.prison.xxhj.wfsb.service;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.xxhj.wfsb.dao.WfsbMapper;
import com.cesgroup.prison.xxhj.wfsb.entity.Wfsb;

@Service("wfsbService")
public class WfsbServiceImpl extends BaseDaoService<Wfsb, String, WfsbMapper> implements WfsbService {
	
	@Resource
	private WfsbMapper wfsbMapper;
	
	@Override
	public List<Map<String, String>> listPhysicalDeviceCount(String pdeCusNumber) {
		
		return wfsbMapper.listPhysicalDeviceCount(pdeCusNumber);
    }
	
	@Override
	public List<Map<String,String>>listPhysicalDeviceCountPrisonList (String pdeDeviceName, String obdParentId) throws Exception  {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("_parameter", pdeDeviceName);
		map.put("obdParentId", obdParentId);
		
		List<Map<String,String>> maps=new ArrayList<>();
		maps= wfsbMapper.listPhysicalDeviceCountPris(map);
		
		List<OrgEntity> orgEntityList = AuthSystemFacade.getAllJyInfo();          // 监狱接口
		List<Map<String, String>> list = new ArrayList<>();	
		for (int i = 0; i < orgEntityList.size(); i++) {
			int k = 0;
			for (int j = 0; j < maps.size(); j++) {
				
				Map<String, String> m = maps.get(j);
				if (orgEntityList.get(i).getOrgKey().equals(maps.get(j).get("PDE_CUS_NUMBER") + "")) {
					
					m.put("OBD_ORGA_NAME", orgEntityList.get(i).getOrgName());
					list.add(m);
					break;				
				} else {
					
					k++;
					if (k == maps.size()) {
						
						Map<String, String> ms = new HashMap<>();
						ms.put("OBD_ORGA_IDNTY", orgEntityList.get(i).getOrgKey());
						ms.put("OBD_ORGA_NAME", orgEntityList.get(i).getOrgName());
						ms.put("PDE_NORMAL_COUNT", "0");
						ms.put("PDE_ABNORMAL_COUNT", "0");
						list.add(ms);
						break;
					}
				}
			}
		}
		
		return list;
	}
}
