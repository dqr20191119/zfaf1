package com.cesgroup.prison.criminal.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.criminal.entity.CriminalEntity;

@Service
public interface CriminalService  extends IBaseCRUDService<CriminalEntity, String> {
	
	public Map<String, Object> getCriminalCount(Map<String, Object> map);
	
	public Map<String, Object> searchListPage(Map<String, Object> map,PageRequest pageRequest);
	
}
