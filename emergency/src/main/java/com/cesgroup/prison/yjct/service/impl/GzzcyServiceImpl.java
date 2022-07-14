package com.cesgroup.prison.yjct.service.impl;
 
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yjct.dao.GzzcyMapper;
import com.cesgroup.prison.yjct.entity.GzzcyEntity;
import com.cesgroup.prison.yjct.service.GzzcyService;

@Service("gzzcyService")
public class GzzcyServiceImpl extends BaseDaoService<GzzcyEntity, String, GzzcyMapper> implements GzzcyService {
	 
	@Resource
	private GzzcyMapper gzzcyMapper;

	@Override
	public List<GzzcyEntity> findAllList(GzzcyEntity gzzcyEntity) {

		return gzzcyMapper.findAllList(gzzcyEntity);
	} 
}
