package com.cesgroup.prison.xxhj.mjkgjl.service.impl;
 

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.xxhj.mjkgjl.entity.MjkgjlEntity;
import com.cesgroup.prison.xxhj.mjkgjl.service.MjkgjlService;
import com.cesgroup.prison.xxhj.mjkgjl.dao.MjkgjlMapper;
@Service("MjkgjlService")
public class MjkgjlServiceImpl extends BaseDaoService<MjkgjlEntity, String, MjkgjlMapper> implements MjkgjlService {
	
	@Resource
	private MjkgjlMapper MjkgjlMapper;

	@Override
	public Page<MjkgjlEntity> findList(MjkgjlEntity mjkgjlEntity, PageRequest pageRequest) {
		return MjkgjlMapper.findList(mjkgjlEntity,pageRequest);
	}


	@Override
	public Page<MjkgjlEntity> searchSwdbPage(MjkgjlEntity mjkgjlEntity, PageRequest pageRequest) {
		return MjkgjlMapper.searchSwdbPage(mjkgjlEntity,pageRequest);
	}



}
