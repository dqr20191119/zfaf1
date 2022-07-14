package com.cesgroup.prison.criminal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.criminal.dao.CriminalMapper;
import com.cesgroup.prison.criminal.entity.CriminalEntity;
import com.cesgroup.prison.criminal.service.CriminalService;

@Service("criminalService")
public class CriminalServiceImpl  extends BaseDaoService<CriminalEntity, String, CriminalMapper> implements CriminalService {
	
	@Resource
	private CriminalMapper criminalMapper;
	
	@Override
	public Map<String, Object> getCriminalCount(Map<String, Object> map) {
		Map<String, Object> countMap = new HashMap<>();
		Integer criminalCount = criminalMapper.getCriminalCount(map);
		countMap.put("criminalCount", criminalCount);
		Integer importCount = criminalMapper.getImportCount(map);
		countMap.put("importCount", importCount);
		return countMap;
	}

	@Override
	public Map<String, Object> searchListPage(Map<String, Object> map, PageRequest pageRequest) {
		Page<Map<String, Object>> page = criminalMapper.searchListPage(map, pageRequest);//查询罪犯
		List<Map<String, Object>> list = page.getContent();
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> m : list){//查询罪犯所在区域
			Map<String,Object> param = new HashMap<String,Object>();
			Map<String,Object> qyMap = criminalMapper.getQy(m);
			param.put("xm", m.get("XM"));
			param.put("bh", m.get("BH"));
			if(qyMap != null){
				if("0".equals(qyMap.get("BJ").toString())){
					param.put("qy", "公共区域");
				}else{
					param.put("qy", qyMap.get("QY").toString());
				}
			}else{
				param.put("qy", map.get("deptName").toString());
			}
			li.add(param);
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("pageNumber", page.getNumber() + 1);
        resultMap.put("totalPages", page.getTotalPages());
        resultMap.put("total", page.getTotalElements());
        resultMap.put("data", li);
		return resultMap;
	}

}
