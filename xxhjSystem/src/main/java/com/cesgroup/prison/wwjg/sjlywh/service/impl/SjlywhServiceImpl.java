package com.cesgroup.prison.wwjg.sjlywh.service.impl;
 
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.wwjg.sjlywh.dao.SjlywhMapper;
import com.cesgroup.prison.wwjg.sjlywh.entity.SjlywhEntity;
import com.cesgroup.prison.wwjg.sjlywh.service.SjlywhService;

@Service("sjlywhService")
public class SjlywhServiceImpl extends BaseDaoService<SjlywhEntity, String, SjlywhMapper> implements SjlywhService {
	
	@Resource
	private SjlywhMapper sjlywhMapper;
	 
	
	@Override
	public SjlywhEntity getById(String id) { 
		
		SjlywhEntity SjlywhEntity = sjlywhMapper.getById(id);
		
		return SjlywhEntity;
	}

	@Override
	public Page<SjlywhEntity> findList(SjlywhEntity sjlywhEntity, PageRequest pageRequest) {
		return sjlywhMapper.findList(sjlywhEntity, pageRequest);
	}

	@Override
	public List<SjlywhEntity> findAllList(SjlywhEntity sjlywhEntity) {
		
		return sjlywhMapper.findAllList(sjlywhEntity);
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(SjlywhEntity sjlywhEntity) throws Exception {

		String id =sjlywhEntity.getId();		
		if(id != null && !"".equals(id)) {
			sjlywhMapper.update(sjlywhEntity);
		} else {			
			sjlywhMapper.insert(sjlywhEntity);			
		}
		
	}
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		sjlywhMapper.updateStatusByIds(Arrays.asList(idArr));
 		
	} 		
	
	@Override
	public SjlywhEntity getByCode(String code) { 
		
		SjlywhEntity SjlywhEntity = sjlywhMapper.getByCode(code);
		
		return SjlywhEntity;
	}
}
