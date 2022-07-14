package com.cesgroup.prison.zbgl.gwgl.service.impl;
 
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.zbgl.gwgl.dao.GwglMapper;
import com.cesgroup.prison.zbgl.gwgl.entity.GwglEntity;
import com.cesgroup.prison.zbgl.gwgl.service.GwglService;

@Service("gwglService")
public class GwglServiceImpl extends BaseDaoService<GwglEntity, String, GwglMapper> implements GwglService {
	
	@Resource
	private GwglMapper gwglMapper;

	@Override
	public GwglEntity getById(String id) {
		
		return gwglMapper.getById(id);
	}

	@Override
	public Page<GwglEntity> findList(GwglEntity gwglEntity, PageRequest pageRequest) {
		
		return gwglMapper.findList(gwglEntity, pageRequest);
	}
	
	@Override
	public List<GwglEntity> findAllList(GwglEntity gwglEntity) {
		
		return gwglMapper.findAllList(gwglEntity);
	}

	@Override
	@Transactional
	public void saveOrUpdate(GwglEntity gwglEntity) {

		String id = gwglEntity.getId();		
		if(id != null && !"".equals(id)) {
			gwglMapper.update(gwglEntity);
		} else {			
			gwglMapper.insert(gwglEntity);			
		}
	}	
	
	@Override
	@Transactional
	public void deleteById(String ids) {
		String[] idArr = ids.split(",");
 		gwglMapper.updateStatusByIds(Arrays.asList(idArr));			 
	}
	
}
