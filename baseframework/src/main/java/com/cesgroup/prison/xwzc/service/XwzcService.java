package com.cesgroup.prison.xwzc.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xwzc.entity.XwzcEntity;

public interface XwzcService extends IBaseCRUDService<XwzcEntity, String> {
	
	public Page<XwzcEntity> searchXwzcList(XwzcEntity xwzcEntity, PageRequest pageRequest,String type);
	
	public Map<String, Object> searchXwzcCount();
	
	 public Map<String, Object> dkImage(String id);
}
