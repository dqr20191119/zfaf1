package com.cesgroup.prison.jfsb.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.jfsb.dao.DoorOpenMapper;
import com.cesgroup.prison.jfsb.entity.DoorOpenEntity;
import com.cesgroup.prison.jfsb.service.DoorOpenService;
@Service
@Transactional
public class DoorOpenServiceImpl extends BaseDaoService<DoorOpenEntity, String, DoorOpenMapper>
	implements DoorOpenService{
	@Resource
	DoorOpenMapper mapper;
	@Override
	public void updateInfo(DoorOpenEntity entity) throws Exception {
		mapper.updateInfo(entity);
	}
	@Override
	public Page<Map<String, Object>> listAll(DoorOpenEntity entity, Pageable pageable) throws Exception {
		return mapper.listAll(entity,pageable);
	}
	@Override
	public DoorOpenEntity getByjyid(String jyid) {
		
		return mapper.getByjyid(jyid);
	}
	
	
	
	
	

}
