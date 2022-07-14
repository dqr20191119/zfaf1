package com.cesgroup.prison.zbgl.zbjk.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zbgl.zbjk.entity.ZbjkEntity;


public interface ZbjkService extends IBaseCRUDService<ZbjkEntity, String> {
	
	public Page<Map<String, Object>> findList(HttpServletRequest request, PageRequest pageRequest);

}
