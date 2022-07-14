package com.cesgroup.prison.common.service.impl;
 
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.dao.CommonEvidenceMapper;
import com.cesgroup.prison.common.dao.CommonEvidenceUseMapper;
import com.cesgroup.prison.common.entity.CommonEvidenceEntity;
import com.cesgroup.prison.common.entity.CommonEvidenceUseEntity;
import com.cesgroup.prison.common.service.CommonEvidenceUseService;

@Service("commonEvidenceUseService")
public class CommonEvidenceUseServiceImpl extends BaseDaoService<CommonEvidenceUseEntity, String, CommonEvidenceUseMapper> implements CommonEvidenceUseService {
	
	@Resource
	private CommonEvidenceUseMapper commonEvidenceUseMapper;

	@Override
	public CommonEvidenceUseEntity getById(String id) {
		
		return commonEvidenceUseMapper.getById(id);
	}

	@Override
	public Page<CommonEvidenceUseEntity> findList(CommonEvidenceUseEntity commonEvidenceUseEntity, PageRequest pageRequest) {
		
		return commonEvidenceUseMapper.findList(commonEvidenceUseEntity, pageRequest);
	}

	@Override
	public List<CommonEvidenceUseEntity> findAllList(CommonEvidenceUseEntity commonEvidenceUseEntity) {
		
		return commonEvidenceUseMapper.findAllList(commonEvidenceUseEntity);
	}

	@Override
	@Transactional
	public void saveOrUpdate(CommonEvidenceUseEntity commonEvidenceUseEntity) {

		List<CommonEvidenceUseEntity> commonEvidenceUseEntityList = commonEvidenceUseEntity.getCommonEvidenceUseEntityList();
		for(CommonEvidenceUseEntity evidence : commonEvidenceUseEntityList) {
			evidence.setCeuCusNumber(commonEvidenceUseEntity.getCeuCusNumber());
			evidence.setCeuCrteTime(commonEvidenceUseEntity.getCeuCrteTime());
			evidence.setCeuCrteUserId(commonEvidenceUseEntity.getCeuCrteUserId());
		}
		
		commonEvidenceUseMapper.insert(commonEvidenceUseEntityList);			
	}	
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {
		
		String[] idArray = ids.split(",");
		for(String id : idArray) {
			commonEvidenceUseMapper.delete(id);
		}
	}
}
