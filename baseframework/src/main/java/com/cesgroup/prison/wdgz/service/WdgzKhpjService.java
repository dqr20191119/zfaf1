package com.cesgroup.prison.wdgz.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.wdgz.entity.WdgzKhpjEntity;

public interface WdgzKhpjService extends IBaseCRUDService<WdgzKhpjEntity, String> {
	
	public Page<Map<String, Object>> findList(HttpServletRequest request, PageRequest pageRequest);
	


	public Map<String, Object>getWg(String zfjbxxId);
	
	public Map<String, Object>getMx(String zbId,String lx);
	
}
