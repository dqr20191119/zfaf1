package com.cesgroup.prison.wwjg.fxdgl.service.impl;
 
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.wwjg.fxdgl.dao.FxdglMapper;
import com.cesgroup.prison.wwjg.fxdgl.entity.FxdglEntity;
import com.cesgroup.prison.wwjg.fxdgl.service.FxdglService;

@Service("fxdglService")
public class FxdglServiceImpl extends BaseDaoService<FxdglEntity, String, FxdglMapper> implements FxdglService {
	
	@Resource
	private FxdglMapper FxdglMapper;
	
	@Override
	public FxdglEntity getById(String id) { 
		
		FxdglEntity FxdglEntity = FxdglMapper.getById(id);
		
		return FxdglEntity;
	}

	@Override
	public Page<FxdglEntity> findList(FxdglEntity FxdglEntity, PageRequest pageRequest) {
		return FxdglMapper.findList(FxdglEntity, pageRequest);
	}

	@Override
	public List<FxdglEntity> findAllList(FxdglEntity FxdglEntity) {
		
		return FxdglMapper.findAllList(FxdglEntity);
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(FxdglEntity FxdglEntity) throws Exception {

		String id =FxdglEntity.getId();		
		if(id != null && !"".equals(id)) {
			FxdglMapper.update(FxdglEntity);
		} else {			
			FxdglMapper.insert(FxdglEntity);			
		}
		
	}
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		FxdglMapper.updateStatusByIds(Arrays.asList(idArr));
 		
	} 
	
	@Override
	public FxdglEntity getByCode(String code) { 
		
		FxdglEntity FxdglEntity = FxdglMapper.getByCode(code);
		
		return FxdglEntity;
	}
}
