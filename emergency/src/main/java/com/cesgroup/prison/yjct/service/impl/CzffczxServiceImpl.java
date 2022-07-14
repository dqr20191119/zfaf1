package com.cesgroup.prison.yjct.service.impl;
 
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yjct.dao.CzffczxMapper;
import com.cesgroup.prison.yjct.entity.CzffczxEntity;
import com.cesgroup.prison.yjct.service.CzffczxService;

@Service("czffczxService")
public class CzffczxServiceImpl extends BaseDaoService<CzffczxEntity, String, CzffczxMapper> implements CzffczxService {
	 
	@Resource
	private CzffczxMapper czffczxMapper;

	@Override
	public List<CzffczxEntity> findAllList(CzffczxEntity czffczxEntity) {

		return czffczxMapper.findAllList(czffczxEntity);
	} 
}
