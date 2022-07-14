package com.cesgroup.prison.xxhj.mjxcjl.service.impl;
 

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.xxhj.mjxcjl.entity.MjxcjlEntity;
import com.cesgroup.prison.xxhj.mjxcjl.service.MjxcjlService;
import com.cesgroup.prison.xxhj.mjxcjl.dao.MjxcjlMapper;

@Service("MjxcjlService")
public class MjxcjlServiceImpl extends BaseDaoService<MjxcjlEntity, String, MjxcjlMapper> implements MjxcjlService {
	
	@Resource
	private MjxcjlMapper MjxcjlMapper;

	@Override
	public Page<MjxcjlEntity> findList(MjxcjlEntity MjxcjlEntity, PageRequest pageRequest) {
		return MjxcjlMapper.findList(MjxcjlEntity,pageRequest);
	}


	@Override
	public Page<MjxcjlEntity> searchSwdbPage(MjxcjlEntity MjxcjlEntity, PageRequest pageRequest) {
		return MjxcjlMapper.searchSwdbPage(MjxcjlEntity,pageRequest);
	}



}
