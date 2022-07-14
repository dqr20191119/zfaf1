package com.cesgroup.prison.zbgl.mbbm.service.impl;
 
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.zbgl.mbbm.dao.MbbmMapper;
import com.cesgroup.prison.zbgl.mbbm.entity.MbbmEntity;
import com.cesgroup.prison.zbgl.mbbm.service.MbbmService;


@Service("MbbmService")
public class MbbmServiceImpl extends BaseDaoService<MbbmEntity, String, MbbmMapper> implements MbbmService {
	   
	@Resource
	private MbbmMapper mbbmMapper;
	
	@Override
	public MbbmEntity getById(String id) { 
		
		MbbmEntity mbbm = mbbmMapper.getById(id);
		
		return mbbm;
	}
	
	@Override
	public Page<MbbmEntity> findList(MbbmEntity mbbmEntity, PageRequest pageRequest) {
		
		return mbbmMapper.findList(mbbmEntity, pageRequest);
	}
	
	@Override
	public List<Map<String, Object>> findAllList(MbbmEntity mbbmEntity) {
		
		return mbbmMapper.findAllList(mbbmEntity);
	}
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {
		
		String[] idArr = ids.split(",");
		mbbmMapper.updateStatusByIds(Arrays.asList(idArr));			 
	}
	
	@Override
	@Transactional
	public void deleteByConditions(MbbmEntity mbbmEntity) {
		
		mbbmMapper.deleteByConditions(mbbmEntity);
	}

	@Override
	@Transactional
	public void updateDmdZtById(MbbmEntity mbbmEntity) {
		mbbmMapper.updateDmdZtById(mbbmEntity);
	}
}
