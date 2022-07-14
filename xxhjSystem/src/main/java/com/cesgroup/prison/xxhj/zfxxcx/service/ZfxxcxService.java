package com.cesgroup.prison.xxhj.zfxxcx.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.zfxxcx.entity.ZfxxcxEntity;

public interface ZfxxcxService extends IBaseCRUDService<ZfxxcxEntity, String> {
	public Page<Map<String, Object>> queryZfxxInfo(HttpServletRequest request,PageRequest pageRequest) throws Exception;
}
