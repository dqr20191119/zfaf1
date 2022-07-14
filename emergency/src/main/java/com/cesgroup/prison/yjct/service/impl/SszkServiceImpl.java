package com.cesgroup.prison.yjct.service.impl;
 
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yjct.dao.SszkMapper;
import com.cesgroup.prison.yjct.entity.SszkEntity;
import com.cesgroup.prison.yjct.service.SszkService;

@Service("sszkService")
public class SszkServiceImpl extends BaseDaoService<SszkEntity, String, SszkMapper> implements SszkService {
	
	@Resource
	private SszkMapper sszkMapper;

	@Override
	public Map<String, Object> findConfig(Map<String, Object> paramMap) {
		
		return sszkMapper.findConfig(paramMap);
	}
}
