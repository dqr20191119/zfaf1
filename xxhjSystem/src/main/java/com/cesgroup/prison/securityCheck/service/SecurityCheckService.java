package com.cesgroup.prison.securityCheck.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.securityCheck.entity.SecurityCheck;

public interface SecurityCheckService extends IBaseCRUDService<SecurityCheck, String> {
	/**
	 * 
	 * @param map
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);
}
