package com.cesgroup.prison.xxhj.xqdjjl.service.impl;
 

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.xxhj.xqdjjl.entity.XqdjjlEntity;
import com.cesgroup.prison.xxhj.xqdjjl.service.XqdjjlService;
import com.cesgroup.prison.xxhj.xqdjjl.dao.XqdjjlMapper;

@Service("XqdjjlService")
public class XqdjjlServiceImpl extends BaseDaoService<XqdjjlEntity, String, XqdjjlMapper> implements XqdjjlService {
	
	@Resource
	private XqdjjlMapper XqdjjlMapper;

	@Override
	public Page<XqdjjlEntity> findList(XqdjjlEntity XqdjjlEntity, PageRequest pageRequest) {
		return XqdjjlMapper.findList(XqdjjlEntity,pageRequest);
	}


	@Override
	public Page<XqdjjlEntity> searchSwdbPage(XqdjjlEntity XqdjjlEntity, PageRequest pageRequest) {
		return XqdjjlMapper.searchSwdbPage(XqdjjlEntity,pageRequest);
	}



}
