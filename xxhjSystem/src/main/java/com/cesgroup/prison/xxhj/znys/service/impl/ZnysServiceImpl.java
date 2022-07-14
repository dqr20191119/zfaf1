package com.cesgroup.prison.xxhj.znys.service.impl;
 

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.xxhj.znys.entity.ZnysEntity;
import com.cesgroup.prison.xxhj.znys.service.ZnysService;
import com.cesgroup.prison.xxhj.znys.dao.ZnysMapper;

@Service("ZnysService")
public class ZnysServiceImpl extends BaseDaoService<ZnysEntity, String, ZnysMapper> implements ZnysService {
	
	@Resource
	private ZnysMapper ZnysMapper;

	@Override
	public Page<ZnysEntity> findList(ZnysEntity ZnysEntity, PageRequest pageRequest) {
		return ZnysMapper.findList(ZnysEntity,pageRequest);
	}


	@Override
	public Page<ZnysEntity> searchSwdbPage(ZnysEntity ZnysEntity, PageRequest pageRequest) {
		return ZnysMapper.searchSwdbPage(ZnysEntity,pageRequest);
	}



}
