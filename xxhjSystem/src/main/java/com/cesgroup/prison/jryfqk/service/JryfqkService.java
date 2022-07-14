package com.cesgroup.prison.jryfqk.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.jryfqk.entity.JryfqkEntity;

public interface JryfqkService extends IBaseCRUDService<JryfqkEntity, String> {

	public List getJryafk();
	
	
	public Page<Map<String,Object>> findList(HttpServletRequest request,Pageable pageable);


	public Page<Map<String, Object>> findListZaiCe(HttpServletRequest request, Pageable pageable);


	public Page<Map<String, Object>> findListZaiYa(HttpServletRequest request, Pageable pageable);
}
