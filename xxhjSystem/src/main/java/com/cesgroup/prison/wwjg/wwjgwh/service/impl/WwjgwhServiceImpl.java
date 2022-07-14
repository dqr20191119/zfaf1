package com.cesgroup.prison.wwjg.wwjgwh.service.impl;
 
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.wwjg.wwjgwh.dao.WwjgwhMapper;
import com.cesgroup.prison.wwjg.wwjgwh.entity.WwjgwhEntity;
import com.cesgroup.prison.wwjg.wwjgwh.service.WwjgwhService;

@Service("wwjgwhService")
public class WwjgwhServiceImpl extends BaseDaoService<WwjgwhEntity, String, WwjgwhMapper> implements WwjgwhService {
	
	@Resource
	private WwjgwhMapper wwjgwhMapper;
	 
	
	@Override
	public WwjgwhEntity getById(String id) { 
		
		WwjgwhEntity wwjgwhEntity = wwjgwhMapper.getById(id);
		
		return wwjgwhEntity;
	}

	@Override
	public Page<WwjgwhEntity> findList(WwjgwhEntity wwjgwhEntity, PageRequest pageRequest) {
		return wwjgwhMapper.findList(wwjgwhEntity, pageRequest);
	}

	@Override
	public List<WwjgwhEntity> findAllList(WwjgwhEntity wwjgwhEntity) {
		
		return wwjgwhMapper.findAllList(wwjgwhEntity);
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(WwjgwhEntity wwjgwhEntity) throws Exception {

		String id =wwjgwhEntity.getId();		
		if(id != null && !"".equals(id)) {
			wwjgwhMapper.update(wwjgwhEntity);
		} else {			
			wwjgwhMapper.insert(wwjgwhEntity);			
		}
		
	}
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		wwjgwhMapper.updateStatusByIds(Arrays.asList(idArr));
 		
	} 	
	
	@Override
	public WwjgwhEntity getByCode(String code) { 
		
		WwjgwhEntity wwjgwhEntity = wwjgwhMapper.getByCode(code);
		
		return wwjgwhEntity;
	}
}
