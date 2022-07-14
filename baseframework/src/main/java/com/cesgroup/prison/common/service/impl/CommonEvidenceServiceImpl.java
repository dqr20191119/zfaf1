package com.cesgroup.prison.common.service.impl;
 
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.dao.CommonEvidenceMapper;
import com.cesgroup.prison.common.entity.CommonEvidenceEntity;
import com.cesgroup.prison.common.service.CommonEvidenceService;

@Service("commonEvidenceService")
public class CommonEvidenceServiceImpl extends BaseDaoService<CommonEvidenceEntity, String, CommonEvidenceMapper> implements CommonEvidenceService {
	
	@Resource
	private CommonEvidenceMapper commonEvidenceMapper;

	@Override
	public CommonEvidenceEntity getById(String id) {
		
		return commonEvidenceMapper.getById(id);
	}

	@Override
	public Page<CommonEvidenceEntity> findList(CommonEvidenceEntity commonEvidenceEntity, PageRequest pageRequest) {
		
		return commonEvidenceMapper.findList(commonEvidenceEntity, pageRequest);
	}

	@Override
	public List<CommonEvidenceEntity> findAllList(CommonEvidenceEntity commonEvidenceEntity) {
		
		return commonEvidenceMapper.findAllList(commonEvidenceEntity);
	}

	@Override
	@Transactional
	public void saveOrUpdate(CommonEvidenceEntity commonEvidenceEntity) {
		
	}	
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {
		
	}
}
