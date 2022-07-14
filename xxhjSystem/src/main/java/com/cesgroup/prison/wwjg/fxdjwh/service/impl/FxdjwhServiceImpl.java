package com.cesgroup.prison.wwjg.fxdjwh.service.impl;
 
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.wwjg.fxdjwh.dao.FxdjwhMapper;
import com.cesgroup.prison.wwjg.fxdjwh.entity.FxdjwhEntity;
import com.cesgroup.prison.wwjg.fxdjwh.service.FxdjwhService;

@Service("FxdjwhService")
public class FxdjwhServiceImpl extends BaseDaoService<FxdjwhEntity, String, FxdjwhMapper> implements FxdjwhService {
	
	@Resource
	private FxdjwhMapper fxdjwhMapper;
	 
	
	@Override
	public FxdjwhEntity getById(String id) { 
		
		FxdjwhEntity fxdjwhEntity = fxdjwhMapper.getById(id);
		
		return fxdjwhEntity;
	}

	@Override
	public Page<FxdjwhEntity> findList(FxdjwhEntity fxdjwhEntity, PageRequest pageRequest) {
		return fxdjwhMapper.findList(fxdjwhEntity, pageRequest);
	}

	@Override
	public List<FxdjwhEntity> findAllList(FxdjwhEntity fxdjwhEntity) {
		
		return fxdjwhMapper.findAllList(fxdjwhEntity);
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(FxdjwhEntity fxdjwhEntity) throws Exception {

		String id =fxdjwhEntity.getId();		
		if(id != null && !"".equals(id)) {
			fxdjwhMapper.update(fxdjwhEntity);
		} else {			
			fxdjwhMapper.insert(fxdjwhEntity);			
		}
		
	}
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		fxdjwhMapper.updateStatusByIds(Arrays.asList(idArr));
 		
	} 	
	
	@Override
	public FxdjwhEntity getByCode(String code) { 
		
		FxdjwhEntity fxdjwhEntity = fxdjwhMapper.getByCode(code);
		
		return fxdjwhEntity;
	}
}
