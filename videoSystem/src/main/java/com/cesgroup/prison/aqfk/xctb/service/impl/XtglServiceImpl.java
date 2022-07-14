package com.cesgroup.prison.aqfk.xctb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesgroup.prison.aqfk.monitor.entity.MonitorDoc;
import com.cesgroup.prison.aqfk.monitor.service.MonitorService;
import com.cesgroup.prison.aqfk.xctb.service.XtglService;

@Service
public class XtglServiceImpl implements XtglService{
	@Autowired
	private MonitorService monitorService;
	
	public List<Map<String,Object>> getMonitorList(List<Map<String, String>> ids){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(ids==null) {
			return null;
		}
		for (int i = 0; i < ids.size(); i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			if(ids.get(i)==null) {
				continue;
			}
			List<Map<String,Object>> imageList = monitorService.searchRelationImgList(ids.get(i).get("ID"));
			MonitorDoc monitorDoc=monitorService.findById(ids.get(i).get("ID"));
			
			map.put("mdoAddr", monitorDoc.getMdoAddr());
			map.put("mdoProblem", monitorDoc.getMdoProblem());
			map.put("imageList", imageList);
			list.add(map);
		}
    	return list;
    }
    
}
